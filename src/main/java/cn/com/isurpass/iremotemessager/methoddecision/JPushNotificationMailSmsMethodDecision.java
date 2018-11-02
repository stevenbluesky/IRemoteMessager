package cn.com.isurpass.iremotemessager.methoddecision;

import cn.com.isurpass.iremotemessager.domain.User;
import cn.com.isurpass.iremotemessager.vo.EventData;
import cn.com.isurpass.iremotemessager.vo.SmsData;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import java.util.List;

@Component("cn.com.isurpass.iremotemessager.methoddecision.JPushNotificationMailSmsMethodDecision")
public class JPushNotificationMailSmsMethodDecision extends JPushNotificationMailMethodDecision {
    @Resource
    private InnerSms innerSms;

    @Override
    public void setMsgInfo(EventData data, List<User> msguser) {
        super.setMsgInfo(data, msguser);
        innerSms.setMsgInfo(data, msguser);
    }

    @Override
    public List<SmsData> getSmsData() {

        return innerSms.getSmsData();
    }

    private class InnerSms extends SmsMethodDecision{

    }

}
