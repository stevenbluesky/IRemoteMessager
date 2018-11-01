package cn.com.isurpass.iremotemessager.domain;

import cn.com.isurpass.iremotemessager.common.constant.IRemoteConstantDefine;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;

@Entity
@Table(name="camera")
public class Camera {

	private int cameraid;
	private String deviceid;
	private String name;
	private String applianceid;
	private String devicetype;
	private String productorid;
	private String applianceuuid;
	private int enablestatus;
	private int status;
	private String warningstatuses;
	private int armstatus = IRemoteConstantDefine.PHONEUSER_ARM_STATUS_ARM;
	private String majortype = IRemoteConstantDefine.DEVICE_MAJORTYPE_CAMERA;
	private Integer partitionid;

	@Id    
	@GeneratedValue(strategy = GenerationType.IDENTITY)   
	@GenericGenerator(name = "generator", strategy = "increment")   
    @Column(name = "cameraid")  
	public int getCameraid() {
		return cameraid;
	}
	public void setCameraid(int cameraid) {
		this.cameraid = cameraid;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getApplianceid() {
		return applianceid;
	}
	public void setApplianceid(String applianceid) {
		this.applianceid = applianceid;
	}
	public String getDevicetype() {
		return devicetype;
	}
	public void setDevicetype(String devicetype) {
		this.devicetype = devicetype;
	}
	public String getProductorid() {
		return productorid;
	}
	public void setProductorid(String productorid) {
		this.productorid = productorid;
	}
	public String getApplianceuuid() {
		return applianceuuid;
	}
	public void setApplianceuuid(String applianceuuid) {
		this.applianceuuid = applianceuuid;
	}
	public String getDeviceid() {
		return deviceid;
	}
	public void setDeviceid(String deviceid) {
		this.deviceid = deviceid;
	}
	public int getEnablestatus() {
		return enablestatus;
	}
	public void setEnablestatus(int enablestatus) {
		this.enablestatus = enablestatus;
	}
	public int getStatus() {
		return status;
	}
	public void setStatus(int status) {
		this.status = status;
	}
	@Transient
	public String getMajortype() {
		return majortype;
	}
	public String getWarningstatuses() {
		return warningstatuses;
	}
	public void setWarningstatuses(String warningstatuses) {
		this.warningstatuses = warningstatuses;
	}

	public int getArmstatus() {
		return armstatus;
	}

	public void setArmstatus(int armstatus) {
		this.armstatus = armstatus;
	}
	public Integer getPartitionid() {
		return partitionid;
	}
	public void setPartitionid(Integer partitionid) {
		this.partitionid = partitionid;
	}
	

}
