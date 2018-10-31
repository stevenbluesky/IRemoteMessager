package cn.com.isurpass.iremotemessager.dao;

import org.springframework.data.repository.CrudRepository;

import cn.com.isurpass.iremotemessager.domain.ZWaveDevice;

import java.util.Collection;
import java.util.List;

public interface ZWaveDeviceDao extends CrudRepository<ZWaveDevice, Integer> 
{
    List<ZWaveDevice> findByZwavedeviceidIn(Collection<Integer> zwavedeviceids);
}
