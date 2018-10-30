package cn.com.isurpass.iremotemessager.messageparser;

import java.util.Arrays;
import java.util.List;

import javax.annotation.Resource;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.stereotype.Component;

import com.alibaba.fastjson.JSONObject;

import cn.com.isurpass.iremotemessager.constant.MsgMethodType;
import cn.com.isurpass.iremotemessager.constant.MsgTemplateType;
import cn.com.isurpass.iremotemessager.domain.MsgContentTemplate;
import cn.com.isurpass.iremotemessager.framework.IMessageParser;
import cn.com.isurpass.iremotemessager.jpush.JPushHelper;
import cn.com.isurpass.iremotemessager.jpush.vo.Payload;
import cn.com.isurpass.iremotemessager.service.MsgContentTemplateService;
import cn.com.isurpass.iremotemessager.vo.EventData;
import cn.com.isurpass.iremotemessager.vo.JPushNotificationData;

@Component
public class JPushNotificationParser implements IMessageParser<JPushNotificationData> 
{
	private static Log log = LogFactory.getLog(JPushNotificationParser.class);
	
	@Resource
	private MsgContentTemplateService ctservice;
	
	@Override
	public List<JPushNotificationData> parse(EventData data, JPushNotificationData targetdata) 
	{	
		List<MsgContentTemplate> lst = ctservice.findByEventcodeAndLanguageAndType(data.getEventtype(),data.getPlatform(), targetdata.getLanguage(), MsgMethodType.jpushnotification);
		
		MsgContentTemplate ct = ctservice.findContentTemplate(lst, MsgTemplateType.sentence);
		if ( ct == null )
		{
			log.warn("sentence template is null");
			return null;
		}
		
		MessageParser mp = new MessageParser(ct.getContenttemplate() , data);
		String alart = mp.getMessage();
		
		ct = ctservice.findContentTemplate(lst, MsgTemplateType.json);
		if ( ct == null )
		{
			log.warn("json template is null");
			return null;
		}
		
		mp = new MessageParser(ct.getContenttemplate() , data);
		String extdata = mp.getMessage();
		
		Payload py = JPushHelper.createNotification(targetdata.getAliases(), alart ,JSONObject.parseObject(extdata),targetdata);
		targetdata.setPayload(py);
		
		return Arrays.asList(new JPushNotificationData[]{targetdata});
	}

}
