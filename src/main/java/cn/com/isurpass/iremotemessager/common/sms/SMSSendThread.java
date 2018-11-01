package cn.com.isurpass.iremotemessager.common.sms;

import cn.com.isurpass.iremotemessager.vo.SMSVo;
import org.apache.commons.lang.StringUtils;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

public class SMSSendThread implements Runnable
{
	private static Log log = LogFactory.getLog(SMSSendThread.class);
	
	SMSVo vo;

	public SMSSendThread(SMSVo vo) {
		super();
		this.vo = vo;
	}

	@Override
	public void run() 
	{
		if ( vo != null && StringUtils.isNotBlank(vo.getMessage()) ){
			if ( log.isInfoEnabled())
				log.info(String.format("send SMS %s to %s ", vo.getMessage(), vo.getPhonenumber()));
			SMSInterface.sendSMS(vo.getCountrycode(),vo.getPhonenumber(), vo.getMessage() , vo.getPlatform());
		}
	}
}
