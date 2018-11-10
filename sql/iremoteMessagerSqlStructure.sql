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
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

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
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

/*Table structure for table `msg_eventgroup` */

DROP TABLE IF EXISTS `msg_eventgroup`;

CREATE TABLE `msg_eventgroup` (
  `msgeventgroupid` int(9) NOT NULL AUTO_INCREMENT,
  `platform` int(9) NOT NULL,
  `eventgroupname` varchar(256) NOT NULL,
  `decription` varchar(1024) DEFAULT NULL,
  `createtime` datetime NOT NULL,
  PRIMARY KEY (`msgeventgroupid`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

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
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

/*Table structure for table `msg_eventtype` */

DROP TABLE IF EXISTS `msg_eventtype`;

CREATE TABLE `msg_eventtype` (
  `msgeventtypeid` int(9) NOT NULL AUTO_INCREMENT,
  `eventtypename` varchar(256) NOT NULL,
  `eventcode` varchar(128) NOT NULL,
  `decription` varchar(1024) DEFAULT NULL,
  `createtime` datetime NOT NULL,
  PRIMARY KEY (`msgeventtypeid`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

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
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

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
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

/*Table structure for table `msg_pushsettingdtl` */

DROP TABLE IF EXISTS `msg_pushsettingdtl`;

CREATE TABLE `msg_pushsettingdtl` (
  `msgpushsettingdtlid` int(9) NOT NULL AUTO_INCREMENT,
  `msgpushsettingid` int(9) NOT NULL,
  `type` int(9) NOT NULL,
  `subtype` int(9) DEFAULT NULL,
  `msgprocessclassid` int(9) NOT NULL,
  PRIMARY KEY (`msgpushsettingdtlid`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;
