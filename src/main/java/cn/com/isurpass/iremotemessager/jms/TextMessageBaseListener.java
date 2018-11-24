package cn.com.isurpass.iremotemessager.jms;

import cn.com.isurpass.iremotemessager.SpringUtil;
import cn.com.isurpass.iremotemessager.framework.EventProcessor;
import cn.com.isurpass.iremotemessager.vo.EventData;
import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONException;
import com.alibaba.fastjson.JSONObject;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import javax.jms.JMSException;
import javax.jms.Message;
import javax.jms.MessageListener;
import javax.jms.TextMessage;

public class TextMessageBaseListener implements MessageListener {

	private static Log log = LogFactory.getLog(TextMessageBaseListener.class);
	private static final String DEFAULT_TASK_QUEUE = "defaulttaskqueue";
	private static final String TYPE = "type";
	private static final String PLATFORM = "platform";
	private static final String DEVICE_ID = "deviceid";

	private Class<EventProcessor> cls ;

	public TextMessageBaseListener(Class<EventProcessor> cls) {
		super();
		this.cls = cls;
	}

	@Override
	public void onMessage(Message message)
	{
		if ( !( message instanceof TextMessage) )
			return ;
		
		try
		{
			TextMessage tm = (TextMessage) message;
			if ( log.isInfoEnabled()){
				log.info(tm.getText());
			}

			JSONObject json;
			try {
				json = JSON.parseObject(tm.getText());
			} catch (JSONException e) {
				log.error(e.getMessage(), e);
				return;
			}
			if (!json.containsKey(TYPE) || !json.containsKey(PLATFORM)) {
				if (log.isInfoEnabled()) {
					log.info("type or platform is null: "+tm.getText());
				}
				return;
			}

			EventData eventData = new EventData();
			eventData.setEventtype(json.getString(TYPE));
			eventData.setPlatform(json.getInteger(PLATFORM));
			eventData.setEventparameters(json);

			EventProcessor eventProcessor = SpringUtil.getBean(cls);

			eventProcessor.setEventdata(eventData);

			String key = json.getString(DEVICE_ID);
			if ( key == null || key.length() == 0 )
				key = DEFAULT_TASK_QUEUE;
			JSMTaskManager.addTask(key, eventProcessor);
		}
		catch(Throwable t)
		{
			log.error(t.getMessage(), t);
		}
	}
}
