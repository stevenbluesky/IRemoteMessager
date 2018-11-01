package cn.com.isurpass.iremotemessager.methoddecision;

import java.util.ArrayList;
import java.util.List;

import cn.com.isurpass.iremotemessager.vo.MailData;
import cn.com.isurpass.iremotemessager.vo.SmsData;
import org.springframework.stereotype.Component;

import cn.com.isurpass.iremotemessager.vo.JPushMessageData;
import cn.com.isurpass.iremotemessager.vo.JPushNotificationData;

@Component("cn.com.isurpass.iremotemessager.methoddecision.JPushMessageMethodDecision")
public class JPushMessageMethodDecision extends MethodDecisionBase
{
	@Override
	public List<JPushMessageData> getJPushMessageData() 
	{
		List<JPushMessageData> lst = new ArrayList<>();

		if (msguser == null || msguser.size() == 0) {
			return lst;
		}
		
		JPushMessageData pmd = super.createJPushMessageData(msguser); 
		lst.add(pmd);
				
		return lst;
	}
}
