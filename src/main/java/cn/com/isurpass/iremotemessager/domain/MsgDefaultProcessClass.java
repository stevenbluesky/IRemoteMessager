package cn.com.isurpass.iremotemessager.domain;

import com.alibaba.fastjson.annotation.JSONField;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;

@Entity
@Table(name = "msg_defaultprocessclass")
public class MsgDefaultProcessClass {

    private Integer msgdefaultprocessclassid;
    private Integer platform;
    private Integer type;
    private Integer subtype;
    private MsgProcessClass msgProcessClass;
    private String eventcode;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @GenericGenerator(name = "generator", strategy = "increment")
    public Integer getMsgdefaultprocessclassid() {
        return msgdefaultprocessclassid;
    }

    public void setMsgdefaultprocessclassid(Integer msgdefaultprocessclassid) {
        this.msgdefaultprocessclassid = msgdefaultprocessclassid;
    }

    public Integer getPlatform() {
        return platform;
    }

    public void setPlatform(Integer platform) {
        this.platform = platform;
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

    public String getEventcode() {
        return eventcode;
    }

    public void setEventcode(String eventcode) {
        this.eventcode = eventcode;
    }

    @JSONField(serialize = false)
    @OneToOne(targetEntity = MsgProcessClass.class, cascade = {CascadeType.DETACH},fetch = FetchType.LAZY)
    @JoinColumn(name = "msgprocessclassid",referencedColumnName = "msgprocessclassid")
    public MsgProcessClass getMsgProcessClass() {
        return msgProcessClass;
    }

    public void setMsgProcessClass(MsgProcessClass msgProcessClass) {
        this.msgProcessClass = msgProcessClass;
    }
}
