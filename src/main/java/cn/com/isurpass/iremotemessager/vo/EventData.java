package cn.com.isurpass.iremotemessager.vo;

import com.alibaba.fastjson.JSONObject;

public class EventData 
{
	private String eventtype;
	private int platform ;
	private JSONObject eventparameters;
	private JSONObject domainobjects;
	
	public String getEventtype() {
		return eventtype;
	}
	public void setEventtype(String eventtype) {
		this.eventtype = eventtype;
	}
	public int getPlatform() {
		return platform;
	}
	public void setPlatform(int platform) {
		this.platform = platform;
	}
	public JSONObject getEventparameters() {
		return eventparameters;
	}
	public void setEventparameters(JSONObject eventparameters) {
		this.eventparameters = eventparameters;
	}
	public JSONObject getDomainobjects() {
		return domainobjects;
	}
	public void setDomainobjects(JSONObject domainobjects) {
		this.domainobjects = domainobjects;
	}
	
	
}
