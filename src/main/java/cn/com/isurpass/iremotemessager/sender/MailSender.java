package cn.com.isurpass.iremotemessager.sender;

import cn.com.isurpass.iremotemessager.common.util.EmailUtils;
import cn.com.isurpass.iremotemessager.framework.IMessageSender;
import cn.com.isurpass.iremotemessager.jms.JSMTaskManager;
import cn.com.isurpass.iremotemessager.messageparser.MailParser;
import cn.com.isurpass.iremotemessager.vo.EventData;
import cn.com.isurpass.iremotemessager.vo.MailData;
import cn.com.isurpass.iremotemessager.vo.SendResult;
import org.springframework.stereotype.Component;

import java.util.List;

@Component("cn.com.isurpass.iremotemessager.sender.MailSender")
public class MailSender implements IMessageSender<MailData>{
    public static final String MAIL_DEFAULT_TASK_QUEUE = "mailDefaultTaskQueue";
    @Override
    public List<SendResult> send(EventData data, MailData targetdata) {
        JSMTaskManager.addTask(MAIL_DEFAULT_TASK_QUEUE, ()->{
            EmailUtils.sendEmail(targetdata.getMails(), targetdata.getMailtitle(), targetdata.getMailcontent());
        });

        return null;
    }
	@Override
	public String getMessageParserClassName()
	{
		return MailParser.class.getName();
	}
}
