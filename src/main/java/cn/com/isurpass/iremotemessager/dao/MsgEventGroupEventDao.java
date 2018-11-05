package cn.com.isurpass.iremotemessager.dao;

import cn.com.isurpass.iremotemessager.domain.MsgEventGroupEvent;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

public interface MsgEventGroupEventDao extends CrudRepository<MsgEventGroupEvent, Integer>{
    MsgEventGroupEvent findByMsgEventType_EventcodeAndPlatform(String eventcode, Integer platform);

    @Query(value = "SELECT ps.`msgpushtargetdecisionid` FROM msg_eventgroupevent ege \n" +
            "LEFT JOIN msg_eventtype et ON et.`eventcode`= ege.`eventcode` \n" +
            "LEFT JOIN msg_eventgroup eg ON eg.`msgeventgroupid` = ege.`msgeventgroupid` \n" +
            "LEFT JOIN msg_pushsetting ps ON ps.`msgeventgroupid`= eg.`msgeventgroupid`\n" +
            "WHERE et.`eventcode`= :eventcode AND ege.`platform`= :platform", nativeQuery = true)
    Integer findMsgPushTargetDecisionId(@Param("eventcode")String eventCode, @Param("platform")Integer platform);

    @Query(value = "SELECT ps.`msgpushmethodid` FROM msg_eventgroupevent ege \n" +
            "LEFT JOIN msg_eventtype et ON et.`eventcode`= ege.`eventcode` \n" +
            "LEFT JOIN msg_eventgroup eg ON eg.`msgeventgroupid` = ege.`msgeventgroupid` \n" +
            "LEFT JOIN msg_pushsetting ps ON ps.`msgeventgroupid`= eg.`msgeventgroupid`\n" +
            "WHERE et.`eventcode`= :eventcode AND ege.`platform`= :platform", nativeQuery = true)
    Integer findMsgPushMethodId(@Param("eventcode")String eventCode, @Param("platform")Integer platform);
}
