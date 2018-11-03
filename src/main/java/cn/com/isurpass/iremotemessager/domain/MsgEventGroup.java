package cn.com.isurpass.iremotemessager.domain;

import com.alibaba.fastjson.annotation.JSONField;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import java.util.Date;
import java.util.List;

@Entity
@Table(name = "msg_eventgroup")
public class MsgEventGroup {

    private Integer msgeventgroupid;
    private Integer platform;
    private String eventgroupname;
    private String decription;
    private Date createtime;
    private List<MsgEventGroupEvent> msgEventGroupEvents;
    private MsgPushSetting msgPushSetting;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @GenericGenerator(name = "generator", strategy = "increment")
    public Integer getMsgeventgroupid() {
        return msgeventgroupid;
    }

    public void setMsgeventgroupid(Integer msgeventgroupid) {
        this.msgeventgroupid = msgeventgroupid;
    }

    public Integer getPlatform() {
        return platform;
    }

    public void setPlatform(Integer platform) {
        this.platform = platform;
    }

    public String getEventgroupname() {
        return eventgroupname;
    }

    public void setEventgroupname(String eventgroupname) {
        this.eventgroupname = eventgroupname;
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
    @OneToMany(targetEntity=MsgEventGroupEvent.class,cascade={CascadeType.ALL,CascadeType.REMOVE},orphanRemoval=true,fetch=FetchType.LAZY,mappedBy="msgEventGroup")
    public List<MsgEventGroupEvent> getMsgEventGroupEvents() {
        return msgEventGroupEvents;
    }

    public void setMsgEventGroupEvents(List<MsgEventGroupEvent> msgEventGroupEvents) {
        this.msgEventGroupEvents = msgEventGroupEvents;
    }

    @JSONField(serialize = false)
    @OneToOne(targetEntity=MsgPushSetting.class,cascade={CascadeType.ALL,CascadeType.REMOVE},orphanRemoval=true,fetch=FetchType.LAZY,mappedBy="msgEventGroup")
    public MsgPushSetting getMsgPushSetting() {
        return msgPushSetting;
    }

    public void setMsgPushSetting(MsgPushSetting msgPushSetting) {
        this.msgPushSetting = msgPushSetting;
    }
}
