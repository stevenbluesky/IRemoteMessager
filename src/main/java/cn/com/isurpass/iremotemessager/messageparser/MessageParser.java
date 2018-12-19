package cn.com.isurpass.iremotemessager.messageparser;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

import cn.com.isurpass.iremotemessager.common.util.IRemoteUtils;
import cn.com.isurpass.iremotemessager.vo.JPushNotificationData;
import freemarker.template.TemplateNotFoundException;
import org.apache.commons.lang.StringUtils;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.ui.freemarker.FreeMarkerTemplateUtils;

import com.alibaba.fastjson.JSON;

import cn.com.isurpass.iremotemessager.common.constant.MsgTemplateType;
import cn.com.isurpass.iremotemessager.freemarker.DBTemplateLoader;
import cn.com.isurpass.iremotemessager.vo.EventData;
import freemarker.template.Configuration;
import freemarker.template.Template;

public class MessageParser
{
	private static Log log = LogFactory.getLog(MessageParser.class);
    private static final String REPORT_TIME = "reporttime";
    private static final String TIME = "time";

	private static Configuration freemarkercfg ;
	private String language ;
	private EventData data;
	private MsgTemplateType type ;
	private JPushNotificationData jPushNotificationData;
	
	static 
	{
		freemarkercfg = new Configuration(Configuration.VERSION_2_3_28);
		freemarkercfg.setTemplateLoader(new DBTemplateLoader());
		freemarkercfg.setNumberFormat("#");
	}
	
	public MessageParser(EventData data , String language,MsgTemplateType type)
	{
		super();
		this.data = data;
		this.language = language;
		this.type = type;
	}

	public MessageParser(EventData data, String language, MsgTemplateType type, JPushNotificationData jPushNotificationData) {
		this(data, language, type);
		this.jPushNotificationData = jPushNotificationData;
	}

	public String getMessage()
	{
		try {
			Template template = freemarkercfg.getTemplate(DBTemplateLoader.TEMPLATE_KEY_TEMPLATE_NAME);

			String tn = FreeMarkerTemplateUtils.processTemplateIntoString(template, createtemplatename());

			template = freemarkercfg.getTemplate(tn);
			//template.setNumberFormat("#");

			return IRemoteUtils.replaceBlank(FreeMarkerTemplateUtils.processTemplateIntoString(template, createparameter()));
		} catch (TemplateNotFoundException e) {
			if (log.isWarnEnabled()) {
				log.warn("TemplateNotFoundException:" + e.getTemplateName());
			}
		} catch (Throwable t) {
			log.error(t.getMessage(), t);
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
		Map<String , Object> m = new HashMap<>() ;
		
		m.put("eventcode",data.getEventtype());
		m.put("platform",String.valueOf(data.getPlatform()));
		m.put("language",language);
		m.put("type",String.valueOf(type.ordinal()));

        appendParameterTime(m);

		if ( data.getEventparameters() != null )
            for (Map.Entry<String, Object> entry : data.getEventparameters().entrySet()) {
                m.put(entry.getKey(), entry.getValue());
            }

		if ( data.getDomainobjects() != null )
			for (Map.Entry<String, Object> entry : data.getDomainobjects().entrySet()) {
				if (entry.getValue() != null) {
					m.put(entry.getKey(), entry.getValue());
				}
			}

		if ( log.isInfoEnabled())
			log.info(JSON.toJSONString(m));
		return m ;
	}

    private void appendParameterTime(Map<String, Object> m) {
	    SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");

	    Iterator<Map.Entry<String, Object>> iterator = data.getEventparameters().entrySet().iterator();
	    while (iterator.hasNext()) {
            Map.Entry<String, Object> entry = iterator.next();
            if (REPORT_TIME.equals(entry.getKey())) {
                String value = new String(entry.getValue().toString());
                if ("0".equals(value)) {
                    m.put(TIME, format.format(new Date()));
                } else {
                    m.put(TIME,
                            StringUtils.isNumeric(value)
                            ? format.format(new Date(Long.valueOf(value.length() == 10 ? value + "000" : value)))
                            : value
                    );
                }
                return;
            }
        }
        m.put(TIME, format.format(new Date()));
    }

    public String getMessageforLog()
	{
		return getMessage();
	}

	public void setType(MsgTemplateType type) {
		this.type = type;
	}
}
