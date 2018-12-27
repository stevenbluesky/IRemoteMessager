package cn.com.isurpass.iremotemessager.dao;

import cn.com.isurpass.iremotemessager.domain.MsgPushSettingDtl;
import org.springframework.data.repository.CrudRepository;

/**
 * @author liwenxiang
 * Date:2018/11/19
 * Time:16:45
 */
public interface MsgPushSettingDtlDao extends CrudRepository<MsgPushSettingDtl,Integer> {

    MsgPushSettingDtl findByMsgpushsettingdtlid(int parseInt);

    MsgPushSettingDtl findByTypeAndSubtypeAndMsgPushSetting_Platform(Integer type, Integer subType, Integer platform);
}
