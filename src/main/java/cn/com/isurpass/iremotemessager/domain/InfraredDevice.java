package cn.com.isurpass.iremotemessager.domain;

import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;

@Entity
@Table(name="infrareddevice")
public class InfraredDevice {
	
	private int infrareddeviceid;
	private String deviceid;
	private String applianceid ;
	private String devicetype;
	private String name ;
	private String productorid;
	private String statuses;
//	private String majortype = IRemoteConstantDefine.DEVICE_MAJORTYPE_INFRARED;

	@Id    
	@GeneratedValue(strategy = GenerationType.IDENTITY)   
	@GenericGenerator(name = "generator", strategy = "increment")   
    @Column(name = "infrareddeviceid")  
	public int getInfrareddeviceid() {
		return infrareddeviceid;
	}
	public void setInfrareddeviceid(int infrareddeviceid) {
		this.infrareddeviceid = infrareddeviceid;
	}
    @Column(name = "deviceid")  
	public String getDeviceid() {
		return deviceid;
	}
	public void setDeviceid(String deviceid) {
		this.deviceid = deviceid;
	}
    @Column(name = "applianceid")  
	public String getApplianceid() {
		return applianceid;
	}
	public void setApplianceid(String applianceid) {
		this.applianceid = applianceid;
	}
    @Column(name = "devicetype")  
	public String getDevicetype() {
		return devicetype;
	}
	public void setDevicetype(String devicetype) {
		this.devicetype = devicetype;
	}
    @Column(name = "name")  
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}

	public String getProductorid() {
		return productorid;
	}
	public void setProductorid(String productorid) {
		this.productorid = productorid;
	}

	public String getStatuses() {
		return statuses;
	}

	public void setStatuses(String statuses) {
		this.statuses = statuses;
	}
/*
	public String getMajortype() {
		return majortype;
	}

	public void setMajortype(String majortype) {
		this.majortype = majortype;
	}*/
}
