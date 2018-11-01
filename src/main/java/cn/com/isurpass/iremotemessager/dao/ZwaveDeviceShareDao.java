package cn.com.isurpass.iremotemessager.dao;

import cn.com.isurpass.iremotemessager.domain.ZWaveDeviceShare;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface ZwaveDeviceShareDao extends CrudRepository<ZWaveDeviceShare, Integer>{
    List<ZWaveDeviceShare> findByZwavedeviceidAndShareowntypeIn(Integer zwavedeviceid, List<Integer> shareowntypeList);

    List<ZWaveDeviceShare> findByInfrareddeviceidAndShareowntypeIn(Integer infareddeivceid, List<Integer> shareowntypeList);

    List<ZWaveDeviceShare> findByAndCameraidAndShareowntypeIn(Integer cameraid, List<Integer> shareowntypeList);

    List<ZWaveDeviceShare> findByDeviceidAndShareowntypeIn(String deviceid, List<Integer> shareowntypeList);
}
