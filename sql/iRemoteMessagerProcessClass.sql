/*
SQLyog  v12.2.6 (64 bit)
MySQL - 5.5.56 : Database - iremote
*********************************************************************
*/


/*!40101 SET NAMES utf8 */;

/*!40101 SET SQL_MODE=''*/;

/*!40014 SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0 */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;
/*!40111 SET @OLD_SQL_NOTES=@@SQL_NOTES, SQL_NOTES=0 */;
CREATE DATABASE /*!32312 IF NOT EXISTS*/`iremote` /*!40100 DEFAULT CHARACTER SET utf8 */;

USE `iremote`;

/*Table structure for table `msg_processclass` */

DROP TABLE IF EXISTS `msg_processclass`;

CREATE TABLE `msg_processclass` (
  `msgprocessclassid` int(9) NOT NULL AUTO_INCREMENT,
  `name` varchar(1024) NOT NULL,
  `classname` varchar(1024) NOT NULL,
  `type` int(9) NOT NULL,
  `subtype` int(9) DEFAULT NULL,
  `decription` varchar(1024) DEFAULT NULL,
  `createtime` datetime NOT NULL,
  PRIMARY KEY (`msgprocessclassid`)
) ENGINE=InnoDB AUTO_INCREMENT=32 DEFAULT CHARSET=utf8;

/*Data for the table `msg_processclass` */

insert  into `msg_processclass`(`msgprocessclassid`,`name`,`classname`,`type`,`subtype`,`decription`,`createtime`) values 

(1,'仅主人','cn.com.isurpass.iremotemessager.targetdecision.OwnerTargetDecision',1,NULL,'推送给主人','2018-11-03 15:22:16'),

(2,'家庭成员','cn.com.isurpass.iremotemessager.targetdecision.FamilyMemeberTargetDecision',1,NULL,'推送给家庭成员','2018-11-03 15:23:09'),

(3,'家庭成员和朋友','cn.com.isurpass.iremotemessager.targetdecision.FamilyandFriendsTargetDecision',1,NULL,'推送给家庭成员和朋友','2018-11-03 15:24:10'),

(4,'有操作权限的人','cn.com.isurpass.iremotemessager.targetdecision.CanOperatePeopleTargetDecision',1,NULL,'推送给有操作权限的人','2018-11-03 15:24:57'),

(5,'被授权人','cn.com.isurpass.iremotemessager.targetdecision.AuthorizedPersonTargetDecision',1,NULL,'推送给被授权人','2018-11-06 11:56:58'),

(6,'授权人和被授权人','cn.com.isurpass.iremotemessager.targetdecision.AuthorizerAndAuthorizedPersonTargetDecision',1,NULL,'推送给授权人和被授权人','2018-11-06 11:57:49'),

(7,'指定的手机号码','cn.com.isurpass.iremotemessager.targetdecision.PhoneNumberTargetDecision',1,NULL,'发短信给指定的手机号码','2018-11-06 12:00:12'),

(8,'指定的邮箱','cn.com.isurpass.iremotemessager.targetdecision.MailTargetDecision',1,NULL,'发邮件给指定的邮箱','2018-11-08 19:58:22'),

(9,'网关原主人','cn.com.isurpass.iremotemessager.targetdecision.GatewayOriginalOwnerTargetDecision',1,NULL,'推送给网关原主人','2018-11-12 09:41:36'),

(10,'App自定义消息推送','cn.com.isurpass.iremotemessager.methoddecision.JPushMessageMethodDecision',2,NULL,'App自定义消息','2018-11-12 09:45:30'),

(11,'App告警消息推送','cn.com.isurpass.iremotemessager.methoddecision.JPushNotificationMethodDecision',2,NULL,'App告警','2018-11-12 10:36:07'),

(12,'按电源供电状态推送','cn.com.isurpass.iremotemessager.methoddecision.JPushNotificationByPowerStatusMethodDecision',2,NULL,'适配器供电时推送消息,电池供电时推送告警','2018-11-12 11:50:48'),

(13,'按情景推送','cn.com.isurpass.iremotemessager.methoddecision.JPushNotificationBySceneSettingMethodDecision',2,NULL,'按情景推送','2018-11-13 10:56:04'),

(14,'按告警状态推送','cn.com.isurpass.iremotemessager.methoddecision.JPushNotificationByWarningStatusMethodDecision',2,NULL,'按告警状态推送','2018-11-13 10:56:45'),

