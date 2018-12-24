package cn.com.isurpass.iremotemessager.methoddecision;

import cn.com.isurpass.iremotemessager.common.constant.IRemoteConstantDefine;
import cn.com.isurpass.iremotemessager.domain.User;
import cn.com.isurpass.iremotemessager.jms.JMSUtil;
import cn.com.isurpass.iremotemessager.service.SystemParameterService;
import cn.com.isurpass.iremotemessager.vo.SmsData;
import com.alibaba.fastjson.JSONObject;
import org.apache.commons.lang.StringUtils;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;

@Component("cn.com.isurpass.iremotemessager.methoddecision.SmsMethodDecision")
@Scope("prototype")
public class SmsMethodDecision extends MethodDecisionBase{
    private static Log log = LogFactory.getLog(SmsMethodDecision.class);
    private static final int SMS_COUNT_EXHAUSTED = 0;
    private static final String SMS_EXHAUSTED_JMS_TOPIC = "smsrunout";

    @Resource
    private SystemParameterService systemParameterService;

    @Override
    public List<SmsData> getSmsData() {
        HashMap<String, SmsData> dataHashMap = new HashMap<>();
        for (User user : msguser) {
            if(!checkPhonenumber(user)){
                continue;
            }
            if(!check(user) || !checkUserSmsCount(user)){
                continue;
            }
            sendRemainingNumber(user);
            sendSMSExhaustedJMS(user);

            String language = getLanguage(user);
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

    private void sendRemainingNumber(User user) {
        if (IRemoteConstantDefine.REMAINING_NUMBER.equals(data.getEventtype())) {
            return;
        }
        JSONObject json = new JSONObject();
        json.put("eventtype", IRemoteConstantDefine.REMAINING_NUMBER);
        json.put("platform", data.getPlatform());
        json.put("type", IRemoteConstantDefine.REMAINING_NUMBER);
        json.put("smsnumber", user.getSmscount());
        json.put("phoneuserid", user.getPhoneuserid());
        JMSUtil.commitMessage(json.toJSONString(), IRemoteConstantDefine.REMAINING_NUMBER);
    }

    private String getLanguage(User user) {
        if (user.getLanguage() != null) {
            return user.getLanguage();
        }
        String language = systemParameterService.getStringValue(IRemoteConstantDefine.KEY_DEFAULT_LANGUAGE);
        return language == null
                ? IRemoteConstantDefine.DEFAULT_LANGUAGE
                : language;
    }

    private boolean checkPhonenumber(User user) {
        return StringUtils.isNotBlank(user.getPhonenumber())
                && StringUtils.isNotBlank(user.getCountrycode());
    }

    protected boolean check(User user) {
        return true;
    }

    private boolean checkUserSmsCount(User user) {
        if (SMS_EXHAUSTED_JMS_TOPIC.equals(data.getEventtype())
                || user.getSmscount() == null) {
            return true;
        }

        if (user.getSmscount() <= 0) {
            log.warn(user.getPhoneuserid() + ": sms count is exhausted");
            return false;
        }
        user.setSmscount(user.getSmscount() - 1);
        return true;
    }

    private void sendSMSExhaustedJMS(User user) {
        if (SMS_EXHAUSTED_JMS_TOPIC.equals(data.getEventtype())
                || IRemoteConstantDefine.REMAINING_NUMBER.equals(data.getEventtype())) {
            return;
        }
        if (user.getSmscount() != SMS_COUNT_EXHAUSTED) {
            return;
        }

        JSONObject jsonObject = new JSONObject();
        jsonObject.put("phonenumbers", new String[][]{{user.getCountrycode(), user.getPhonenumber()}});
        jsonObject.put("eventtype", IRemoteConstantDefine.SMS_RUN_OUT);
        jsonObject.put("platform", data.getPlatform());
        JMSUtil.commitMessage(jsonObject.toJSONString(), SMS_EXHAUSTED_JMS_TOPIC);
    }
}
