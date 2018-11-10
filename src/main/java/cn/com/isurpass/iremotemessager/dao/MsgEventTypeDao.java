package cn.com.isurpass.iremotemessager.dao;

import cn.com.isurpass.iremotemessager.domain.MsgEventType;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface MsgEventTypeDao extends CrudRepository<MsgEventType, Integer>{
    @Query(value = "SELECT eventcode from msg_eventtype", nativeQuery = true)
    List<String> findAllEventCode();
}
