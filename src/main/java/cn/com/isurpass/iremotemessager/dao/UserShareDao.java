package cn.com.isurpass.iremotemessager.dao;

import cn.com.isurpass.iremotemessager.domain.UserShare;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface UserShareDao extends CrudRepository<UserShare, Integer>{
    List<UserShare> findByShareuseridAndSharedevicetypeAndSharetypeAndStatus(Integer shareUserId, Integer sharedevicetype, Integer shareType, Integer status);
}
