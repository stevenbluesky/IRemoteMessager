package cn.com.isurpass.iremotemessager.methoddecision;

import cn.com.isurpass.iremotemessager.vo.SmsData;
import org.springframework.stereotype.Component;

import java.util.List;

@Component("cn.com.isurpass.iremotemessager.methoddecision.JPushNotificationMailSmsMethodDecision")
public class JPushNotificationMailSmsMethodDecision extends JPushNotificationMailMethodDecision {
    @Override
    public List<SmsData> getSmsData() {
        InnerSms innerSms = new InnerSms();
        return innerSms.getSmsData();
    }

    private class InnerSms extends SmsMethodDecision{

    }

}
