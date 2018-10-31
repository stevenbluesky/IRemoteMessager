package cn.com.isurpass.iremotemessager.dao;

import cn.com.isurpass.iremotemessager.domain.UserShareDevice;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface UserShareDeviceDao  extends CrudRepository<UserShareDevice, Integer>{
    List<UserShareDevice> findByUserShare_ShareuseridAndZwavedeviceid(Integer fromuserid, Integer zwavedeviceid);

    List<UserShareDevice> findByUserShare_ShareuseridAndInfrareddeviceid(Integer fromuserid, Integer infrareddeviceid);

    List<UserShareDevice> findByUserShare_ShareuseridAndCameraid(Integer fromuserid, Integer cameraid);
    }
