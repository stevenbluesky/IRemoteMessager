package cn.com.isurpass.iremotemessager.targetdecision;

import cn.com.isurpass.iremotemessager.domain.User;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

/**
 * 指定的手机
 * @author jwzh
 */
@Component("cn.com.isurpass.iremotemessager.targetdecision.PhoneNumberTargetDecision")
@Scope("prototype")
public class PhoneNumberTargetDecision extends TargetDecisionBase {

    @Override
    protected List<User> descision() {
        String[][] phonenumbers = eventparameters.getObject("phonenumbers", String[][].class);
        ArrayList<User> userList = new ArrayList<>();

        for (int i = 0; phonenumbers != null && i < phonenumbers.length; i++) {
            User user = new User();
            user.setCountrycode(phonenumbers[i][0]);
            user.setPhonenumber(phonenumbers[i][1]);
            userList.add(user);
        }

        String phonenumber = eventparameters.getString("phonenumber");
        String countrycode = eventparameters.getString("countrycode");
        if (phonenumber != null && countrycode != null) {
            User user = new User();
            user.setCountrycode(countrycode);
            user.setPhonenumber(phonenumber);
            userList.add(user);
        }

        return userList;
    }
}
