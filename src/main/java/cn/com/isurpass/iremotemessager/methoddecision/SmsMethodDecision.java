package cn.com.isurpass.iremotemessager.methoddecision;

import cn.com.isurpass.iremotemessager.domain.User;
import cn.com.isurpass.iremotemessager.vo.SmsData;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;

@Component("cn.com.isurpass.iremotemessager.methoddecision.SmsMethodDecision")
public class SmsMethodDecision extends MethodDecisionBase{

    @Override
    public List<SmsData> getSmsData() {
        HashMap<String, SmsData> dataHashMap = new HashMap<>();
        for (User user : msguser) {
            String language = user.getLanguage();
            if (!dataHashMap.containsKey(language)) {
                SmsData smsData = new SmsData();
                smsData.setPhoneusers(new ArrayList<>());
                smsData.setPhonenumersList(new ArrayList<>());
                dataHashMap.put(language, smsData);
            }

            SmsData smsData = dataHashMap.get(language);
            smsData.getPhonenumersList().add(Arrays.asList(user.getCountrycode(), user.getPhonenumber()));
            smsData.getPhoneusers().add(user);
        }

        return new ArrayList<>(dataHashMap.values());
    }
}
