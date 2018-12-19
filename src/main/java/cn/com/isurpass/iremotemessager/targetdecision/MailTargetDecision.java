package cn.com.isurpass.iremotemessager.targetdecision;

import cn.com.isurpass.iremotemessager.domain.User;
import org.apache.commons.lang.StringUtils;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

/**
 * 指定的邮箱
 * @author jwzh
 */
@Component("cn.com.isurpass.iremotemessager.targetdecision.MailTargetDecision")
@Scope("prototype")
public class MailTargetDecision extends TargetDecisionBase {

    @Override
    protected List<User> descision() {
        String[] mails = eventparameters.getObject("mails", String[].class);
        ArrayList<User> userList = new ArrayList<>();

        for (int i = 0; mails != null && i < mails.length; i++) {
            User user = new User();
            user.setMail(mails[i]);

            userList.add(user);
        }

        String mail = eventparameters.getString("mail");
        if (StringUtils.isNotBlank(mail)) {
            User user = new User();
            user.setMail(mail);

            userList.add(user);
        }

        return userList;
    }
}
