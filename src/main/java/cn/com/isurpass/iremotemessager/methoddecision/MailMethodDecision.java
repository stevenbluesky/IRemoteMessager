package cn.com.isurpass.iremotemessager.methoddecision;

import cn.com.isurpass.iremotemessager.domain.User;
import cn.com.isurpass.iremotemessager.vo.MailData;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

@Component("cn.com.isurpass.iremotemessager.methoddecision.MailMethodDecision")
public class MailMethodDecision extends MethodDecisionBase {
    @Override
    public List<MailData> getMailData() {
        HashMap<String, MailData> dataHashMap = new HashMap<>();
        for (User user : msguser) {
            String language = user.getLanguage();
            if (!dataHashMap.containsKey(language)) {
                MailData mailData = new MailData();
                mailData.setLanguage(language);
                mailData.setMailsList(new ArrayList<>());
                mailData.setPhoneusers(new ArrayList<>());

                dataHashMap.put(language, mailData);
            }

            MailData mailData = dataHashMap.get(language);
            mailData.getMailsList().add(user.getMail());
            mailData.getPhoneusers().add(user);
        }

        return new ArrayList<>(dataHashMap.values());
    }
}
