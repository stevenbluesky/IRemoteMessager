package cn.com.isurpass.iremotemessager.freemarker;

import java.io.IOException;
import java.io.Reader;
import java.io.StringReader;
import java.util.Date;

import org.apache.commons.lang.StringUtils;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;

import cn.com.isurpass.iremotemessager.SpringUtil;
import cn.com.isurpass.iremotemessager.common.constant.MsgTemplateType;
import cn.com.isurpass.iremotemessager.domain.MsgContentTemplate;
import cn.com.isurpass.iremotemessager.service.MsgContentTemplateService;
import freemarker.cache.TemplateLoader;

public class DBTemplateLoader implements TemplateLoader
{
	public final static String TEMPLATE_KEY_TEMPLATE_NAME = "templatekeytemplate";
	private final static String TEMPLATE__NAME_TEMPLATE = "{\"eventcode\":\"${eventcode}\",\"platform\":\"${platform}\",\"language\":\"${language}\",\"type\":\"${type}\"}";
	private static  MsgContentTemplate TEMPLATE_KEY_TEMPLATE = new MsgContentTemplate(TEMPLATE__NAME_TEMPLATE , new Date());
	
	@Override
	public Object findTemplateSource(String name) throws IOException
	{
        name = splitName(name);
		if ( TEMPLATE_KEY_TEMPLATE_NAME.equals(name) )
			return TEMPLATE_KEY_TEMPLATE;
		
		if ( StringUtils.isBlank(name))
			return null ;
		
		JSONObject json = JSON.parseObject(name);
		if ( !json.containsKey("eventcode")
			|| !json.containsKey("platform")
			|| !json.containsKey("language")
			|| !json.containsKey("type"))
			return null ;
		String eventcode = json.getString("eventcode");
		int platform = json.getIntValue("platform");
		String language = json.getString("language");
		int type = json.getIntValue("type");
		
		MsgContentTemplateService svr = SpringUtil.getBean(MsgContentTemplateService.class);
		
		return svr.findContentTemplate(eventcode, platform, language, MsgTemplateType.values()[type]);
	}

    private String splitName(String name) {
	    name = name.substring(0, name.lastIndexOf("_"));
	    name = name.substring(0, name.lastIndexOf("_"));

	    return name;
    }

    @Override
	public long getLastModified(Object templateSource)
	{
		if ( templateSource == null )
			return 0 ;
		return ((MsgContentTemplate)templateSource).getLastupdatetime().getTime();
	}

	@Override
	public Reader getReader(Object templateSource, String encoding) throws IOException
	{
		if ( templateSource == null )
			return null ;
		return new StringReader(((MsgContentTemplate) templateSource).getContenttemplate());
	}

	@Override
	public void closeTemplateSource(Object templateSource) throws IOException
	{
		//Do nothing
	}

}
