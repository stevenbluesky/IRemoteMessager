package cn.com.isurpass.iremotemessager.dao;

import cn.com.isurpass.iremotemessager.domain.MsgEventGroup;
import cn.com.isurpass.iremotemessager.domain.MsgPushSetting;
import cn.com.isurpass.iremotemessager.domain.NotificationSetting;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import java.util.List;

/**
 * @author liwenxiang
 * Date:2018/11/17
 * Time:14:48
 */
public interface MsgPushSettingDao extends CrudRepository<MsgPushSetting, Integer> {
    @Query(value = "SELECT ps.* FROM msg_eventgroupevent ege \n" +
            "LEFT JOIN msg_eventtype et ON et.`eventcode`= ege.`eventcode` \n" +
            "LEFT JOIN msg_eventgroup eg ON eg.`msgeventgroupid` = ege.`msgeventgroupid` AND eg.`platform` = ege.`platform`\n" +
            "LEFT JOIN msg_pushsetting ps ON ps.`msgeventgroupid`= eg.`msgeventgroupid` AND ps.`platform`= eg.`platform`\n" +
            "WHERE et.`eventcode`= :eventcode AND ege.`platform`= :platform", nativeQuery = true)
    MsgPushSetting findMsgPushSetting(@Param("eventcode")String eventCode, @Param("platform")Integer platform);

    List<MsgPushSetting> findByPlatformAndMsgEventGroupIn(Integer platform, List<MsgEventGroup> eventgroup, Pageable pageable);

    long countByPlatformAndMsgEventGroupIn(Integer platform, List<MsgEventGroup> eventgroup);

    long countByMsgEventGroupIn(List<MsgEventGroup> eventgroup);

    List<MsgPushSetting> findByMsgEventGroupIn(List<MsgEventGroup> eventgroup, Pageable pageable);
}
