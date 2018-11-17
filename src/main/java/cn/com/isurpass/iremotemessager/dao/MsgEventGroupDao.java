package cn.com.isurpass.iremotemessager.dao;

import cn.com.isurpass.iremotemessager.domain.MsgEventGroup;
import org.springframework.data.domain.Pageable;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface MsgEventGroupDao extends CrudRepository<MsgEventGroup, Integer> {

    List<MsgEventGroup> findByPlatformAndEventgroupnameContaining(int platform, String eventgroupname, Pageable pageable);

    long countByPlatformAndEventgroupnameContaining(int platform, String eventgroupname);

    MsgEventGroup findByPlatformAndEventgroupname(int platform, String eventgroupname);

    List<MsgEventGroup> findByEventgroupnameContaining(String eventgroupname, Pageable pageable);

    List<MsgEventGroup> findByEventgroupnameContaining(String eventgroupname);

    long countByEventgroupnameContaining(String eventgroupname);

    MsgEventGroup findByMsgeventgroupid(Integer msgeventgroupid);

    void deleteByMsgeventgroupid(int parseInt);
}
