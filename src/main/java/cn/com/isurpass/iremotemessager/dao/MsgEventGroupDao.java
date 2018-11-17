package cn.com.isurpass.iremotemessager.dao;

import cn.com.isurpass.iremotemessager.domain.MsgEventGroup;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface MsgEventGroupDao extends CrudRepository<MsgEventGroup, Integer> {
    List<MsgEventGroup> findByPlatform(Integer platform);
}
