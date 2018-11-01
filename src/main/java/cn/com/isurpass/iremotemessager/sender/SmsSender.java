package cn.com.isurpass.iremotemessager.sender;

import cn.com.isurpass.iremotemessager.common.sms.SMSManageThread;
import cn.com.isurpass.iremotemessager.framework.IMessageSender;
import cn.com.isurpass.iremotemessager.vo.EventData;
import cn.com.isurpass.iremotemessager.vo.SMSVo;
import cn.com.isurpass.iremotemessager.vo.SendResult;
import cn.com.isurpass.iremotemessager.vo.SmsData;
import org.springframework.stereotype.Component;

import java.util.List;

@Component("cn.com.isurpass.iremotemessager.sender.SmsSender")
public class SmsSender implements IMessageSender<SmsData>{
    @Override
    public List<SendResult> send(EventData data, SmsData smsData) {

        String[][] phonenumbers = smsData.getPhonenumbers();
        for (int i = 0; i < phonenumbers.length; i++) {
            SMSVo smsVo = new SMSVo();
            smsVo.setCountrycode(phonenumbers[i][0]);
            smsVo.setPhonenumber(phonenumbers[i][1]);
            smsVo.setMessage(smsData.getMessage());
            smsVo.setPlatform(data.getPlatform());

            SMSManageThread.addSMS(smsVo);
        }
        return null;
    }
}
