package cn.com.isurpass.iremotemessager.domain;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "zwavedevice")
public class ZWaveDevice
{

	@Id
	private int zwavedeviceid;
	private String deviceid;
	private int nuid;
	private String devicetype;
	private String name;
	private Integer status;
	private String statuses;
	private Float fstatus;
	private String warningstatuses;
	private Integer battery = 100;
	private int enablestatus;
	private String productor;
	private int armstatus = 1;
	private Date createtime;
	private Date lastactivetime;

	public int getZwavedeviceid()
	{
		return zwavedeviceid;
	}

	public void setZwavedeviceid(int zwavedeviceid)
	{
		this.zwavedeviceid = zwavedeviceid;
	}

	@Column(name = "deviceid")
	public String getDeviceid()
	{
		return deviceid;
	}

	public void setDeviceid(String deviceid)
	{
		this.deviceid = deviceid;
	}

	@Column(name = "nuid")
	public int getNuid()
	{
		return nuid;
	}

	public void setNuid(int nuid)
	{
		this.nuid = nuid;
	}

	@Column(name = "devicetype")
	public String getDevicetype()
	{
		return devicetype;
	}

	public void setDevicetype(String devicetype)
	{
		this.devicetype = devicetype;
	}

	@Column(name = "name")
	public String getName()
	{
		return name;
	}

	public void setName(String name)
	{
		this.name = name;
	}

	@Column(name = "status")
	public Integer getStatus()
	{
		return status;
	}

	public void setStatus(Integer status)
	{
		this.status = status;
	}

	@Column(name = "battery")
	public Integer getBattery()
	{
		return battery;
	}

	public void setBattery(Integer battery)
	{
		this.battery = battery;
	}

	@Column(name = "statuses")
	public String getStatuses()
	{
		return statuses;
	}

	public void setStatuses(String statuses)
	{
		this.statuses = statuses;
	}

	public int getEnablestatus()
	{
		return enablestatus;
	}

	public void setEnablestatus(int enablestatus)
	{
		this.enablestatus = enablestatus;
	}

	public Float getFstatus()
	{
		return fstatus;
	}

	public void setFstatus(Float fstatus)
	{
		this.fstatus = fstatus;
	}

	public String getWarningstatuses()
	{
		return warningstatuses;
	}

	public void setWarningstatuses(String waringstatuses)
	{
		this.warningstatuses = waringstatuses;
	}

	public String getProductor()
	{
		return productor;
	}

	public void setProductor(String productor)
	{
		this.productor = productor;
	}

	public int getArmstatus()
	{
		return armstatus;
	}

	public void setArmstatus(int armstatus)
	{
		this.armstatus = armstatus;
	}

	public Date getCreatetime()
	{
		return createtime;
	}

	public void setCreatetime(Date createtime)
	{
		this.createtime = createtime;
	}

	public Date getLastactivetime()
	{
		return lastactivetime;
	}

	public void setLastactivetime(Date lastactivetime)
	{
		this.lastactivetime = lastactivetime;
	}

}
