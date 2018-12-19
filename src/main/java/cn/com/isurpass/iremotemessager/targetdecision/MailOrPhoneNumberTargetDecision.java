package cn.com.isurpass.iremotemessager.targetdecision;

import cn.com.isurpass.iremotemessager.SpringUtil;
import cn.com.isurpass.iremotemessager.domain.User;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.List;

/**
 * 指定的邮箱或者手机号码
 */
@Component("cn.com.isurpass.iremotemessager.targetdecision.MailOrPhoneNumberTargetDecision")
@Scope("prototype")
public class MailOrPhoneNumberTargetDecision extends TargetDecisionBase {
    private InnerMailTargetDecision innerMail;
    private InnerPhoneNumberTargetDecision innerPhoneNumber;

    @Override
    protected List<User> descision() {
        innerMail = (InnerMailTargetDecision) SpringUtil.getBean("cn.com.isurpass.iremotemessager.targetdecision.MailOrPhoneNumberTargetDecision$InnerMailTargetDecision");
        innerPhoneNumber = (InnerPhoneNumberTargetDecision) SpringUtil.getBean("cn.com.isurpass.iremotemessager.targetdecision.MailOrPhoneNumberTargetDecision$InnerPhoneNumberTargetDecision");

        ArrayList<User> userList = new ArrayList<>();
        userList.addAll(innerMail.descision());
        userList.addAll(innerPhoneNumber.descision());

        return userList;
    }

    @Component
    @Scope("prototype")
    private class InnerMailTargetDecision extends MailTargetDecision{}
    @Component
    @Scope("prototype")
    private class InnerPhoneNumberTargetDecision extends  PhoneNumberTargetDecision{}
}