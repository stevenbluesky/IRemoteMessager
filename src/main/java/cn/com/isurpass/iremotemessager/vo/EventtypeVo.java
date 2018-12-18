package cn.com.isurpass.iremotemessager.vo;

import java.util.Date;

/**
 * @author liwenxiang
 * Date:2018/11/6
 * Time:16:56
 */
public class EventtypeVo {
    private Integer msgeventtypeid;
    private String eventname;
    private String eventcode;
    private Date createtime;
    private String description;
    private Integer msgeventgroupeventid;

    public Integer getMsgeventtypeid() {
        return msgeventtypeid;
    }

    public void setMsgeventtypeid(Integer msgeventtypeid) {
        this.msgeventtypeid = msgeventtypeid;
    }

    public String getEventname() {
        return eventname;
    }

    public void setEventname(String eventname) {
        this.eventname = eventname;
    }

    public String getEventcode() {
        return eventcode;
    }

    public void setEventcode(String eventcode) {
        this.eventcode = eventcode;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Date getCreatetime() {
        return createtime;
    }

    public void setCreatetime(Date createtime) {
        this.createtime = createtime;
    }

    public Integer getMsgeventgroupeventid() {
        return msgeventgroupeventid;
    }

    public void setMsgeventgroupeventid(Integer msgeventgroupeventid) {
        this.msgeventgroupeventid = msgeventgroupeventid;
    }
}
