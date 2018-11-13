package cn.com.isurpass.iremotemessager.common.util;

import cn.com.isurpass.iremotemessager.SpringUtil;
import cn.com.isurpass.iremotemessager.common.constant.IRemoteConstantDefine;
import cn.com.isurpass.iremotemessager.service.SystemParameterService;
import com.sun.mail.util.MailSSLSocketFactory;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.mail.javamail.JavaMailSenderImpl;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Component;

import javax.mail.internet.MimeMessage;
import java.io.File;
import java.security.GeneralSecurityException;
import java.util.List;
import java.util.Map;
import java.util.Properties;

@Component
public class EmailUtils {

    private static final Logger log = LoggerFactory.getLogger(EmailUtils.class);
    private static SystemParameterService systemParameterService;
    private static String auth = "true";
    private static String host;
    private static String protocol = "smtp";
    private static Integer port;
    private static String authName;
    private static String password;
    private static boolean isSSL = true;
    private static String charset = "UTF-8";
    private static String timeout = "5000";

    static {
        systemParameterService = SpringUtil.getBean(SystemParameterService.class);

        host = systemParameterService.getStringValue(IRemoteConstantDefine.SYSTEMPARAMETER_USER_MAIL_SERVER_HOST);
        port = Integer.valueOf(systemParameterService.getStringValue(IRemoteConstantDefine.SYSTEMPARAMETER_USER_MAIL_SERVER_POST));
        isSSL = Boolean.valueOf(systemParameterService.getStringValue(IRemoteConstantDefine.SYSTEMPARAMETER_USER_MAIL_VALIDATE));
        password = systemParameterService.getStringValue(IRemoteConstantDefine.SYSTEMPARAMETER_USER_MAIL_PASSWORD);
        authName = systemParameterService.getStringValue(IRemoteConstantDefine.SYSTEMPARAMETER_USER_MAIL_FROMADDRESS);
//        authName = systemParameterService.getStringValue(IRemoteConstantDefine.SYSTEMPARAMETER_USER_MAIL_USERNAME);
    }

    public static boolean sendEmail(String[] toUsers, String subject, String content) {
        return sendEmail(subject, toUsers, null, content, null);
    }

   /**
    * 发送邮件
    * @param subject 主题
    * @param toUsers 收件人
    * @param ccUsers 抄送
    * @param content 邮件内容
    * @param attachfiles 附件列表  List<Map<String, String>> key: name && file
    */
   public static boolean sendEmail(String subject, String[] toUsers, String[] ccUsers, String content, List<Map<String, String>> attachfiles) {
       boolean flag = true;
       try {
           JavaMailSenderImpl javaMailSender = new JavaMailSenderImpl();
           javaMailSender.setHost(host);
           javaMailSender.setUsername(authName);
           javaMailSender.setPassword(password);
           javaMailSender.setDefaultEncoding(charset);
           javaMailSender.setProtocol(protocol);
           javaMailSender.setPort(port);

           Properties properties = new Properties();
           properties.setProperty("mail.smtp.auth", auth);
           properties.setProperty("mail.smtp.timeout", timeout);
           if(isSSL){
               MailSSLSocketFactory sf = null;
               try {
                   sf = new MailSSLSocketFactory();
                   sf.setTrustAllHosts(true);
                   properties.put("mail.smtp.ssl.enable", "true");
                   properties.put("mail.smtp.ssl.socketFactory", sf);
               } catch (GeneralSecurityException e) {
                   e.printStackTrace();
               }
           }
           javaMailSender.setJavaMailProperties(properties);

           MimeMessage mailMessage = javaMailSender.createMimeMessage();
           MimeMessageHelper messageHelper = new MimeMessageHelper(mailMessage, true);
           messageHelper.setTo(toUsers);
           if (ccUsers != null && ccUsers.length > 0) {
               messageHelper.setCc(ccUsers);
           }
           messageHelper.setFrom(authName);
           messageHelper.setSubject(subject);
           messageHelper.setText(content, true);

           if (attachfiles != null && attachfiles.size() > 0) {
               for (Map<String, String> attachfile : attachfiles) {
                   String attachfileName = attachfile.get("name");
                   File file = new File(attachfile.get("file"));
                   messageHelper.addAttachment(attachfileName, file);
               }
           }

           log.info("Send mail to ["+toUsers.toString()+"], subject=["+subject+"], content=["+content+"]");
           javaMailSender.send(mailMessage);
       } catch (Exception e) {
           log.error("Send mail failed!", e);
           flag = false;
       }
       return flag;
   }
}