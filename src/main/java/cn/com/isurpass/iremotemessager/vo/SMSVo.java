package cn.com.isurpass.iremotemessager.vo;

import cn.com.isurpass.iremotemessager.common.constant.IRemoteConstantDefine;

public class SMSVo {

	private String countrycode = IRemoteConstantDefine.DEFAULT_COUNTRYCODE;
	private String phonenumber ;
	private String message ;
	private int platform;
	private boolean sendoverseasms = false ;

	public SMSVo() {
	}

	public SMSVo(String countrycode, String phonenumber, String message, int platform, boolean sendoverseasms)
	{
		super();
		this.countrycode = countrycode;
		this.phonenumber = phonenumber;
		this.message = message;
		this.platform = platform;
		this.sendoverseasms = sendoverseasms;
	}

	public SMSVo(String countrycode , String phonenumber, String message , int platform) {
		super();
		this.countrycode = countrycode ;
		this.phonenumber = phonenumber;
		this.message = message;
		this.platform = platform;
	}

	public String getPhonenumber() {
		return phonenumber;
	}
	public void setPhonenumber(String phonenumber) {
		this.phonenumber = phonenumber;
	}
	public String getMessage()
	{	
		return message;
	}
	public void setMessage(String message) {
		this.message = message;
	}
	public String getCountrycode() {
		return countrycode;
	}
	public int getPlatform() {
		return platform;
	}

	public boolean isSendoverseasms()
	{
		return sendoverseasms;
	}

	public void setCountrycode(String countrycode) {
		this.countrycode = countrycode;
	}

	public void setPlatform(int platform) {
		this.platform = platform;
	}

	public void setSendoverseasms(boolean sendoverseasms) {
		this.sendoverseasms = sendoverseasms;
	}
}
