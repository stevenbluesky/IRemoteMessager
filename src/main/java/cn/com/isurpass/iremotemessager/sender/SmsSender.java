package cn.com.isurpass.iremotemessager.sender;

import cn.com.isurpass.iremotemessager.framework.IMessageSender;
import cn.com.isurpass.iremotemessager.vo.EventData;
import cn.com.isurpass.iremotemessager.vo.SendResult;
import cn.com.isurpass.iremotemessager.vo.SmsData;

import java.util.List;

public class SmsSender implements IMessageSender<SmsData>{
    @Override
    public List<SendResult> send(EventData data, SmsData targetdata) {

        return null;
    }
}
