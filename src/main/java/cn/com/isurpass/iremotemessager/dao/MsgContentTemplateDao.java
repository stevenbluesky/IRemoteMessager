package cn.com.isurpass.iremotemessager.dao;

import java.util.List;

import org.springframework.data.repository.CrudRepository;

import cn.com.isurpass.iremotemessager.domain.MsgContentTemplate;

public interface MsgContentTemplateDao extends CrudRepository<MsgContentTemplate, Integer> 
{
	List<MsgContentTemplate> findByEventcodeAndPlatformInAndLanguageInAndType(String eventcode ,List<Integer> platform, List<String> language , Integer type);
}
