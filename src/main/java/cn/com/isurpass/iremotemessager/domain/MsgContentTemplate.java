package cn.com.isurpass.iremotemessager.domain;

import com.alibaba.fastjson.annotation.JSONField;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;

@Entity
@Table(name = "msg_contenttemplate")
public class MsgContentTemplate 
{
	private Integer msgcontenttemplateid;
	private Integer platform;
	private String eventcode;
	private String language;
	private Integer type;
	private String contenttemplate;
	private MsgEventType msgEventType;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@GenericGenerator(name = "generator", strategy = "increment")
	public Integer getMsgcontenttemplateid()
	{
		return msgcontenttemplateid;
	}
	public void setMsgcontenttemplateid(Integer msgcontenttemplateid)
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

	@JSONField(serialize = false)
	@OneToOne(targetEntity = MsgEventType.class,cascade = {CascadeType.REMOVE})
	@JoinColumn(name = "msgeventtypeid",referencedColumnName = "msgeventtypeid")
	public MsgEventType getMsgEventType() {
		return msgEventType;
	}

	public void setMsgEventType(MsgEventType msgEventType) {
		this.msgEventType = msgEventType;
	}
}
