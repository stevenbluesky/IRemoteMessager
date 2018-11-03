package cn.com.isurpass.iremotemessager.domain;

import com.alibaba.fastjson.annotation.JSONField;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import java.util.Date;
import java.util.List;

@Entity
@Table(name = "msg_processclass")
public class MsgProcessClass {

    private Integer msgprocessclassid;
    private String name;
    private String classname;
    private Integer type;
    private Integer subtype;
    private String decription;
    private Date createtime;
    private List<MsgPushSetting> msgPushTargetSettings;
    private List<MsgPushSetting> msgPushMethodSettings;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @GenericGenerator(name = "generator", strategy = "increment")
    public Integer getMsgprocessclassid() {
        return msgprocessclassid;
    }

    public void setMsgprocessclassid(Integer msgprocessclassid) {
        this.msgprocessclassid = msgprocessclassid;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getClassname() {
        return classname;
    }

    public void setClassname(String classname) {
        this.classname = classname;
    }

    public Integer getType() {
        return type;
    }

    public void setType(Integer type) {
        this.type = type;
    }

    public Integer getSubtype() {
        return subtype;
    }

    public void setSubtype(Integer subtype) {
        this.subtype = subtype;
    }

    public String getDecription() {
        return decription;
    }

    public void setDecription(String decription) {
        this.decription = decription;
    }

    public Date getCreatetime() {
        return createtime;
    }

    public void setCreatetime(Date createtime) {
        this.createtime = createtime;
    }

    @JSONField(serialize = false)
    @OneToMany(targetEntity=MsgPushSetting.class,cascade={CascadeType.ALL,CascadeType.REMOVE},orphanRemoval=true,fetch=FetchType.LAZY,mappedBy="msgPushTargetDecision")
    public List<MsgPushSetting> getMsgPushTargetSettings() {
        return msgPushTargetSettings;
    }

    public void setMsgPushTargetSettings(List<MsgPushSetting> msgPushTargetSettings) {
        this.msgPushTargetSettings = msgPushTargetSettings;
    }
    @JSONField(serialize = false)
    @OneToMany(targetEntity=MsgPushSetting.class,cascade={CascadeType.ALL,CascadeType.REMOVE},orphanRemoval=true,fetch=FetchType.LAZY,mappedBy="msgPushMethod")
    public List<MsgPushSetting> getMsgPushMethodSettings() {
        return msgPushMethodSettings;
    }

    public void setMsgPushMethodSettings(List<MsgPushSetting> msgPushMethodSettings) {
        this.msgPushMethodSettings = msgPushMethodSettings;
    }
}
