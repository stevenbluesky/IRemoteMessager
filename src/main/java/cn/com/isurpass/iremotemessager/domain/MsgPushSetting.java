package cn.com.isurpass.iremotemessager.domain;

import com.alibaba.fastjson.annotation.JSONField;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import java.util.Date;

@Entity
@Table(name = "msg_pushsetting")
public class MsgPushSetting {

    private Integer msgpushsettingid;
    private Integer platform;
    private MsgProcessClass msgPushTargetDecision;
    private MsgProcessClass msgPushMethod;
    private Date createtime;
    private MsgEventGroup msgEventGroup;
    private MsgPushSettingDtl msgPushSettingDtl;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @GenericGenerator(name = "generator", strategy = "increment")
    public Integer getMsgpushsettingid() {
        return msgpushsettingid;
    }

    public void setMsgpushsettingid(Integer msgpushsettingid) {
        this.msgpushsettingid = msgpushsettingid;
    }


    public Integer getPlatform() {
        return platform;
    }

    public void setPlatform(Integer platform) {
        this.platform = platform;
    }

    public java.util.Date getCreatetime() {
        return createtime;
    }

    public void setCreatetime(Date createtime) {
        this.createtime = createtime;
    }

    @JSONField(serialize = false)
    @ManyToOne(targetEntity=MsgProcessClass.class,cascade={CascadeType.DETACH})
    @JoinColumn(name="msgpushtargetdecisionid",referencedColumnName="msgprocessclassid",nullable=true)
    public MsgProcessClass getMsgPushTargetDecision() {
        return msgPushTargetDecision;
    }

    public void setMsgPushTargetDecision(MsgProcessClass msgPushTargetDecision) {
        this.msgPushTargetDecision = msgPushTargetDecision;
    }

    @JSONField(serialize = false)
    @ManyToOne(targetEntity=MsgProcessClass.class,cascade={CascadeType.DETACH})
    @JoinColumn(name="msgpushmethodid",referencedColumnName="msgprocessclassid",nullable=true)
    public MsgProcessClass getMsgPushMethod() {
        return msgPushMethod;
    }

    public void setMsgPushMethod(MsgProcessClass msgPushMethod) {
        this.msgPushMethod = msgPushMethod;
    }

    @JSONField(serialize = false)
    @OneToOne(targetEntity = MsgEventGroup.class,cascade = {CascadeType.REMOVE})
    @JoinColumn(name = "msgeventgroupid",referencedColumnName = "msgeventgroupid")
    public MsgEventGroup getMsgEventGroup() {
        return msgEventGroup;
    }

    public void setMsgEventGroup(MsgEventGroup msgEventGroup) {
        this.msgEventGroup = msgEventGroup;
    }

    @JSONField(serialize = false)
    @OneToOne(targetEntity=MsgPushSettingDtl.class,cascade={CascadeType.ALL,CascadeType.REMOVE},orphanRemoval=true,fetch=FetchType.LAZY,mappedBy="msgPushSetting")
    public MsgPushSettingDtl getMsgPushSettingDtl() {
        return msgPushSettingDtl;
    }

    public void setMsgPushSettingDtl(MsgPushSettingDtl msgPushSettingDtl) {
        this.msgPushSettingDtl = msgPushSettingDtl;
    }
}
