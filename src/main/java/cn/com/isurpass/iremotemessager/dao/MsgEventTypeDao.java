package cn.com.isurpass.iremotemessager.dao;

import cn.com.isurpass.iremotemessager.domain.MsgEventType;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;


import java.util.List;

/**
 * @author liwenxiang
 * Date:2018/11/6
 * Time:17:32
 */
@Repository
public interface MsgEventTypeDao extends CrudRepository<MsgEventType, Integer> {

    @Query(value = "SELECT eventcode from msg_eventtype", nativeQuery = true)
    List<String> findAllEventCode();

    List<MsgEventType> findByEventtypenameContainingAndEventcodeContaining(String eventname, String eventcode, Pageable pageable);

    long countByEventtypenameContainingAndEventcodeContaining(String eventname, String eventcode);

    MsgEventType findByMsgeventtypeid(Integer msgeventtypeid);

    MsgEventType findByEventtypename(String eventname);

    MsgEventType findByEventcode(String eventcode);

    List<MsgEventType> findByEventtypenameContainingAndEventcodeContaining(String eventname, String eventcode);
}
