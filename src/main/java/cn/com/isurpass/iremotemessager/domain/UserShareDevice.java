package cn.com.isurpass.iremotemessager.domain;

import com.alibaba.fastjson.annotation.JSONField;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;

@Entity
@Table(name="usersharedevice")
public class UserShareDevice {

    private int usersharedeviceid;
    private UserShare userShare;
    private Integer zwavedeviceid;
    private Integer infrareddeviceid;
    private Integer cameraid;
//    private ZWaveDeviceShare zwavedeviceshare;

    public UserShareDevice() {
    }

    public UserShareDevice(UserShare userShare, Integer zwavedeviceid, Integer infrareddeviceid, Integer cameraid) {
        this.userShare = userShare;
        this.zwavedeviceid = zwavedeviceid;
        this.infrareddeviceid = infrareddeviceid;
        this.cameraid = cameraid;
    }

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @GenericGenerator(name = "generator", strategy = "increment")
    @Column(name = "usersharedeviceid")
    public int getUsersharedeviceid() {
        return usersharedeviceid;
    }

    public void setUsersharedeviceid(int usersharedeviceid) {
        this.usersharedeviceid = usersharedeviceid;
    }

//    @JSON(serialize=false)
    @JSONField(serialize = false)
    @ManyToOne(targetEntity= UserShare.class,cascade={CascadeType.DETACH})
    @JoinColumn(name="usershareid",referencedColumnName="shareid",nullable=false)
    public UserShare getUserShare() {
        return userShare;
    }

    public void setUserShare(UserShare userShare) {
        this.userShare = userShare;
    }

    public Integer getZwavedeviceid() {
        return zwavedeviceid;
    }

    public void setZwavedeviceid(Integer zwavedeviceid) {
        this.zwavedeviceid = zwavedeviceid;
    }

    public Integer getInfrareddeviceid() {
        return infrareddeviceid;
    }

    public void setInfrareddeviceid(Integer infrareddeviceid) {
        this.infrareddeviceid = infrareddeviceid;
    }

    /*@JSON(serialize=false)
    @JSONField(serialize = false)
    @OneToOne(fetch = FetchType.LAZY,cascade={CascadeType.ALL})
    @JoinColumn(name = "zwavedeviceshareid")
    public ZWaveDeviceShare getZwavedeviceshare()
    {
        return zwavedeviceshare;
    }

    public void setZwavedeviceshare(ZWaveDeviceShare zwavedeviceshare)
    {
        this.zwavedeviceshare = zwavedeviceshare;
    }*/

    public Integer getCameraid()
    {
        return cameraid;
    }

    public void setCameraid(Integer cameraid)
    {
        this.cameraid = cameraid;
    }

}
