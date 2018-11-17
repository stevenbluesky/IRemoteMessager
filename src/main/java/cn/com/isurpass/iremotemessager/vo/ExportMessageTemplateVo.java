package cn.com.isurpass.iremotemessager.vo;

import java.util.Date;

/**
 * @author liwenxiang
 * Date:2018/11/9
 * Time:19:05
 */
public class ExportMessageTemplateVo {
    private Integer platform;
    private Integer msgeventtypeid;
    private String eventcode;
    private String language;
    private Integer type;
    private String contenttemplate;

    public Integer getMsgeventtypeid() {
        return msgeventtypeid;
    }

    public void setMsgeventtypeid(Integer msgeventtypeid) {
        this.msgeventtypeid = msgeventtypeid;
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

    public String getLanguage() {
        return language;
    }

    public void setLanguage(String language) {
        this.language = language;
    }

    public Integer getType() {
        return type;
    }

    public void setType(Integer type) {
        this.type = type;
    }

    public String getContenttemplate() {
        return contenttemplate;
    }

    public void setContenttemplate(String contenttemplate) {
        this.contenttemplate = contenttemplate;
    }

}
