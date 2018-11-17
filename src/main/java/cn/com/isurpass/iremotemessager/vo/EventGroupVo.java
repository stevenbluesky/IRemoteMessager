package cn.com.isurpass.iremotemessager.vo;

/**
 * @author liwenxiang
 * Date:2018/11/16
 * Time:10:05
 */
public class EventGroupVo {
    private Integer msgeventgroupid;
    private Integer platform;
    private String eventgroupname;
    private String description;
    private String eventliststr;

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

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getEventliststr() {
        return eventliststr;
    }

    public void setEventliststr(String eventliststr) {
        this.eventliststr = eventliststr;
    }
}
