package cn.com.isurpass.iremotemessager.vo;

/**
 * @author liwenxiang
 * Date:2018/11/16
 * Time:10:05
 */
public class PushSettingVo {
    private Integer msgpushsettingid;
    private Integer platform;
    private String eventgroupname;
    private String pushtargetclass;
    private String pushmethodclass;
    private String pushclass;
    private String apppushclass;
    private String smspushclass;
    private String emailpushclass;

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

    public String getEventgroupname() {
        return eventgroupname;
    }

    public void setEventgroupname(String eventgroupname) {
        this.eventgroupname = eventgroupname;
    }

    public String getPushtargetclass() {
        return pushtargetclass;
    }

    public void setPushtargetclass(String pushtargetclass) {
        this.pushtargetclass = pushtargetclass;
    }

    public String getPushmethodclass() {
        return pushmethodclass;
    }

    public void setPushmethodclass(String pushmethodclass) {
        this.pushmethodclass = pushmethodclass;
    }

    public String getPushclass() {
        return pushclass;
    }

    public void setPushclass(String pushclass) {
        this.pushclass = pushclass;
    }

    public String getApppushclass() {
        return apppushclass;
    }

    public void setApppushclass(String apppushclass) {
        this.apppushclass = apppushclass;
    }

    public String getSmspushclass() {
        return smspushclass;
    }

    public void setSmspushclass(String smspushclass) {
        this.smspushclass = smspushclass;
    }

    public String getEmailpushclass() {
        return emailpushclass;
    }

    public void setEmailpushclass(String emailpushclass) {
        this.emailpushclass = emailpushclass;
    }
}
