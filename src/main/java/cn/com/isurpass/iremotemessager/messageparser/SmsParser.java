package cn.com.isurpass.iremotemessager.messageparser;

import cn.com.isurpass.iremotemessager.common.constant.MsgTemplateType;
import cn.com.isurpass.iremotemessager.framework.IMessageParser;
import cn.com.isurpass.iremotemessager.vo.EventData;
import cn.com.isurpass.iremotemessager.vo.SmsData;
import org.springframework.stereotype.Component;

import java.util.Arrays;
import java.util.List;

@Component("cn.com.isurpass.iremotemessager.messageparser.SmsParser")
public class SmsParser implements IMessageParser<SmsData> 
{
    @Override
    public List<SmsData> parse(EventData data, SmsData targetdata) 
    {
		MessageParser mp = new MessageParser(data,targetdata.getLanguage(),MsgTemplateType.sentence);
		
		targetdata.setMessage(mp.getMessage());

        return Arrays.asList(new SmsData[]{targetdata});
    }
}
