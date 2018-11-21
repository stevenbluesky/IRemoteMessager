package cn.com.isurpass.iremotemessager.dao;

import java.util.List;

import cn.com.isurpass.iremotemessager.domain.MsgEventType;
import cn.com.isurpass.iremotemessager.vo.MessageTemplateVo;
import org.springframework.data.domain.Pageable;
import org.springframework.data.repository.CrudRepository;

import cn.com.isurpass.iremotemessager.domain.MsgContentTemplate;

public interface MsgContentTemplateDao extends CrudRepository<MsgContentTemplate, Integer> {

	List<MsgContentTemplate> findByEventcodeAndPlatformInAndLanguageInAndType(String eventcode ,List<Integer> platform, List<String> language , Integer type);

	List<MsgContentTemplate> findByMsgEventType_Msgeventtypeid(Integer msgeventtypeid, Pageable pageable);

	long countByMsgEventType_Msgeventtypeid(Integer msgeventtypeid);

    MsgContentTemplate findByMsgcontenttemplateid(Integer msgcontenttemplateid);

    void deleteByMsgcontenttemplateid(int id);

    List<MsgContentTemplate> findByPlatformAndLanguage(Integer platform, String language);

    List<MsgContentTemplate> findByEventcodeAndPlatformAndLanguageInAndType(String s, int parseInt, String s1, int parseInt1);

    MsgContentTemplate findByPlatformAndMsgEventTypeAndLanguageAndType(Integer platform, MsgEventType event, String language, Integer type);
}
