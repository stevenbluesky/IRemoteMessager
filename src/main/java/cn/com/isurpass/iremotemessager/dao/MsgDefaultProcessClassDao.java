package cn.com.isurpass.iremotemessager.dao;

import cn.com.isurpass.iremotemessager.domain.MsgDefaultProcessClass;
import org.springframework.data.repository.CrudRepository;

public interface MsgDefaultProcessClassDao extends CrudRepository<MsgDefaultProcessClass,Integer>{
    MsgDefaultProcessClass findByPlatformAndEventcodeAndType(Integer paltform, String eventcode, Integer type);
}
