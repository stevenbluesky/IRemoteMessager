package cn.com.isurpass.iremotemessager.domain;

import com.alibaba.fastjson.annotation.JSONField;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import java.util.Date;

@Entity
@Table(name = "msg_eventgroupevent")
public class MsgEventGroupEvent {

    private Integer msgeventgroupeventid;
    private Integer platform;
    private String eventcode;
    private Date createtime;
    private MsgEventType msgEventType;
    private MsgEventGroup msgEventGroup;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @GenericGenerator(name = "generator", strategy = "increment")
    public Integer getMsgeventgroupeventid() {
        return msgeventgroupeventid;
    }

    public void setMsgeventgroupeventid(Integer msgeventgroupeventid) {
        this.msgeventgroupeventid = msgeventgroupeventid;
    }

    public Integer getPlatform() {
        return platform;
    }

    public void setPlatform(Integer platform) {
        this.platform = platform;
    }

    public String getEventcode() {
        return eventcode;
    }

    public void setEventcode(String eventcode) {
        this.eventcode = eventcode;
    }

    public Date getCreatetime() {
        return createtime;
    }

    public void setCreatetime(Date createtime) {
        this.createtime = createtime;
    }

    @JSONField(serialize = false)
    @ManyToOne(targetEntity=MsgEventType.class,cascade={CascadeType.DETACH})
    @JoinColumn(name="msgeventtypeid",referencedColumnName="msgeventtypeid",nullable=true)
    public MsgEventType getMsgEventType() {
        return msgEventType;
    }

    public void setMsgEventType(MsgEventType msgEventType) {
        this.msgEventType = msgEventType;
    }

    @JSONField(serialize = false)
    @ManyToOne(targetEntity=MsgEventGroup.class,cascade={CascadeType.DETACH})
    @JoinColumn(name="msgeventgroupid",referencedColumnName="msgeventgroupid",nullable=true)
    public MsgEventGroup getMsgEventGroup() {
        return msgEventGroup;
    }

    public void setMsgEventGroup(MsgEventGroup msgEventGroup) {
        this.msgEventGroup = msgEventGroup;
    }
}
