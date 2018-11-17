package cn.com.isurpass.iremotemessager.framework;

import cn.com.isurpass.iremotemessager.vo.EventData;
import cn.com.isurpass.iremotemessager.vo.SendResult;
import cn.com.isurpass.iremotemessager.vo.SmsData;

import java.util.List;

public interface ISmsMessageSender extends IMessageSender<SmsData> {

    int sendSMS(String countrycode ,String phonenumber , String message);

    @Override
    default List<SendResult> send(EventData data, SmsData smsData){
        String[][] phonenumbers = smsData.getPhonenumbers();

        for (int i = 0; i < phonenumbers.length; i++) {
            String countryCode = phonenumbers[i][0];
            String phoneNumber = phonenumbers[i][1];
            String message = smsData.getMessage();

            sendSMS(countryCode, phoneNumber, message);
        }
        return null;
    }
}
