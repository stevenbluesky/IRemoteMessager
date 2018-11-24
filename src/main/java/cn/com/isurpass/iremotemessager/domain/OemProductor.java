package cn.com.isurpass.iremotemessager.domain;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="oemproductor")
public class OemProductor {
    private Integer oemproductorid;
    private String name;
    private Integer platform;
    private String pushmasterkey;
    private String pushappkey;

    @Id
    public Integer getOemproductorid() {
        return oemproductorid;
    }

    public void setOemproductorid(Integer oemproductorid) {
        this.oemproductorid = oemproductorid;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getPlatform() {
        return platform;
    }

    public void setPlatform(Integer platform) {
        this.platform = platform;
    }

    public String getPushmasterkey() {
        return pushmasterkey;
    }

    public void setPushmasterkey(String pushmasterkey) {
        this.pushmasterkey = pushmasterkey;
    }

    public String getPushappkey() {
        return pushappkey;
    }

    public void setPushappkey(String pushappkey) {
        this.pushappkey = pushappkey;
    }
}
