package cn.com.isurpass.iremotemessager.methoddecision;

import cn.com.isurpass.iremotemessager.SpringUtil;
import cn.com.isurpass.iremotemessager.domain.User;
import cn.com.isurpass.iremotemessager.vo.EventData;
import cn.com.isurpass.iremotemessager.vo.MailData;
import cn.com.isurpass.iremotemessager.vo.SmsData;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import java.util.List;

@Component("cn.com.isurpass.iremotemessager.methoddecision.MailAndSmsMethodDecision")
@Scope("prototype")
public class MailAndSmsMethodDecision extends MethodDecisionBase {
    private InnerMail innerMail;
    private InnerSms innerSms;

    @Override
    public void setMsgInfo(EventData data, List<User> msguser) {
        super.setMsgInfo(data, msguser);

        innerMail = (InnerMail) SpringUtil.getBean("cn.com.isurpass.iremotemessager.methoddecision.MailAndSmsMethodDecision$InnerMail");
        innerSms = (InnerSms) SpringUtil.getBean("cn.com.isurpass.iremotemessager.methoddecision.MailAndSmsMethodDecision$InnerSms");

        innerSms.setMsgInfo(data, msguser);
        innerMail.setMsgInfo(data, msguser);
    }

    @Override
    public List<MailData> getMailData() {
        return innerMail.getMailData();
    }

    @Override
    public List<SmsData> getSmsData() {
        return innerSms.getSmsData();
    }

    @Component
    @Scope("prototype")
    private class InnerMail extends MailMethodDecision {
    }

    @Component
    @Scope("prototype")
    private class InnerSms extends SmsMethodDecision {
    }
}
