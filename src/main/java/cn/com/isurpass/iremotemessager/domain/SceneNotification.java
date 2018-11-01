package cn.com.isurpass.iremotemessager.domain;

import com.alibaba.fastjson.annotation.JSONField;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;

@Entity
@Table(name = "scenenotification")
public class SceneNotification {
    private int scenenotificationid;
    private int app;
    private String mail;
    private String message;
    private int ring = 0;
    private int builder_id = 0;
    private String sound;
    private Scene scene;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @GenericGenerator(name = "generator", strategy = "increment")
    @Column(name = "scenenotificationid")
    public int getScenenotificationid() {
        return scenenotificationid;
    }

    public void setScenenotificationid(int scenenotificationid) {
        this.scenenotificationid = scenenotificationid;
    }

    public int getApp() {
        return app;
    }

    public void setApp(int app) {
        this.app = app;
    }

    public String getMail() {
        return mail;
    }

    public void setMail(String mail) {
        this.mail = mail;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public int getRing() {
        return ring;
    }

    public void setRing(int ring) {
        this.ring = ring;
    }

    public int getBuilder_id() {
        return builder_id;
    }

    public String getSound() {
        return sound;
    }

    @JSONField(serialize = false)
    @OneToOne
    @JoinColumn(name = "scenedbid")
    public Scene getScene() {
        return scene;
    }

    public void setScene(Scene scene) {
        this.scene = scene;
    }

    public void setBuilder_id(int builder_id) {
        this.builder_id = builder_id;
    }

    public void setSound(String sound) {
        this.sound = sound;
    }
}
