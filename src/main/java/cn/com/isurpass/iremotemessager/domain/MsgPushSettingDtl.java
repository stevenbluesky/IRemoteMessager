package cn.com.isurpass.iremotemessager.domain;

import com.alibaba.fastjson.annotation.JSONField;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;

@Entity
@Table(name = "msg_pushsettingdtl")
public class MsgPushSettingDtl {

    private Integer msgpushsettingdtlid;
    private Integer type;
    private Integer subtyp;
    private MsgPushSetting msgPushSetting;
    private MsgProcessClass msgProcessClass;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @GenericGenerator(name = "generator", strategy = "increment")
    public Integer getMsgpushsettingdtlid() {
        return msgpushsettingdtlid;
    }

    public void setMsgpushsettingdtlid(Integer msgpushsettingdtlid) {
        this.msgpushsettingdtlid = msgpushsettingdtlid;
    }


    public Integer getType() {
        return type;
    }

    public void setType(Integer type) {
        this.type = type;
    }


    public Integer getSubtyp() {
        return subtyp;
    }

    public void setSubtyp(Integer subtyp) {
        this.subtyp = subtyp;
    }

    @JSONField(serialize = false)
    @OneToOne(targetEntity = MsgPushSetting.class,cascade = {CascadeType.REMOVE})
    @JoinColumn(name = "msgpushsettingid",referencedColumnName = "msgpushsettingid")
    public MsgPushSetting getMsgPushSetting() {
        return msgPushSetting;
    }

    public void setMsgPushSetting(MsgPushSetting msgPushSetting) {
        this.msgPushSetting = msgPushSetting;
    }

    @JSONField(serialize = false)
    @OneToOne(targetEntity = MsgProcessClass.class,cascade = {CascadeType.REMOVE})
    @JoinColumn(name = "msgprocessclassid",referencedColumnName = "msgprocessclassid")
    public MsgProcessClass getMsgProcessClass() {
        return msgProcessClass;
    }

    public void setMsgProcessClass(MsgProcessClass msgProcessClass) {
        this.msgProcessClass = msgProcessClass;
    }
}
