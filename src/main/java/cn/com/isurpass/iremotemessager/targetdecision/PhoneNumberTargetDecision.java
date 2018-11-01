package cn.com.isurpass.iremotemessager.targetdecision;

import cn.com.isurpass.iremotemessager.domain.User;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

/**
 * 指定的手机
 * @author jwzh
 */
@Component("cn.com.isurpass.iremotemessager.targetdecision.PhoneNumberTargetDecision")
public class PhoneNumberTargetDecision extends TargetDecisionBase {

    @Override
    protected List<User> descision() {
        String[][] phonenumbers = eventparameters.getObject("phonenumbers", String[][].class);
        ArrayList<User> userList = new ArrayList<>();

        for (int i = 0; i < phonenumbers.length; i++) {
            User user = new User();
            user.setCountrycode(phonenumbers[i][0]);
            user.setPhonenumber(phonenumbers[i][1]);
            userList.add(user);
        }

        return userList;
    }
}
