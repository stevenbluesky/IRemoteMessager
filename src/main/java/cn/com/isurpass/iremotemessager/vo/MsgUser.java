package cn.com.isurpass.iremotemessager.vo;

import cn.com.isurpass.iremotemessager.domain.User;

public class MsgUser 
{
	private int phoneuserid;
	private String contrycode;
	private String phonenumber;
	private String mail;
	private String alias;
	private String language;
	private String name;
	private User phoneuser ;
	
	public int getPhoneuserid() {
		return phoneuserid;
	}
	public void setPhoneuserid(int phoneuserid) {
		this.phoneuserid = phoneuserid;
	}
	public String getContrycode() {
		return contrycode;
	}
	public void setContrycode(String contrycode) {
		this.contrycode = contrycode;
	}
	public String getPhonenumber() {
		return phonenumber;
	}
	public void setPhonenumber(String phonenumber) {
		this.phonenumber = phonenumber;
	}
	public String getMail() {
		return mail;
	}
	public void setMail(String mail) {
		this.mail = mail;
	}
	public String getAlias() {
		return alias;
	}
	public void setAlias(String alias) {
		this.alias = alias;
	}
	public String getLanguage() {
		return language;
	}
	public void setLanguage(String language) {
		this.language = language;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public User getPhoneuser() {
		return phoneuser;
	}
	public void setPhoneuser(User phoneuser) {
		this.phoneuser = phoneuser;
	}

	
}
