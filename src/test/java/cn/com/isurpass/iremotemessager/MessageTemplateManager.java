package cn.com.isurpass.iremotemessager;

import cn.com.isurpass.iremotemessager.dao.MsgContentTemplateDao;
import cn.com.isurpass.iremotemessager.domain.MsgContentTemplate;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Iterator;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * 针对数据库中的模版不正确,使用此脚本针对性的进行整理
 */
@RunWith(SpringRunner.class)
@SpringBootTest
public class MessageTemplateManager {
    private static final String MESSAGE = "\"message\":";
    private static final String MESSAGET_TO_EVENT_TYPE = "\"message\":\"${eventtype}\",";
    private static final String MESSAGE_2 = "message??>";
    private static final String EVENT_TYPE_2 = "eventtype??>";
    private static final String MESSAGE_3 = "$\\{message!}";
    private static final String EVENT_TYPE_3 = "${eventtype}";
    private static final String MESSAGE_4 = "${message}";

    @Resource
    private MsgContentTemplateDao msgContentTemplateDao;

    /**
     * <p>
     * 现状:
     * 1.目前数据库中的 json 模版或是缺少了 message 字段,或是 message字段不正确
     * 具体情况有三种:
     * 1) 缺少 message 字段: 使用 "message": 对整个字符串进行搜索, 如果不存在此字符,
     * 则在json的第一个元素之前添加 "message":"${eventtype}",
     * 2)
     * a) 使用 if 形式的 message, 通过搜索 message??> , 如果存在, 则用 eventtype??> 替换 message??>,
     * 用 ${eventtype} 替换 ${message}
     * b) 使用 ${message!} 形式的, 直接用 ${eventtype} 替换
     */
//    @Test
    public void manager() {
        String[] codeArray = {"dooropen", "doorclose","moveout", "smoke","waterleak","gasleak","tampleralarm","sos",
            "doorlockopen","passworderror5times","lockkeyerror","lockkeyevent","locklockerror","bulliedopenlock",
            "dscalarm","movein","malfunction","lowbattery",
            "unalarmtampleralarm","unalarmsos","unalarmsmoke","unalarmgasleak","unalarmwaterleak","unalarmpassworderror5times",
        "unalarmlockkeyerror","unalarmmovein","unalarmdooropen","unalarmdoorlockopen","unalarmbulliedopenlock","unalarmlocklockerror",
        "unalarmlockkeyevent","unalarmcameradectectmove","unalarmdscalarm"};
        List<MsgContentTemplate> list = msgContentTemplateDao.findByTypeAndEventcodeIn(1, Arrays.asList(codeArray));

        Iterator<MsgContentTemplate> templateIterator = list.iterator();
        while (templateIterator.hasNext()) {
            MsgContentTemplate template = templateIterator.next();
            String content = template.getContenttemplate();
            String s;
            if (containsMessage(content)) {
                if (hasIfforMessage(content)) {
                    s = content.replace(MESSAGE_2, EVENT_TYPE_2).replace(MESSAGE_4, EVENT_TYPE_3);
                } else if (hasDirectlyMessage(content)) {
                    s = content.replace(MESSAGE_3, EVENT_TYPE_3);
                } else {
                    s = content;
                }

            } else {
                s = content.replace("\\{", "{" + MESSAGET_TO_EVENT_TYPE);
            }

//            System.out.println(s);
            template.setContenttemplate(s);
            msgContentTemplateDao.save(template);
        }
    }

    /**
     * 对 $(**) 形式的错误模版, 使用 ${**} 替换
     */
//    @Test
    public void manager2(){

        List<MsgContentTemplate> list = msgContentTemplateDao.findByType(1);

        Pattern pattern = Pattern.compile("\\$\\((.*?)\\)");

        Iterator<MsgContentTemplate> iterator = list.iterator();
        String s;
        while (iterator.hasNext()) {
            MsgContentTemplate template = iterator.next();
            s = template.getContenttemplate();
            Matcher matcher = pattern.matcher(s);
            while (matcher.find()) {
                s = s.replace(matcher.group(), "${" + matcher.group(1) + "}");
            }
//            System.out.println(s);
            template.setContenttemplate(s);
            msgContentTemplateDao.save(template);
        }
        System.out.println("=============================");

    }

//    @Test
    public void validate(){
        List<MsgContentTemplate> list = msgContentTemplateDao.findByType(1);

        for (MsgContentTemplate template : list) {
            if (template.getContenttemplate().contains("${message}")) {
//                list1.add(template.getMsgcontenttemplateid() + "-" + template.getMsgEventType().getEventcode());
                String s = template.getContenttemplate().replace("message??","eventtype??")
                        .replace("${message}","${eventtype}");
                template.setContenttemplate(s);
                msgContentTemplateDao.save(template);
            }
        }
    }

    private boolean hasDirectlyMessage(String content) {
        return content.contains(MESSAGE_3);
    }

    private boolean hasIfforMessage(String content) {
        return content.contains(MESSAGE_2);
    }

    private boolean containsMessage(String content) {
        return content.contains(MESSAGE);
    }
}
