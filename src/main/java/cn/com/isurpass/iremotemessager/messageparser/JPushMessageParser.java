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
import cn.com.isurpass.iremotemessager.vo.JPushMessageData;

@Component
public class JPushMessageParser implements IMessageParser<JPushMessageData> 
{
	private static Log log = LogFactory.getLog(JPushMessageParser.class);

	@Resource
	private MsgContentTemplateService ctservice;
	
	@Override
	public List<JPushMessageData> parse(EventData data, JPushMessageData targetdata) 
	{		
		List<MsgContentTemplate> lst = ctservice.findByEventcodeAndLanguageAndType(data.getEventtype(),data.getPlatform(), "zh_CN", MsgMethodType.jpushmessage);
		
		MsgContentTemplate ct = ctservice.findContentTemplate(lst, MsgTemplateType.json);
		if ( ct == null )
		{
			log.warn("json template is null");
			return null;
		}
		
		MessageParser mp = new MessageParser(ct.getContenttemplate() , data);
		String extdata = mp.getMessage();
		
		Payload py = JPushHelper.createMessage(targetdata.getAliases(), JSONObject.parseObject(extdata));
		targetdata.setPayload(py);
		
		return Arrays.asList(new JPushMessageData[]{targetdata});
	}

}
