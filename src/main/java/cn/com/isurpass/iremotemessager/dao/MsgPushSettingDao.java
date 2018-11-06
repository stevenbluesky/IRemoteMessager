package cn.com.isurpass.iremotemessager.dao;

import cn.com.isurpass.iremotemessager.domain.MsgPushSetting;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

public interface MsgPushSettingDao extends CrudRepository<MsgPushSetting, Integer>{
    @Query(value = "SELECT ps.* FROM msg_eventgroupevent ege \n" +
            "LEFT JOIN msg_eventtype et ON et.`eventcode`= ege.`eventcode` \n" +
            "LEFT JOIN msg_eventgroup eg ON eg.`msgeventgroupid` = ege.`msgeventgroupid` AND eg.`platform` = ege.`platform`\n" +
            "LEFT JOIN msg_pushsetting ps ON ps.`msgeventgroupid`= eg.`msgeventgroupid` AND ps.`platform`= eg.`platform`\n" +
            "WHERE et.`eventcode`= :eventcode AND ege.`platform`= :platform", nativeQuery = true)
    MsgPushSetting findMsgPushSetting(@Param("eventcode")String eventCode, @Param("platform")Integer platform);
}
