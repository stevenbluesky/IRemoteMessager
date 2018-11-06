package cn.com.isurpass.iremotemessager.service;

import java.util.Arrays;
import java.util.List;

import javax.annotation.Resource;

import org.apache.commons.lang.StringUtils;
import org.springframework.stereotype.Component;

import cn.com.isurpass.iremotemessager.common.constant.MsgTemplateType;
import cn.com.isurpass.iremotemessager.dao.MsgContentTemplateDao;
import cn.com.isurpass.iremotemessager.domain.MsgContentTemplate;

@Component
public class MsgContentTemplateService
{
	private final static String defaultlanguage = "zh_CN";

	
	@Resource
	private MsgContentTemplateDao dao ;
	
	public List<MsgContentTemplate> findByEventcodeAndLanguageAndType(String eventcode ,int platform ,String language , MsgTemplateType type)
	{
		List<MsgContentTemplate> lst = dao.findByEventcodeAndPlatformInAndLanguageInAndType(eventcode, 
																				Arrays.asList(new Integer[] {platform , 999999}),
																				Arrays.asList(new String[] {language , defaultlanguage }), 
																				type.ordinal());
																				
		
		return lst ;
	}
	
	public MsgContentTemplate findContentTemplate(String eventcode ,int platform ,String language , MsgTemplateType type)
	{
		List<MsgContentTemplate> lst = this.findByEventcodeAndLanguageAndType(eventcode, platform, language, type);
		
		if ( lst == null || lst.size() == 0 )
			return null ;
		
		for ( MsgContentTemplate mct : lst)   // language + platform
			if ( StringUtils.isNotBlank(mct.getLanguage()) 
				&& mct.getLanguage().equals(language)
				&& mct.getPlatform() == platform)
				return mct ;

		for ( MsgContentTemplate mct : lst)  // default language + platform
			if ( StringUtils.isNotBlank(mct.getLanguage()) 
				&& mct.getLanguage().equals(defaultlanguage)
				&& mct.getPlatform() == platform)
				return mct ;
		
		for ( MsgContentTemplate mct : lst)  // language + default platform
			if ( StringUtils.isNotBlank(mct.getLanguage()) 
				&& mct.getLanguage().equals(language))
				return mct ;

		return lst.get(0) ;		
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
