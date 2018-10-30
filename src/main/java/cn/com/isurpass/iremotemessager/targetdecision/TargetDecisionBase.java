package cn.com.isurpass.iremotemessager.targetdecision;

import java.util.List;

import javax.annotation.Resource;

import org.apache.commons.beanutils.PropertyUtils;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import com.alibaba.fastjson.JSONObject;

import cn.com.isurpass.iremotemessager.domain.Gateway;
import cn.com.isurpass.iremotemessager.domain.User;
import cn.com.isurpass.iremotemessager.domain.ZWaveDevice;
import cn.com.isurpass.iremotemessager.framework.IMessageTargetDecision;
import cn.com.isurpass.iremotemessager.service.GatewayService;
import cn.com.isurpass.iremotemessager.service.UserService;
import cn.com.isurpass.iremotemessager.service.ZWaveDeviceService;
import cn.com.isurpass.iremotemessager.vo.EventData;
import cn.com.isurpass.iremotemessager.vo.MsgUser;

public abstract class TargetDecisionBase implements IMessageTargetDecision 
{
	private static Log log = LogFactory.getLog(TargetDecisionBase.class);

	@Resource
	protected UserService userservice;
	@Resource
	private GatewayService gatewayservice;
	@Resource
	private ZWaveDeviceService zwavedeviceservice ;
	
	protected EventData data;
	protected JSONObject eventparameters;
	protected JSONObject domainobjects = new JSONObject();
	protected User phoneuser ;
	protected Gateway gateway;
	protected ZWaveDevice zwavedevice ;
	
	@Override
	public List<MsgUser> messageTarget(EventData data) 
	{
		this.data = data;
		this.eventparameters = data.getEventparameters();
		data.setDomainobjects(domainobjects);;
		
		initDomainObject();
		
		return descision();
	}
	
	protected abstract List<MsgUser> descision();
	
	protected void initDomainObject()
	{
		if ( eventparameters.containsKey("phoneuserid"))
			phoneuser = userservice.findById(eventparameters.getInteger("phoneuserid"));

		if ( eventparameters.containsKey("deviceid") )
			gateway = gatewayservice.findById(eventparameters.getString("deviceid"));
		
		if ( eventparameters.containsKey("zwavedeviceid"))
			zwavedevice = zwavedeviceservice.findById(eventparameters.getInteger("zwavedeviceid"));
		
		if ( gateway == null && zwavedevice != null )
			gateway = gatewayservice.findById(zwavedevice.getDeviceid());
		
		if ( phoneuser == null && gateway != null && gateway.getPhoneuserid() != null  )
			phoneuser = userservice.findById(gateway.getPhoneuserid());
		
		if (phoneuser != null )
			domainobjects.put("user", phoneuser);
		
		if ( gateway != null )
			domainobjects.put("gateway", gateway);
	}
	

	protected MsgUser createMsgUser(User pu)
	{
		MsgUser mu = new MsgUser();
		
		mu.setPhoneuser(pu);
		try 
		{
			PropertyUtils.copyProperties(mu, pu);
		} 
		catch (Throwable t) 
		{
			log.error(t.getMessage() ,t);
			return null ;
		}
		return mu ;
	}
	
	
}
