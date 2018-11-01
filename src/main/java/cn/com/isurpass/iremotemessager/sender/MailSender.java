package cn.com.isurpass.iremotemessager.sender;

import cn.com.isurpass.iremotemessager.framework.IMessageSender;
import cn.com.isurpass.iremotemessager.vo.EventData;
import cn.com.isurpass.iremotemessager.vo.MailData;
import cn.com.isurpass.iremotemessager.vo.SendResult;

import java.util.List;

public class MailSender implements IMessageSender<MailData>{
    @Override
    public List<SendResult> send(EventData data, MailData targetdata) {

        return null;
    }
}
