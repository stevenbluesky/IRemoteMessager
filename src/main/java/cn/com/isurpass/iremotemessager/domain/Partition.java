package cn.com.isurpass.iremotemessager.domain;

import com.alibaba.fastjson.annotation.JSONField;
import org.hibernate.annotations.BatchSize;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name="securitypartition")
public class Partition {
	private Integer partitionid;
	private String name;
	private ZWaveDevice zwavedevice ;
	private Integer dscpartitionid;
	private int status;
	private int armstatus;
	private int warningstatus;
	private Integer delay;
	private List<ZWaveSubDevice> zWaveSubDevices;
	private Integer phoneuserid;
	private List<ZWaveDevice> zWaveDevices;
	private String password;

	public Partition() {
		super();
	}

	public Partition(String name, int dscpartitionid) {
		super();
		this.name = name;
		this.dscpartitionid = dscpartitionid;
	}

	public Partition(String name, ZWaveDevice zwavedevice, int dscpartitionid) {
		super();
		this.name = name;
		this.zwavedevice = zwavedevice;
		this.dscpartitionid = dscpartitionid;
	}

	public Partition(String name, ZWaveDevice zwavedevice, int dscpartitionid, int status, int armstatus,
                     int warningstatus) {
		super();
		this.name = name;
		this.zwavedevice = zwavedevice;
		this.dscpartitionid = dscpartitionid;
		this.status = status;
		this.armstatus = armstatus;
		this.warningstatus = warningstatus;
	}

	public Partition(String name, ZWaveDevice zwavedevice, int dscpartitionid, int status, int armstatus,
                     int warningstatus, Integer delay, String password) {
		super();
		this.name = name;
		this.zwavedevice = zwavedevice;
		this.dscpartitionid = dscpartitionid;
		this.status = status;
		this.armstatus = armstatus;
		this.warningstatus = warningstatus;
		this.delay = delay;
		this.password = password;
	}

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@GenericGenerator(name = "generator", strategy = "increment")
	public Integer getPartitionid() {
		return partitionid;
	}

	public void setPartitionid(Integer partitionid) {
		this.partitionid = partitionid;
	}

	public int getStatus() {
		return status;
	}

	public void setStatus(int status) {
		this.status = status;
	}

	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	@JSONField(serialize = false)
	@ManyToOne(targetEntity=ZWaveDevice.class,cascade={CascadeType.DETACH},fetch= FetchType.LAZY)
	@JoinColumn(name="dsczwavedeviceid",referencedColumnName="zwavedeviceid",nullable=true)
	@BatchSize(size=300)
	public ZWaveDevice getZwavedevice() {
		return zwavedevice;
	}
	public void setZwavedevice(ZWaveDevice zwavedevice) {
		this.zwavedevice = zwavedevice;
	}
	@JSONField(serialize = false)
	@OneToMany(targetEntity=ZWaveSubDevice.class,cascade={CascadeType.ALL, CascadeType.REMOVE},orphanRemoval=true,fetch= FetchType.LAZY,mappedBy="securitypartition")
	@BatchSize(size=300)
	public List<ZWaveSubDevice> getzWaveSubDevices() {
		return zWaveSubDevices;
	}
	public void setzWaveSubDevices(List<ZWaveSubDevice> zWaveSubDevices) {
		this.zWaveSubDevices = zWaveSubDevices;
	}

	public Integer getPhoneuserid() {
		return phoneuserid;
	}

	public void setPhoneuserid(Integer phoneuserid) {
		this.phoneuserid = phoneuserid;
	}

	public Integer getDscpartitionid() {
		return dscpartitionid;
	}

	public void setDscpartitionid(Integer dscpartitionid) {
		this.dscpartitionid = dscpartitionid;
	}

	public int getArmstatus() {
		return armstatus;
	}
	public void setArmstatus(int armstatus) {
		this.armstatus = armstatus;
	}
	public int getWarningstatus() {
		return warningstatus;
	}
	public void setWarningstatus(int warningstatus) {
		this.warningstatus = warningstatus;
	}

	public Integer getDelay() {
		return delay;
	}

	public void setDelay(Integer delay) {
		this.delay = delay;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	

}
