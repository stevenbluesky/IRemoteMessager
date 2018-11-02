package cn.com.isurpass.iremotemessager.dao;

import cn.com.isurpass.iremotemessager.domain.SystemParameter;
import org.springframework.data.repository.CrudRepository;

public interface SystemParameterDao extends CrudRepository<SystemParameter, Integer>{
    SystemParameter findByKey(String key);
}
