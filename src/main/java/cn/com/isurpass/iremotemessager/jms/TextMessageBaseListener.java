package cn.com.isurpass.iremotemessager.jms;

import cn.com.isurpass.iremotemessager.SpringUtil;
import cn.com.isurpass.iremotemessager.framework.EventProcessor;
import cn.com.isurpass.iremotemessager.vo.EventData;
import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import javax.jms.Message;
import javax.jms.MessageListener;
import javax.jms.TextMessage;

public class TextMessageBaseListener implements MessageListener {

	private static Log log = LogFactory.getLog(TextMessageBaseListener.class);
	private static final String DEFAULT_TASK_QUEUE = "defaulttaskqueue";
	private static final String TYPE = "type";
	private static final String PLATFORM = "platform";
	private static final String DEVICEID = "deviceid";

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

			log.info(tm.getText());

			if ( log.isInfoEnabled())
			{
				log.info(cls.getName());
				log.info(tm.getText());
			}
			JSONObject json = JSON.parseObject(tm.getText());
			if (!json.containsKey(TYPE) || !json.containsKey(PLATFORM)) {
				log.info("type or platform is null: "+tm.getText());
				return;
			}

			EventData eventData = new EventData();
			eventData.setEventtype(json.getString(TYPE));
			eventData.setPlatform(json.getInteger(PLATFORM));
			eventData.setEventparameters(json);

			EventProcessor eventProcessor = SpringUtil.getBean(cls);

			eventProcessor.setEventdata(eventData);
//			ITextMessageProcessor pro = JSON.parseObject(tm.getText(), cls);

			String key = json.getString(DEVICEID);
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
