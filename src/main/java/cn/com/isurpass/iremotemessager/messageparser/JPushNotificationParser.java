package cn.com.isurpass.iremotemessager.messageparser;

import java.util.Arrays;
import java.util.List;

import javax.annotation.Resource;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.stereotype.Component;

import com.alibaba.fastjson.JSONObject;

import cn.com.isurpass.iremotemessager.common.constant.MsgTemplateType;
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

		MessageParser mp = new MessageParser(data,targetdata.getLanguage(),MsgTemplateType.sentence);
		String alart = mp.getMessage();
		

		mp = new MessageParser(data,targetdata.getLanguage(),MsgTemplateType.json);
		String extdata = mp.getMessage();
		
		Payload py = JPushHelper.createNotification(targetdata.getAliases(), alart ,JSONObject.parseObject(extdata),targetdata);
		targetdata.setPayload(py);
		
		return Arrays.asList(new JPushNotificationData[]{targetdata});
	}

}
