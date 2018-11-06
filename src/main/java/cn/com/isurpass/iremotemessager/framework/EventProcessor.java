package cn.com.isurpass.iremotemessager.framework;

import java.util.Iterator;
import java.util.List;
import java.util.Map;

import cn.com.isurpass.iremotemessager.common.constant.IRemoteConstantDefine;
import cn.com.isurpass.iremotemessager.common.util.IRemoteUtils;
import cn.com.isurpass.iremotemessager.domain.User;
import cn.com.isurpass.iremotemessager.service.MsgEventGroupeventService;
import cn.com.isurpass.iremotemessager.service.MsgPushSettingService;
import cn.com.isurpass.iremotemessager.vo.*;
import org.apache.commons.lang.StringUtils;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.NoSuchBeanDefinitionException;
import org.springframework.stereotype.Component;

import com.alibaba.fastjson.JSONObject;

import cn.com.isurpass.iremotemessager.SpringUtil;
import cn.com.isurpass.iremotemessager.targetdecision.OwnerTargetDecision;

import javax.annotation.Resource;

@Component
public class EventProcessor implements Runnable
{
	private static Log log = LogFactory.getLog(OwnerTargetDecision.class);

	private EventData eventdata;
	private ProcessClass processclass;

	@Resource
	private MsgEventGroupeventService msgEventGroupeventService;
	@Resource
	private MsgPushSettingService msgPushSettingService;

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
		if (!initProcessclass()) {
			return;
		}

		IMessageTargetDecision td = createTargetDecision();

		List<User> lmu = td.messageTarget(eventdata);

		IMessageMethodDecision mmd = createMethodDecision();
		mmd.setMsgInfo(eventdata, lmu);

		List<JPushMessageData> lpmd = mmd.getJPushMessageData();
		sendmessage(lpmd, IRemoteConstantDefine.MESSAGE_PARSE_TYPE_JPUSHMESSAGE, IRemoteConstantDefine.MESSAGE_SENDER_TYPE_JPUSHMESSAGE);

		List<JPushNotificationData> lpnd = mmd.getJPushNotificationData();
		sendmessage(lpnd, IRemoteConstantDefine.MESSAGE_PARSE_TYPE_JPUSHNOTIFICATION, IRemoteConstantDefine.MESSAGE_SENDER_TYPE_JPUSHNOTIFICATION);

		List<SmsData> smsDataList = mmd.getSmsData();
		sendmessage(smsDataList, IRemoteConstantDefine.MESSAGE_PARSE_TYPE_SMS, IRemoteConstantDefine.MESSAGE_SENDER_TYPE_SMS);
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

	private boolean initProcessclass()
	{
		processclass = new ProcessClass();

		if (StringUtils.isBlank(eventdata.getEventtype()) || IRemoteUtils.isBlank(eventdata.getPlatform())) {
			log.warn("push fail, can't find even type or platform");
			return false;
		}

		String targetDecisionClassName = msgEventGroupeventService.findMsgPushTargetDecisionClassName(eventdata.getEventtype(), eventdata.getPlatform());
		if (StringUtils.isBlank(targetDecisionClassName)) {
			log.warn("push fail, can't find targetDecisionClass Define");
			return false;
		}

		String msgPushMethodClassName = msgEventGroupeventService.findMsgPushMethodClassName(eventdata.getEventtype(), eventdata.getPlatform());
		if (StringUtils.isBlank(targetDecisionClassName)) {
			log.warn("push fail, can't find push method Define");
			return false;
		}

		Map<Integer, String> parserMap = msgPushSettingService.findParserMap(eventdata.getEventtype(), eventdata.getPlatform());
		Iterator<Map.Entry<Integer, String>> parserIterator = parserMap.entrySet().iterator();
		while (parserIterator.hasNext()) {
			Map.Entry<Integer, String> next = parserIterator.next();
			processclass.getParseclass()[next.getKey()] = next.getValue();
		}

		Map<Integer, String> senderMap = msgPushSettingService.findSenderMap(eventdata.getEventtype(), eventdata.getPlatform());
		Iterator<Map.Entry<Integer, String>> senderIterator = senderMap.entrySet().iterator();
		while (senderIterator.hasNext()) {
			Map.Entry<Integer, String> next = senderIterator.next();
			processclass.getParseclass()[next.getKey()] = next.getValue();
		}

		processclass.setTargetdecisionclass(targetDecisionClassName);
		processclass.setMethoddecisionclass(msgPushMethodClassName);
		
		JSONObject json = new JSONObject();
		json.put("key", "a test message");
		processclass.getMessagetemplate()[IRemoteConstantDefine.MESSAGE_PARSE_TYPE_JPUSHMESSAGE] = new String[] {json.toJSONString()};
		processclass.getMessagetemplate()[IRemoteConstantDefine.MESSAGE_PARSE_TYPE_JPUSHNOTIFICATION] = new String[] {"A test notification" , json.toJSONString()};
		return true;
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
		catch (NoSuchBeanDefinitionException e)
		{
			try
			{
				return SpringUtil.getBean(Class.forName(classname));
			}
			catch (ClassNotFoundException e1)
			{
				e1.printStackTrace();
			}
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
