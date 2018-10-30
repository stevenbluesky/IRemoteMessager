package cn.com.isurpass.iremotemessager.domain;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "phoneuser")
public class User 
{
	@Id
	private int phoneuserid;
	private String countrycode;
	private String phonenumber;
	private String mail;
	private String alias;
	private String language;
	private String name;
	private Integer familyid;
	
	public int getPhoneuserid() {
		return phoneuserid;
	}
	public void setPhoneuserid(int phoneuserid) {
		this.phoneuserid = phoneuserid;
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
	public String getCountrycode()
	{
		return countrycode;
	}
	public void setCountrycode(String countrycode)
	{
		this.countrycode = countrycode;
	}
	public Integer getFamilyid()
	{
		return familyid;
	}
	public void setFamilyid(Integer familyid)
	{
		this.familyid = familyid;
	}
	
	
}
