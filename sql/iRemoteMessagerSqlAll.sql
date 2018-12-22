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

/*Table structure for table `msg_contenttemplate` */

DROP TABLE IF EXISTS `msg_contenttemplate`;

CREATE TABLE `msg_contenttemplate` (
  `msgcontenttemplateid` int(9) NOT NULL AUTO_INCREMENT,
  `platform` int(9) NOT NULL,
  `msgeventtypeid` int(9) NOT NULL,
  `eventcode` varchar(128) NOT NULL,
  `language` varchar(16) NOT NULL,
  `type` int(9) NOT NULL,
  `contenttemplate` varchar(4098) NOT NULL,
  `createtime` datetime NOT NULL,
  `lastupdatetime` datetime NOT NULL,
  PRIMARY KEY (`msgcontenttemplateid`)
) ENGINE=InnoDB AUTO_INCREMENT=7 DEFAULT CHARSET=utf8;

/*Data for the table `msg_contenttemplate` */

insert  into `msg_contenttemplate`(`msgcontenttemplateid`,`platform`,`msgeventtypeid`,`eventcode`,`language`,`type`,`contenttemplate`,`createtime`,`lastupdatetime`) values 
(1,9,1,'devicestatus','en_US',1,'{\"eventcode\":\"${eventcode}\",\"platform\":\"${platform}\",\"language\":\"${language}\",\"type\":\"${type}\"}','2018-11-09 11:09:09','2018-11-09 11:09:13'),
(2,9,1,'devicestatus','en_US',2,'send sms test ${eventcode}','2018-11-09 13:50:05','2018-11-09 13:50:08'),
(3,9,1,'devicestatus','zh_CN',2,'本月已经给您发送了5条短信(不含本条)，达到短信月上限，本月内如有告警，将不再给您发送短信，请注意居家安全。','2018-11-10 15:56:17','2018-11-10 15:56:21'),
(4,9,1,'devicestatus','zh_CN',1,'{\"eventcode\":\"${eventcode}\",\"platform\":\"${platform}\",\"language\":\"${language}\",\"type\":\"${type}\"}','2018-11-12 10:04:09','2018-11-12 10:04:17'),
(5,9,1,'devicestatus','zh_CN',3,'mail title','2018-11-12 11:33:42','2018-11-12 11:44:04'),
(6,9,1,'devicestatus','zh_CN',4,'mail content','2018-11-12 11:45:10','2018-11-12 11:45:13');

/*Table structure for table `msg_defaultprocessclass` */

DROP TABLE IF EXISTS `msg_defaultprocessclass`;

CREATE TABLE `msg_defaultprocessclass` (
  `msgdefaultprocessclassid` int(9) NOT NULL AUTO_INCREMENT,
  `platform` int(9) NOT NULL,
  `type` int(9) NOT NULL,
  `subtype` int(9) DEFAULT NULL,
  `msgprocessclassid` int(9) NOT NULL,
  `eventcode` varchar(128) NOT NULL,
  PRIMARY KEY (`msgdefaultprocessclassid`)
) ENGINE=InnoDB AUTO_INCREMENT=11 DEFAULT CHARSET=utf8;

/*Data for the table `msg_defaultprocessclass` */

insert  into `msg_defaultprocessclass`(`msgdefaultprocessclassid`,`platform`,`type`,`subtype`,`msgprocessclassid`,`eventcode`) values 
(1,9,1,NULL,2,'defaultEventCode'),
(2,999999,2,NULL,4,'devicestatus'),
(3,999999,4,3,29,'defaultEventCode'),
(4,999999,4,4,28,'defaultEventCode'),
(5,999999,3,3,25,'defaultEventCode'),
(6,999999,3,1,21,'defaultEventCode'),
(7,999999,3,2,23,'defaultEventCode'),
(8,999999,3,4,24,'defaultEventCode'),
(9,999999,4,1,26,'defaultEventCode'),
(10,999999,4,2,27,'defaultEventCode');

/*Table structure for table `msg_eventgroup` */

DROP TABLE IF EXISTS `msg_eventgroup`;

CREATE TABLE `msg_eventgroup` (
  `msgeventgroupid` int(9) NOT NULL AUTO_INCREMENT,
  `platform` int(9) NOT NULL,
  `eventgroupname` varchar(256) NOT NULL,
  `decription` varchar(1024) DEFAULT NULL,
  `createtime` datetime NOT NULL,
  PRIMARY KEY (`msgeventgroupid`)
) ENGINE=InnoDB AUTO_INCREMENT=5 DEFAULT CHARSET=utf8;

/*Data for the table `msg_eventgroup` */

