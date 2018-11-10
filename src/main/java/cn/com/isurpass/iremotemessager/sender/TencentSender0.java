package cn.com.isurpass.iremotemessager.sender;

import org.springframework.stereotype.Component;

@Component("cn.com.isurpass.iremotemessager.sender.TencentSender0")
public class TencentSender0 extends TencentSender
{
	private static final String SMSSIGN = "\u3010\u5C0F\u767D\u7BA1\u5BB6\u3011";
	
	public TencentSender0()
	{
		super(SMSSIGN);
	}
}
