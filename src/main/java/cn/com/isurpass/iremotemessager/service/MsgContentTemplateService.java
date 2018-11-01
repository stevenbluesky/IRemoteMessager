package cn.com.isurpass.iremotemessager.service;

import java.util.Arrays;
import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Component;

import cn.com.isurpass.iremotemessager.common.constant.MsgMethodType;
import cn.com.isurpass.iremotemessager.common.constant.MsgTemplateType;
import cn.com.isurpass.iremotemessager.dao.MsgContentTemplateDao;
import cn.com.isurpass.iremotemessager.domain.MsgContentTemplate;

@Component
public class MsgContentTemplateService
{
	private final static String defaultlanguage = "zh_CN";

	
	@Resource
	private MsgContentTemplateDao dao ;
	
	public List<MsgContentTemplate> findByEventcodeAndLanguageAndType(String eventcode ,int platform ,String language , MsgMethodType type)
	{
		List<MsgContentTemplate> lst = dao.findByEventcodeAndPlatformAndLanguageInAndTypeIn(eventcode, platform,
																				Arrays.asList(new String[] {language , defaultlanguage }), 
																				type.getTemplatetype());
																				
		
		return lst ;
	}
	
	public MsgContentTemplate findContentTemplate(List<MsgContentTemplate> lst , MsgTemplateType type)
	{
		if ( lst == null || lst.size() == 0 )
			return null ;
		
		for ( MsgContentTemplate mct : lst)
			if ( type.ordinal() == mct.getType())
				return mct ;
		return null ;
	}
}
