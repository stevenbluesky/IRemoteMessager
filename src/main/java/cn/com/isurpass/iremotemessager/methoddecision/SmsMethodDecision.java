package cn.com.isurpass.iremotemessager.methoddecision;

import cn.com.isurpass.iremotemessager.domain.User;
import cn.com.isurpass.iremotemessager.vo.SmsData;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;

@Component("cn.com.isurpass.iremotemessager.methoddecision.SmsMethodDecision")
public class SmsMethodDecision extends MethodDecisionBase{
    private static Log log = LogFactory.getLog(SmsMethodDecision.class);

    @Override
    public List<SmsData> getSmsData() {
        HashMap<String, SmsData> dataHashMap = new HashMap<>();
        for (User user : msguser) {
            if(!check(user) || !checkUserSmsCount(user)){
                continue;
            }

            String language = user.getLanguage();
            if (!dataHashMap.containsKey(language)) {
                SmsData smsData = new SmsData();
                smsData.setPhoneusers(new ArrayList<>());
                smsData.setPhonenumersList(new ArrayList<>());
                smsData.setLanguage(language);
                dataHashMap.put(language, smsData);
            }

            SmsData smsData = dataHashMap.get(language);
            smsData.getPhonenumersList().add(Arrays.asList(user.getCountrycode(), user.getPhonenumber()));
            smsData.getPhoneusers().add(user);
        }

        return new ArrayList<>(dataHashMap.values());
    }

    protected boolean check(User user) {
        return true;
    }

    private boolean checkUserSmsCount(User user) {
        if ((user.getSmscount() == null || user.getSmscount() <= 0)) {
            log.warn(user.getPhoneuserid() + ": sms count is exhausted");
            return false;
        }
        user.setSmscount(user.getSmscount() - 1);
        return true;
    }


}
