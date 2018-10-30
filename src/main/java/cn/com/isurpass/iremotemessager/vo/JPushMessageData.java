package cn.com.isurpass.iremotemessager.vo;

import java.util.List;

import cn.com.isurpass.iremotemessager.domain.User;
import cn.com.isurpass.iremotemessager.jpush.vo.Payload;

public class JPushMessageData {

	private List<User> phoneusers;
	private String[] aliases;
	private Payload payload;
	public List<User> getPhoneusers() {
		return phoneusers;
	}
	public void setPhoneusers(List<User> phoneusers) {
		this.phoneusers = phoneusers;
	}
	public String[] getAliases() {
		return aliases;
	}
	public void setAliases(String[] aliases) {
		this.aliases = aliases;
	}
	public Payload getPayload() {
		return payload;
	}
	public void setPayload(Payload payload) {
		this.payload = payload;
	}

	
	
}
