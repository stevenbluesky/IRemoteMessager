package cn.com.isurpass.iremotemessager.domain;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "msg_contenttemplate")
public class MsgContentTemplate 
{
	@Id
	private int msgcontenttemplateid;
	private int platform;
	private int msgeventtypeid;
	private String eventcode;
	private String language;
	private int type;
	private String contenttemplate;
	
	public int getMsgcontenttemplateid()
	{
		return msgcontenttemplateid;
	}
	public void setMsgcontenttemplateid(int msgcontenttemplateid)
	{
		this.msgcontenttemplateid = msgcontenttemplateid;
	}
	public int getPlatform()
	{
		return platform;
	}
	public void setPlatform(int platform)
	{
		this.platform = platform;
	}
	public int getMsgeventtypeid()
	{
		return msgeventtypeid;
	}
	public void setMsgeventtypeid(int msgeventtypeid)
	{
		this.msgeventtypeid = msgeventtypeid;
	}
	public String getEventcode()
	{
		return eventcode;
	}
	public void setEventcode(String eventcode)
	{
		this.eventcode = eventcode;
	}
	public String getLanguage()
	{
		return language;
	}
	public void setLanguage(String language)
	{
		this.language = language;
	}
	public int getType()
	{
		return type;
	}
	public void setType(int type)
	{
		this.type = type;
	}
	public String getContenttemplate()
	{
		return contenttemplate;
	}
	public void setContenttemplate(String contenttemplate)
	{
		this.contenttemplate = contenttemplate;
	}

}
