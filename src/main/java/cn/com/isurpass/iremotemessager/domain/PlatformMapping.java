package cn.com.isurpass.iremotemessager.domain;


import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;

@Entity
@Table(name="platformmapping")
public class PlatformMapping {
    private int platformmappingid;
    private int fromplatform;
    private int toplatform;

    public PlatformMapping() {
    }

    public PlatformMapping(int fromplatform, int toplatform) {
        this.fromplatform = fromplatform;
        this.toplatform = toplatform;
    }

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @GenericGenerator(name = "generator", strategy = "increment")
    public int getPlatformmappingid() {
        return platformmappingid;
    }

    public void setPlatformmappingid(int platformmappingid) {
        this.platformmappingid = platformmappingid;
    }

    public int getFromplatform() {
        return fromplatform;
    }

    public void setFromplatform(int fromplatform) {
        this.fromplatform = fromplatform;
    }

    public int getToplatform() {
        return toplatform;
    }

    public void setToplatform(int toplatform) {
        this.toplatform = toplatform;
    }
}
