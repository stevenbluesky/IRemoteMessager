package cn.com.isurpass.iremotemessager.common.sms;

public interface ISmsSender {

	int sendSMS(String countrycode ,String phonenumber , String message);
}
