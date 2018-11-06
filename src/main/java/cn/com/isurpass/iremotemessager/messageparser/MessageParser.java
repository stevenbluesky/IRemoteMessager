package cn.com.isurpass.iremotemessager.messageparser;

import java.util.HashMap;
import java.util.Map;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.ui.freemarker.FreeMarkerTemplateUtils;


import cn.com.isurpass.iremotemessager.common.constant.MsgTemplateType;
import cn.com.isurpass.iremotemessager.freemarker.DBTemplateLoader;
import cn.com.isurpass.iremotemessager.vo.EventData;
import freemarker.template.Configuration;
import freemarker.template.Template;

public class MessageParser
{
	private static Log log = LogFactory.getLog(MessageParser.class);
	
	private static Configuration freemarkercfg ;
	private String language ;
	private EventData data;
	private MsgTemplateType type ;
	
	static 
	{
		freemarkercfg = new Configuration(Configuration.VERSION_2_3_28);
	}
	
	public MessageParser(EventData data , String language,MsgTemplateType type)
	{
		super();
		this.data = data;
		this.language = language;
		this.type = type;
	}
	
	
	public String getMessage()
	{
		try
		{
			Template template = freemarkercfg.getTemplate(DBTemplateLoader.TEMPLATE_KEY_TEMPLATE_NAME);
			
			String tn = FreeMarkerTemplateUtils.processTemplateIntoString(template, createtemplatename());
			
			template = freemarkercfg.getTemplate(tn);
			
			return FreeMarkerTemplateUtils.processTemplateIntoString(template, createparameter());
		}
		catch (Throwable t)
		{
			log.error(t.getMessage() , t);
		}
		
		return null;
	}
	
	private Map<String , String> createtemplatename()
	{
		Map<String , String> m = new HashMap<String , String>() ;
		m.put("eventcode",data.getEventtype());
		m.put("platform",String.valueOf(data.getPlatform()));
		m.put("language",language);
		m.put("type",String.valueOf(type.ordinal()));
		
		return m ;
	}
	
	private Map<String , Object> createparameter()
	{
		Map<String , Object> m = new HashMap<String , Object>() ;
		
		m.put("eventcode",data.getEventtype());
		m.put("platform",String.valueOf(data.getPlatform()));
		m.put("language",language);
		m.put("type",String.valueOf(type.ordinal()));
		
		if ( data.getEventparameters() != null )
			for ( String k : data.getEventparameters().keySet())
				m.put(k, data.getEventparameters().get(k));
		
		if ( data.getDomainobjects() != null )
			for ( String k : data.getDomainobjects().keySet())
				m.put(k, data.getDomainobjects().get(k));
		
		return m ;
	}
	
	public String getMessageforLog()
	{
		return getMessage();
	}
	
}
