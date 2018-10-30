package cn.com.isurpass.iremotemessager.vo;

import java.util.List;

import cn.com.isurpass.iremotemessager.domain.User;
import cn.com.isurpass.iremotemessager.jpush.vo.Payload;

public class JPushNotificationData {

	private String language;
	private List<User> phoneusers;
	private List<String> aliaseslist;
	private String[] aliases;
	private Payload payload;
	private String iossound;
	private Integer androidbundlerid;
	
	public String getLanguage() {
		return language;
	}
	public void setLanguage(String language) {
		this.language = language;
	}
	public List<User> getPhoneusers() {
		return phoneusers;
	}
	public void setPhoneusers(List<User> phoneusers) {
		this.phoneusers = phoneusers;
	}
	public String[] getAliases() {
		if ( aliases == null && aliaseslist != null )
			return aliaseslist.toArray(new String[0]);
		return aliases;
	}
	public void setAliases(String[] aliases) {
		this.aliases = aliases;
	}
	public String getIossound() {
		return iossound;
	}
	public void setIossound(String iossound) {
		this.iossound = iossound;
	}
	public Integer getAndroidbundlerid() {
		return androidbundlerid;
	}
	public void setAndroidbundlerid(Integer androidbundlerid) {
		this.androidbundlerid = androidbundlerid;
	}
	public List<String> getAliaseslist()
	{
		return aliaseslist;
	}
	public void setAliaseslist(List<String> aliaseslist)
	{
		this.aliaseslist = aliaseslist;
	}
	public Payload getPayload()
	{
		return payload;
	}
	public void setPayload(Payload payload)
	{
		this.payload = payload;
	}

	
}
