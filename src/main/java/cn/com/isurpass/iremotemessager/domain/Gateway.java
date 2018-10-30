package cn.com.isurpass.iremotemessager.domain;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;


@Entity
@Table(name = "remote")
public class Gateway implements Serializable
{
	private static final long serialVersionUID = 1L;
	@Id
	private String deviceid;
	private Integer phoneuserid;
	private String phonenumber;
	private String name;
	private int status;
	private Date createtime;
	private Date lastupdatetime;
	private int platform;
	private int network;
	private int networkintensity;
	private int powertype;
	private Integer battery = 100;
	private int remotetype;

	public String getDeviceid()
	{
		return deviceid;
	}

	public void setDeviceid(String deviceid)
	{
		this.deviceid = deviceid;
	}

	@Column(name = "phonenumber")
	public String getPhonenumber()
	{
		return phonenumber;
	}

	public void setPhonenumber(String phonenumber)
	{
		this.phonenumber = phonenumber;
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

	@Column(name = "createtime")
	public Date getCreatetime()
	{
		return createtime;
	}

	public void setCreatetime(Date createtime)
	{
		this.createtime = createtime;
	}

	@Column(name = "lastupdatetime")
	public Date getLastupdatetime()
	{
		return lastupdatetime;
	}

	public void setLastupdatetime(Date lastupdatetime)
	{
		this.lastupdatetime = lastupdatetime;
	}

	@Column(name = "status")
	public synchronized int getStatus()
	{
		return status;
	}

	public synchronized void setStatus(int status)
	{
		this.status = status;
	}

	@Column(name = "phoneuserid")
	public Integer getPhoneuserid()
	{
		return phoneuserid;
	}

	public void setPhoneuserid(Integer phoneuserid)
	{
		this.phoneuserid = phoneuserid;
	}

	@Column(name = "platform")
	public int getPlatform()
	{
		return platform;
	}

	public void setPlatform(int platform)
	{
		this.platform = platform;
	}

	public int getNetwork()
	{
		return network;
	}

	public void setNetwork(int network)
	{
		this.network = network;
	}

	public int getNetworkintensity()
	{
		return networkintensity;
	}

	public void setNetworkintensity(int networkintensity)
	{
		this.networkintensity = networkintensity;
	}

	public int getPowertype()
	{
		return powertype;
	}

	public void setPowertype(int powertype)
	{
		this.powertype = powertype;
	}

	public Integer getBattery()
	{
		return battery;
	}

	public void setBattery(Integer battery)
	{
		this.battery = battery;
	}

	public int getRemotetype()
	{
		return remotetype;
	}

	public void setRemotetype(int remotetype)
	{
		this.remotetype = remotetype;
	}

}
