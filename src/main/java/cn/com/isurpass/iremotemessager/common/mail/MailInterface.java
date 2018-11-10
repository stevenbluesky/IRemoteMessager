package cn.com.isurpass.iremotemessager.common.mail;

import cn.com.isurpass.iremotemessager.SpringUtil;
import cn.com.isurpass.iremotemessager.common.constant.IRemoteConstantDefine;
import cn.com.isurpass.iremotemessager.service.SystemParameterService;
import cn.com.isurpass.iremotemessager.vo.MailSendVo;
import org.apache.commons.lang.StringUtils;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.List;

@Component
public class MailInterface {
    private static Log log = LogFactory.getLog(MailInterface.class);
    private static SystemParameterService systemParameterService;

    static {
         systemParameterService = SpringUtil.getBean(SystemParameterService.class);
    }

    public static boolean sendMail(List<String> mail, String subject, String content){

        try{
            MailSendVo mailInfo = new MailSendVo();
            mailInfo.setMailServerHost(systemParameterService.getStringValue(IRemoteConstantDefine.SYSTEMPARAMETER_USER_MAIL_SERVER_HOST));
            mailInfo.setMailServerPost(systemParameterService.getStringValue(IRemoteConstantDefine.SYSTEMPARAMETER_USER_MAIL_SERVER_POST));
            mailInfo.setValidate(Boolean.valueOf(systemParameterService.getStringValue(IRemoteConstantDefine.SYSTEMPARAMETER_USER_MAIL_VALIDATE)));
            mailInfo.setUserName(systemParameterService.getStringValue(IRemoteConstantDefine.SYSTEMPARAMETER_USER_MAIL_USERNAME));
            mailInfo.setPassWord(systemParameterService.getStringValue(IRemoteConstantDefine.SYSTEMPARAMETER_USER_MAIL_PASSWORD));
            mailInfo.setFromAddress(systemParameterService.getStringValue(IRemoteConstantDefine.SYSTEMPARAMETER_USER_MAIL_FROMADDRESS));
            mailInfo.setToAddress(mail);
            mailInfo.setSubject(subject);
            mailInfo.setContent(content);
            if(mail==null||mail.size()==0){
            	return false;
            }
            log.info("mail data:" + mailInfo.toString());
            new Thread(() -> EmailUtil.sendHtmlMail(mailInfo)).start();
        }catch (Exception e){
            log.error("" , e);
            return false;
        }
        return true;
    }

    public static boolean sendUserMail(List<String> mail, String subject, String content){
        SystemParameterService sps = new SystemParameterService();
        try{
            MailSendVo mailInfo = new MailSendVo();
            mailInfo.setMailServerHost(sps.getStringValue(IRemoteConstantDefine.SYSTEMPARAMETER_USER_MAIL_SERVER_HOST));
            mailInfo.setMailServerPost(sps.getStringValue(IRemoteConstantDefine.SYSTEMPARAMETER_USER_MAIL_SERVER_POST));
            mailInfo.setValidate(Boolean.valueOf(sps.getStringValue(IRemoteConstantDefine.SYSTEMPARAMETER_USER_MAIL_VALIDATE)));
            mailInfo.setUserName(sps.getStringValue(IRemoteConstantDefine.SYSTEMPARAMETER_USER_MAIL_USERNAME));
            mailInfo.setPassWord(sps.getStringValue(IRemoteConstantDefine.SYSTEMPARAMETER_USER_MAIL_PASSWORD));
            mailInfo.setFromAddress(sps.getStringValue(IRemoteConstantDefine.SYSTEMPARAMETER_USER_MAIL_FROMADDRESS));
            mailInfo.setToAddress(mail);
            mailInfo.setSubject(subject);
            mailInfo.setContent(content);
            if(mail==null||mail.size()==0){
            	return false;
            }
            log.info("mail data:" + mailInfo.toString());
            new Thread(() -> EmailUtil.sendHtmlMail(mailInfo)).start();
        }catch (Exception e){
            log.error("" , e);
            return false;
        }
        return true;
    }

    public static boolean sendUserMail(String mail, String subject, String content){
        if(StringUtils.isEmpty(mail))
            return false;
        List<String> mailList = new ArrayList<>();
        mailList.add(mail);
        return sendUserMail(mailList,subject,content);
    }

    public static boolean sendSupportMail(String subject, String content){
        SystemParameterService sps = new SystemParameterService();
        try{
            MailSendVo mailInfo = new MailSendVo();
            mailInfo.setMailServerHost(sps.getStringValue(IRemoteConstantDefine.SYSTEMPARAMETER_SUPPORT_MAIL_SERVER_HOST));
            mailInfo.setMailServerPost(sps.getStringValue(IRemoteConstantDefine.SYSTEMPARAMETER_SUPPORT_MAIL_SERVER_POST));
            mailInfo.setValidate(Boolean.valueOf(sps.getStringValue(IRemoteConstantDefine.SYSTEMPARAMETER_SUPPORT_MAIL_VALIDATE)));
            mailInfo.setUserName(sps.getStringValue(IRemoteConstantDefine.SYSTEMPARAMETER_SUPPORT_MAIL_USERNAME));
            mailInfo.setPassWord(sps.getStringValue(IRemoteConstantDefine.SYSTEMPARAMETER_SUPPORT_MAIL_PASSWORD));
            mailInfo.setFromAddress(sps.getStringValue(IRemoteConstantDefine.SYSTEMPARAMETER_SUPPORT_MAIL_FROMADDRESS));
            mailInfo.getToAddress().add(sps.getStringValue(IRemoteConstantDefine.SYSTEMPARAMETER_SUPPORT_MAIL_TOADDRESS));
            mailInfo.setSubject(subject);
            mailInfo.setContent(content);
            new Thread(() -> EmailUtil.sendHtmlMail(mailInfo)).start();
        }catch (Exception e){
            log.error("" , e);
            return false;
        }
        return true;
    }

  /*  @Autowired
    public MailInterface(SystemParameterService systemParameterService) {
        MailInterface.systemParameterService = systemParameterService;
    }*/
}