(15,'推送告警和邮件','cn.com.isurpass.iremotemessager.methoddecision.JPushNotificationMailMethodDecision',2,NULL,'推送告警和邮件','2018-11-13 10:57:49'),

(16,'推送告警,邮件,短信','cn.com.isurpass.iremotemessager.methoddecision.JPushNotificationMailSmsMethodDecision',2,NULL,'推送告警,邮件,短信','2018-11-13 10:58:21'),

(17,'推送告警','cn.com.isurpass.iremotemessager.methoddecision.JPushNotificationMethodDecision',2,NULL,'推送告警','2018-11-13 10:59:05'),

(18,'仅推送邮件','cn.com.isurpass.iremotemessager.methoddecision.MailMethodDecision',2,NULL,'仅推送邮件','2018-11-13 11:00:20'),

(19,'仅短信','cn.com.isurpass.iremotemessager.methoddecision.SmsMethodDecision',2,NULL,'仅短信','2018-11-13 11:00:58'),

(20,'按时间窗设置推送','cn.com.isurpass.iremotemessager.methoddecision.NotificationTimeWindownSettingMethodDecision',2,NULL,'按时间窗设置推送','2018-11-13 11:01:26'),

(21,'InfoChangedParser','cn.com.isurpass.iremotemessager.messageparser.JPushMessageInfoChangedParser',3,1,'InfoChanged内容生成','2018-11-13 11:03:52'),

(22,'JPushMessageParser','cn.com.isurpass.iremotemessager.messageparser.JPushMessageParser',3,1,'JPushMessageParser','2018-11-13 11:04:34'),

(23,'JPushNotificationParser','cn.com.isurpass.iremotemessager.messageparser.JPushNotificationParser',3,2,'JPushNotificationParser','2018-11-13 11:04:52'),

(24,'MailParser','cn.com.isurpass.iremotemessager.messageparser.MailParser',3,4,'MailParser','2018-11-13 11:05:17'),

(25,'SMSParser','cn.com.isurpass.iremotemessager.messageparser.SmsParser',3,3,'SMSParser','2018-11-13 11:05:56'),

(26,'JPuShMessageSender','cn.com.isurpass.iremotemessager.sender.JPushMessageSender',4,1,'JPushMessageSender','2018-11-13 11:08:45'),

(27,'JPushNotification','cn.com.isurpass.iremotemessager.sender.JPushNotificationSender',4,2,'JPushNotification','2018-11-13 11:09:11'),

(28,'MailSender','cn.com.isurpass.iremotemessager.sender.MailSender',4,4,'MailSender','2018-11-13 11:09:35'),

(29,'AmetaPhoneUserSmsSender','cn.com.isurpass.iremotemessager.sender.AmetaPhoneUserSmsSender',4,3,'AmetaPhoneUserSmsSender','2018-11-13 11:10:14'),

(30,'NorthAmericanPhoneUserSmsSender','cn.com.isurpass.iremotemessager.sender.NorthAmericanPhoneUserSmsSender',4,3,'NorthAmericanPhoneUserSmsSender','2018-11-13 11:10:38'),

(31,'TencentSender','cn.com.isurpass.iremotemessager.sender.TencentSender0',4,3,'TencentSender0','2018-11-13 11:11:19'),

(32, '腾讯国内短信', 'cn.com.isurpass.iremotemessager.sender.TencentOnlyDomesticSender', 4,3,'TencentOnlyDomesticSender','2018-12-10 18:00:00'),

(33, '邮箱或者手机号码', 'cn.com.isurpass.iremotemessager.targetdecision.MailOrPhoneNumberTargetDecision', 1,NULL,'邮箱或者手机号码二选一,根据推送的参数判断','2018-12-17 11:00:00'),

(34, '设置网关主人', 'cn.com.isurpass.iremotemessager.targetdecision.RemoteOwnerSettingTargetDecision', 1,NULL,'推送给网关主人','2018-12-17 11:00:00'),

(35, '邮箱和短信', 'cn.com.isurpass.iremotemessager.methoddecision.MailAndSmsMethodDecision', 2,NULL,'邮箱和短信','2018-12-18 18:00:00'),

(36, '按通知App和Mail值推送', 'cn.com.isurpass.iremotemessager.methoddecision.JPushNotificationByAppMailSettingMethodDecision', 2,NULL,'按 Notificationsetting.App 和 Notificationsetting.Mail 值推送','2018-12-19 18:00:00');


/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;
