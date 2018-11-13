package cn.com.isurpass.iremotemessager.messageparser;

import cn.com.isurpass.iremotemessager.common.constant.MsgTemplateType;
import cn.com.isurpass.iremotemessager.framework.IMessageParser;
import cn.com.isurpass.iremotemessager.vo.EventData;
import cn.com.isurpass.iremotemessager.vo.MailData;
import org.springframework.stereotype.Component;

import java.util.Arrays;
import java.util.List;

@Component("cn.com.isurpass.iremotemessager.messageparser.Mailparser")
public class MailParser  implements IMessageParser<MailData>{

    @Override
    public List<MailData> parse(EventData data, MailData targetdata) {
        MessageParser messageParser = new MessageParser(data, targetdata.getLanguage(), MsgTemplateType.mailtitle);

        String title = messageParser.getMessage();

        messageParser.setType(MsgTemplateType.mailcontent);

        String content = messageParser.getMessage();

        targetdata.setMailtitle(title);
        targetdata.setMailcontent(content);

        return Arrays.asList(new MailData[]{targetdata});
    }
}
