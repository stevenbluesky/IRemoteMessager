package cn.com.isurpass.iremotemessager.framework;

import java.util.List;

import cn.com.isurpass.iremotemessager.domain.User;
import cn.com.isurpass.iremotemessager.vo.*;
import org.apache.commons.lang.StringUtils;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.stereotype.Component;

import com.alibaba.fastjson.JSONObject;

import cn.com.isurpass.iremotemessager.SpringUtil;
import cn.com.isurpass.iremotemessager.targetdecision.OwnerTargetDecision;

@Component
public class EventProcessor implements Runnable
{
	private static Log log = LogFactory.getLog(OwnerTargetDecision.class);
	private final static int MESSAGE_PARSE_TYPE_JPUSHMESSAGE = 1;
	private final static int MESSAGE_PARSE_TYPE_JPUSHNOTIFICATION = 2;
	private final static int MESSAGE_PARSE_TYPE_SMS = 3;
	private final static int MESSAGE_PARSE_TYPE_MAIL = 4;

	private final static int MESSAGE_SENDER_TYPE_JPUSHMESSAGE = 1;
	private final static int MESSAGE_SENDER_TYPE_JPUSHNOTIFICATION = 2;
	private final static int MESSAGE_SENDER_TYPE_SMS = 3;
	private final static int MESSAGE_SENDER_TYPE_MAIL = 4;

	private EventData eventdata;
	private ProcessClass processclass;

	public EventProcessor()
	{
		super();
	}

	public EventProcessor(EventData eventdata)
	{
		super();
		this.eventdata = eventdata;
	}

	@Override
	public void run()
	{
		initProcessclass();

		IMessageTargetDecision td = createTargetDecision();

		List<User> lmu = td.messageTarget(eventdata);

		IMessageMethodDecision mmd = createMethodDecision();
		mmd.setMsgInfo(eventdata, lmu);

		List<JPushMessageData> lpmd = mmd.getJPushMessageData();
		sendmessage(lpmd, MESSAGE_PARSE_TYPE_JPUSHMESSAGE, MESSAGE_SENDER_TYPE_JPUSHMESSAGE);

		List<JPushNotificationData> lpnd = mmd.getJPushNotificationData();
		sendmessage(lpnd, MESSAGE_PARSE_TYPE_JPUSHNOTIFICATION, MESSAGE_SENDER_TYPE_JPUSHNOTIFICATION);

		List<SmsData> smsDataList = mmd.getSmsData();
		sendmessage(smsDataList, MESSAGE_PARSE_TYPE_SMS, MESSAGE_SENDER_TYPE_SMS);
	}

	private <T> void sendmessage(List<T> lst, int parsertype, int sendertype)
	{
		if ( lst == null || lst.size() == 0 )
			return ;
		IMessageParser<T> pmdparser = createMessageParser(parsertype);
		IMessageSender<T> pmdsender = createSender(sendertype);

		for (T pmd : lst)
		{
			List<T> ls = pmdparser.parse(eventdata, pmd);
			if ( ls == null )
				continue;
			for (T p : ls)
				pmdsender.send(eventdata, p);
		}
	}

	private void initProcessclass()
	{
		processclass = new ProcessClass();
//		processclass.setTargetdecisionclass("cn.com.isurpass.iremotemessager.targetdecision.OwnerTargetDecision");
//		processclass.setMethoddecisionclass("cn.com.isurpass.iremotemessager.methoddecision.JPushMessageMethodDecision");
//		processclass.getParseclass()[MESSAGE_PARSE_TYPE_JPUSHMESSAGE] = "cn.com.isurpass.iremotemessager.messageparser.JPushMessageParser";
//		processclass.getSendclass()[MESSAGE_SENDER_TYPE_JPUSHMESSAGE] = "cn.com.isurpass.iremotemessager.sender.JPushMessageSender" ;
		
		processclass.setTargetdecisionclass("cn.com.isurpass.iremotemessager.targetdecision.CanOperatePeopleTargetDecision");
		processclass.setMethoddecisionclass("cn.com.isurpass.iremotemessager.methoddecision.JPushNotificationMailSmsMethodDecision");
		processclass.getParseclass()[MESSAGE_PARSE_TYPE_JPUSHMESSAGE] = "cn.com.isurpass.iremotemessager.messageparser.JPushMessageParser";
		processclass.getParseclass()[MESSAGE_PARSE_TYPE_JPUSHNOTIFICATION] = "cn.com.isurpass.iremotemessager.messageparser.JPushNotificationParser";
		processclass.getParseclass()[MESSAGE_PARSE_TYPE_SMS] = "cn.com.isurpass.iremotemessager.messageparser.SmsParser";

		processclass.getSendclass()[MESSAGE_SENDER_TYPE_JPUSHMESSAGE] = "cn.com.isurpass.iremotemessager.sender.JPushMessageSender" ;
		processclass.getSendclass()[MESSAGE_SENDER_TYPE_JPUSHNOTIFICATION] = "cn.com.isurpass.iremotemessager.sender.JPushNotificationSender" ;
		processclass.getSendclass()[MESSAGE_SENDER_TYPE_SMS] = "cn.com.isurpass.iremotemessager.sender.SmsSender";
		
		JSONObject json = new JSONObject();
		json.put("key", "a test message");
		processclass.getMessagetemplate()[MESSAGE_PARSE_TYPE_JPUSHMESSAGE] = new String[] {json.toJSONString()};
		processclass.getMessagetemplate()[MESSAGE_PARSE_TYPE_JPUSHNOTIFICATION] = new String[] {"A test notification" , json.toJSONString()};
	}

	private IMessageMethodDecision createMethodDecision()
	{
		Object instance = createclassinstance(processclass.getMethoddecisionclass());
		if (instance == null)
			return null;
		return (IMessageMethodDecision) instance;
	}

	private IMessageTargetDecision createTargetDecision()
	{
		Object instance = createclassinstance(processclass.getTargetdecisionclass());
		if (instance == null)
			return null;
		return (IMessageTargetDecision) instance;
	}

	private <T> IMessageParser<T> createMessageParser(int type)
	{
		Object instance = createclassinstance(processclass.getParseclass()[type]);
		if (instance == null)
			return null;
		return (IMessageParser) instance;
	}

	private <T> IMessageSender<T> createSender(int type)
	{
		Object instance = createclassinstance(processclass.getSendclass()[type]);
		if (instance == null)
			return null;
		return (IMessageSender) instance;
	}

	private Object createclassinstance(String classname)
	{
		if (StringUtils.isBlank(classname))
			return null;

		try
		{
			return SpringUtil.getBean(classname,Class.forName(classname));
			//return Class.forName(classname).newInstance();
		}
		catch (Throwable t)
		{
			log.error(t.getMessage(), t);
		}
		return null;
	}

	public void setEventdata(EventData eventdata)
	{
		this.eventdata = eventdata;
	}

}
