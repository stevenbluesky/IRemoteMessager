package cn.com.isurpass.iremotemessager;

import cn.com.isurpass.iremotemessager.common.util.EmailUtils;
import cn.com.isurpass.iremotemessager.dao.MsgEventGroupDao;
import cn.com.isurpass.iremotemessager.dao.MsgPushSettingDao;
import cn.com.isurpass.iremotemessager.domain.MsgPushSetting;
import cn.com.isurpass.iremotemessager.service.*;
import com.alibaba.fastjson.JSONObject;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.NoSuchBeanDefinitionException;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.TransactionStatus;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.transaction.support.DefaultTransactionDefinition;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@RunWith(SpringRunner.class)
@SpringBootTest
public class IremotemessagerApplicationTests {
    @Resource
    private UserShareService userShareService;
    @Resource
    private UserShareDeviceService userShareDeviceService;
    @Resource
    private MsgEventGroupeventService msgEventGroupeventService;
    @Resource
    private MsgPushSettingService msgPushSettingService;
    @Resource
    private MsgEventTypeService msgEventTypeService;
    @Resource
    private MsgPushSettingDao msgPushSettingDao;
    @Resource
    private MsgEventGroupDao msgEventGroupDao;

    @Transactional
    @Test
    public void contextLoads() {
        JSONObject json = new JSONObject();
        ArrayList<String> s = new ArrayList<>();
        s.add("86");
        s.add("123123");
        ArrayList<String> s1 = new ArrayList<>();
        s1.add("86");
        s1.add("123123");
        ArrayList<List> lists = new ArrayList<>();
        lists.add(s);
        lists.add(s1);
        json.put("phone", lists);
        System.out.println(json.toJSONString());

        String[][] phones = json.getObject("phone", String[][].class);

        for (int i = 0; i < phones.length; i++) {
            for (int j = 0; j < phones[i].length; j++) {
                System.out.println(phones[i][j]);
            }
        }
    }

    @Test
    @org.springframework.transaction.annotation.Transactional(readOnly = true)
    public void testNativeQuerySentence() {
        String type = "devicestatus";
        int platfom = 9;
        String devicestatus = msgEventGroupeventService.findMsgPushTargetDecisionClassName("devicestatus", platfom);
        String methodClassName = msgEventGroupeventService.findMsgPushMethodClassName("devicestatus", platfom);

        System.out.println(devicestatus);
        System.out.println(methodClassName);

        Map<Integer, String> parserMap = msgPushSettingService.findParserMap(type, platfom);
        Map<Integer, String> senderMap = msgPushSettingService.findSenderMap(type, platfom);

    }

    @Test
    public void testPushSetting() {
        MsgPushSetting devicestatus = msgPushSettingService.findPushSetting("devicestatus", 9);
        System.out.println(devicestatus);
    }

    @Test
    public void testGetBean() {
        try {
            SpringUtil.getBean("cn.com.isurpass.iremotemessager.sender.MailSender", Class.forName("cn.com.isurpass.iremotemessager.sender.MailSender"));
        } catch (ClassNotFoundException e) {
            System.out.println("None");
//			e.printStackTrace();
        } catch (NoSuchBeanDefinitionException e) {
            try {
                SpringUtil.getBean(Class.forName("cn.com.isurpass.iremotemessager.sender.MailSender"));
            } catch (ClassNotFoundException e1) {
                e1.printStackTrace();
            }
        }
    }

//    @Test
    @Transactional
    public void testSendMail() {
		/*ArrayList<String>maiList = new ArrayList<>();
		maiList.add("zhulei@isurpass.com.cn");
		MailInterface.sendMail(maiList, "Test", "Just test");*/
        String[] users = {"zhulei@isurpass.com.cn"};
//		EmailUtils.sendEmail("title", users, null, "content...", null);
        EmailUtils.sendEmail(users, "title", "content..");
        EmailUtils.sendEmail(users, "sfsfsafsfsa", "asfsadfasfsdfadsfasfasf..");
    }

    @Test
    public void testQueryEventCode() {
        List<String> allEventCode = msgEventTypeService.findAllEventCode();
        System.out.println(allEventCode);
    }

    @Test
    public void testSQL(){
//        MsgPushSetting msgPushSetting = msgPushSettingDao.findById(3).orElse(null);
//        msgPushSettingDao.delete(msgPushSetting);
            msgPushSettingDao.deleteById(1);
//            msgEventGroupDao.deleteById(2);
    }
}
