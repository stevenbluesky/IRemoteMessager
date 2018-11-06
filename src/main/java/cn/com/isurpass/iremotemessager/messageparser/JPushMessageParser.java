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
		
		MessageParser mp = new MessageParser(data, "zh_CN" , MsgTemplateType.json);
		String extdata = mp.getMessage();
		
		Payload py = JPushHelper.createMessage(targetdata.getAliases(), JSONObject.parseObject(extdata));
		targetdata.setPayload(py);
		
		return Arrays.asList(new JPushMessageData[]{targetdata});
	}

}