insert  into `msg_eventgroup`(`msgeventgroupid`,`platform`,`eventgroupname`,`decription`,`createtime`) values 
(4,9,'ameta事件组','ameta事件组ffdasf','2018-11-20 17:15:30');

/*Table structure for table `msg_eventgroupevent` */

DROP TABLE IF EXISTS `msg_eventgroupevent`;

CREATE TABLE `msg_eventgroupevent` (
  `msgeventgroupeventid` int(9) NOT NULL AUTO_INCREMENT,
  `msgeventgroupid` int(9) NOT NULL,
  `platform` int(9) NOT NULL,
  `msgeventtypeid` int(9) NOT NULL,
  `eventcode` varchar(128) NOT NULL,
  `createtime` datetime NOT NULL,
  PRIMARY KEY (`msgeventgroupeventid`)
) ENGINE=InnoDB AUTO_INCREMENT=7 DEFAULT CHARSET=utf8;

/*Data for the table `msg_eventgroupevent` */

insert  into `msg_eventgroupevent`(`msgeventgroupeventid`,`msgeventgroupid`,`platform`,`msgeventtypeid`,`eventcode`,`createtime`) values 
(4,4,9,1,'devicestatus','2018-11-20 17:18:50'),
(5,4,9,2,'warning','2018-11-20 17:18:50'),
(6,4,9,3,'battery','2018-11-20 17:18:50');

/*Table structure for table `msg_eventtype` */

DROP TABLE IF EXISTS `msg_eventtype`;

CREATE TABLE `msg_eventtype` (
  `msgeventtypeid` int(9) NOT NULL AUTO_INCREMENT,
  `eventtypename` varchar(256) NOT NULL,
  `eventcode` varchar(128) NOT NULL,
  `decription` varchar(1024) DEFAULT NULL,
  `createtime` datetime NOT NULL,
  PRIMARY KEY (`msgeventtypeid`)
) ENGINE=InnoDB AUTO_INCREMENT=5 DEFAULT CHARSET=utf8;

/*Data for the table `msg_eventtype` */

insert  into `msg_eventtype`(`msgeventtypeid`,`eventtypename`,`eventcode`,`decription`,`createtime`) values 
(1,'设备状态','devicestatus','设备状态改变','2018-11-03 15:29:12'),
(2,'告警','warning','告警 warning','2018-11-07 16:44:18'),
(3,'电量','battery','battery','2018-11-20 17:18:07'),
(4,'一个测试的事件','atesteventcode','测试ativemq的订阅','2018-11-24 14:53:37');

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
(31,'TencentSender','cn.com.isurpass.iremotemessager.sender.TencentSender0',4,3,'TencentSender0','2018-11-13 11:11:19');

/*Table structure for table `msg_pushsetting` */

DROP TABLE IF EXISTS `msg_pushsetting`;

CREATE TABLE `msg_pushsetting` (
  `msgpushsettingid` int(9) NOT NULL AUTO_INCREMENT,
  `platform` int(9) NOT NULL,
  `msgeventgroupid` int(9) NOT NULL,
  `msgpushtargetdecisionid` int(9) NOT NULL,
  `msgpushmethodid` int(9) NOT NULL,
  `createtime` datetime NOT NULL,
  PRIMARY KEY (`msgpushsettingid`)
) ENGINE=InnoDB AUTO_INCREMENT=5 DEFAULT CHARSET=utf8;

/*Data for the table `msg_pushsetting` */

insert  into `msg_pushsetting`(`msgpushsettingid`,`platform`,`msgeventgroupid`,`msgpushtargetdecisionid`,`msgpushmethodid`,`createtime`) values 
(4,9,4,4,16,'2018-11-20 17:24:55');

/*Table structure for table `msg_pushsettingdtl` */

DROP TABLE IF EXISTS `msg_pushsettingdtl`;

CREATE TABLE `msg_pushsettingdtl` (
  `msgpushsettingdtlid` int(9) NOT NULL AUTO_INCREMENT,
  `msgpushsettingid` int(9) NOT NULL,
  `type` int(9) NOT NULL,
  `subtype` int(9) DEFAULT NULL,
  `msgprocessclassid` int(9) NOT NULL,
  PRIMARY KEY (`msgpushsettingdtlid`)
) ENGINE=InnoDB AUTO_INCREMENT=12 DEFAULT CHARSET=utf8;

/*Data for the table `msg_pushsettingdtl` */

insert  into `msg_pushsettingdtl`(`msgpushsettingdtlid`,`msgpushsettingid`,`type`,`subtype`,`msgprocessclassid`) values 
(9,4,4,1,26),
(10,4,4,3,31),
(11,4,4,4,28);

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;
