package cn.com.isurpass.iremotemessager.constant;

import java.util.ArrayList;
import java.util.List;

public enum MsgMethodType
{
	blank(),
	jpushmessage(MsgTemplateType.json),
	jpushnotification(MsgTemplateType.json,MsgTemplateType.sentence),
	sms(MsgTemplateType.sentence),
	mail(MsgTemplateType.mailtitle,MsgTemplateType.mailcontent);
	
	private List<Integer> templatetype;
	
	private MsgMethodType(MsgTemplateType... templatetype)
	{
		
		this.templatetype = new ArrayList<Integer>();
		if ( templatetype != null && templatetype.length > 0 )
		{
			for ( MsgTemplateType tt : templatetype)
				this.templatetype.add(tt.ordinal());
		}
		
	}

	public List<Integer> getTemplatetype()
	{
		return templatetype;
	}

	
}
