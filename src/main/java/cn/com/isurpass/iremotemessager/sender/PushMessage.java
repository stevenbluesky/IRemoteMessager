package cn.com.isurpass.iremotemessager.sender;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

import cn.com.isurpass.iremotemessager.util.AES;
import org.apache.commons.lang.StringUtils;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import cn.com.isurpass.iremotemessager.jpush.vo.Payload;
import cn.jiguang.common.resp.APIRequestException;
import cn.jpush.api.JPushClient;
import cn.jpush.api.push.PushResult;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.serializer.SerializerFeature;

public class PushMessage
{
	private final static int PLATFORM_SINGAPORE = 7;
	private static Log log = LogFactory.getLog(PushMessage.class);

	private static Map<Integer, JPushClient[]> jpushmap = new ConcurrentHashMap<Integer, JPushClient[]>();
	private static JPushClient iSurpassPushClent = new JPushClient("29263ccfdb548afedbc0cdda",
										"df1a7eaf85c6bba7c9fd5d3b");

	public static void initPushClient(int platform, String masterkey, String appkey)
	{
		synchronized (jpushmap)
		{
			if (jpushmap.containsKey(platform) || StringUtils.isBlank(masterkey) || StringUtils.isBlank(appkey))
				return;
			jpushmap.put(platform, new JPushClient[] {
									new JPushClient(AES.decrypt2Str(masterkey), AES.decrypt2Str(appkey)) });
		}
	}

	public static void appendiSurpassPushClient()
	{
		synchronized (jpushmap)
		{
			JPushClient[] jc = jpushmap.get(PLATFORM_SINGAPORE);
			if (jc == null)
			{
				log.error("JPush Client not init.");
				return;
			}
			for (JPushClient j : jc)
			{
				if (j == iSurpassPushClent)
				{
					log.info("iSurpass JPush clent have been appended");
					return;
				}
			}

			jc = new JPushClient[] { jc[0], iSurpassPushClent };
			jpushmap.put(PLATFORM_SINGAPORE, jc);
		}
	}

	public static void push(Payload payload, int platform)
	{
		JPushClient[] jpush = jpushmap.get(platform);

		if (jpush == null)
		{
			log.error(String.format("Unknow platform %d", platform));
			return;
		}

		String str = JSON.toJSONString(payload, SerializerFeature.DisableCircularReferenceDetect);
		log.info(str);

		for (int i = 0; i < jpush.length; i++)
		{
			try
			{
				PushResult result = jpush[i].sendPush(str);

				if (log.isInfoEnabled())
					log.info(String.format("msg id:%s,result:%s", result.msg_id, result.isResultOK()));
			}
			catch (APIRequestException e)
			{
				if (e.getErrorCode() == 1011)
					log.info(e.getMessage());
				else
					log.error(e.getMessage(), e);
			}
			catch (Throwable t)
			{
				log.error(t.getMessage(), t);
			}
		}
	}

}
