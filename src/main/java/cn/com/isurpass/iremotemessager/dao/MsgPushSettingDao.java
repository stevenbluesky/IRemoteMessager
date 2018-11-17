package cn.com.isurpass.iremotemessager.dao;

import cn.com.isurpass.iremotemessager.domain.MsgEventGroup;
import cn.com.isurpass.iremotemessager.domain.MsgPushSetting;
import cn.com.isurpass.iremotemessager.domain.NotificationSetting;
import org.springframework.data.domain.Pageable;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

/**
 * @author liwenxiang
 * Date:2018/11/17
 * Time:14:48
 */
public interface MsgPushSettingDao extends CrudRepository<MsgPushSetting, Integer> {
    List<MsgPushSetting> findByPlatformAndMsgEventGroupIn(Integer platform, List<MsgEventGroup> eventgroup, Pageable pageable);

    long countByPlatformAndMsgEventGroupIn(Integer platform, List<MsgEventGroup> eventgroup);

    long countByMsgEventGroupIn(List<MsgEventGroup> eventgroup);

    List<MsgPushSetting> findByMsgEventGroupIn(List<MsgEventGroup> eventgroup, Pageable pageable);
}
