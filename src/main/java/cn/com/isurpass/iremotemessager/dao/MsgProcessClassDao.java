package cn.com.isurpass.iremotemessager.dao;

import cn.com.isurpass.iremotemessager.domain.MsgProcessClass;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface MsgProcessClassDao extends CrudRepository<MsgProcessClass, Integer> {
    List<MsgProcessClass> findByType(Integer type);

    List<MsgProcessClass> findByTypeAndSubtype(Integer type, Integer subtype);
}
