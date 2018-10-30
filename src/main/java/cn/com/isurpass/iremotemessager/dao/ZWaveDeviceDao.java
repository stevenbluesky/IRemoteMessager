package cn.com.isurpass.iremotemessager.dao;

import org.springframework.data.repository.CrudRepository;

import cn.com.isurpass.iremotemessager.domain.ZWaveDevice;

public interface ZWaveDeviceDao extends CrudRepository<ZWaveDevice, Integer> 
{

}
