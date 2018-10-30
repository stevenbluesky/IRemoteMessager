package cn.com.isurpass.iremotemessager.vo;

import java.util.List;

import cn.com.isurpass.iremotemessager.domain.User;

public class SendResult {

	private List<User> phoneusers;
	private int resultcode;
	private String errmsg;
	
	public List<User> getPhoneusers() {
		return phoneusers;
	}
	public void setPhoneusers(List<User> phoneusers) {
		this.phoneusers = phoneusers;
	}
	public int getResultcode() {
		return resultcode;
	}
	public void setResultcode(int resultcode) {
		this.resultcode = resultcode;
	}
	public String getErrmsg() {
		return errmsg;
	}
	public void setErrmsg(String errmsg) {
		this.errmsg = errmsg;
	}
	
	
}
