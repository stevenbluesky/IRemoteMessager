package cn.com.isurpass.iremotemessager.domain;

import com.alibaba.fastjson.annotation.JSONField;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import java.util.Date;
import java.util.List;

@Entity
@Table(name = "msg_eventtype")
public class MsgEventType {

    private Integer msgeventtypeid;
    private String eventtypename;
    private String eventcode;
    private String decription;
    private Date createtime;
    private List<MsgEventGroupEvent> msgEventGroupEvents;
    private List<MsgContentTemplate> msgContentTemplates;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @GenericGenerator(name = "generator", strategy = "increment")
    public Integer getMsgeventtypeid() {
        return msgeventtypeid;
    }

    public void setMsgeventtypeid(Integer msgeventtypeid) {
        this.msgeventtypeid = msgeventtypeid;
    }

    public String getEventtypename() {
        return eventtypename;
    }

    public void setEventtypename(String eventtypename) {
        this.eventtypename = eventtypename;
    }

    public String getEventcode() {
        return eventcode;
    }

    public void setEventcode(String eventcode) {
        this.eventcode = eventcode;
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
    @OneToMany(targetEntity=MsgEventGroupEvent.class,cascade={CascadeType.ALL,CascadeType.REMOVE},orphanRemoval=true,fetch=FetchType.LAZY,mappedBy="msgEventType")
    public List<MsgEventGroupEvent> getMsgEventGroupEvents() {
        return msgEventGroupEvents;
    }

    public void setMsgEventGroupEvents(List<MsgEventGroupEvent> msgEventGroupEvents) {
        this.msgEventGroupEvents = msgEventGroupEvents;
    }

    @JSONField(serialize = false)
    @OneToMany(targetEntity=MsgContentTemplate.class,cascade={CascadeType.ALL,CascadeType.REMOVE},orphanRemoval=true,fetch=FetchType.LAZY,mappedBy="msgEventType")
    public List<MsgContentTemplate> getMsgContentTemplates() {
        return msgContentTemplates;
    }

    public void setMsgContentTemplates(List<MsgContentTemplate> msgContentTemplates) {
        this.msgContentTemplates = msgContentTemplates;
    }
}
