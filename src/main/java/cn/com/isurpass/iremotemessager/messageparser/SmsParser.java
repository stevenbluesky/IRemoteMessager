package cn.com.isurpass.iremotemessager.messageparser;

import cn.com.isurpass.iremotemessager.framework.IMessageParser;
import cn.com.isurpass.iremotemessager.vo.EventData;
import cn.com.isurpass.iremotemessager.vo.SmsData;
import org.springframework.stereotype.Component;

import java.util.Arrays;
import java.util.List;

@Component
public class SmsParser implements IMessageParser<SmsData> {
    @Override
    public List<SmsData> parse(EventData data, SmsData targetdata) {
        targetdata.setMessage("test123");

        return Arrays.asList(new SmsData[]{targetdata});
    }
}
