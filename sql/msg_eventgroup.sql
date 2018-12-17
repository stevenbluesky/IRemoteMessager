/*
SQLyog Ultimate v8.32 
MySQL - 5.5.49 
*********************************************************************
*/
/*!40101 SET NAMES utf8 */;

CREATE TABLE `msg_eventgroup` (
  `msgeventgroupid` int(9) NOT NULL AUTO_INCREMENT,
  `platform` int(9) NOT NULL,
  `eventgroupname` varchar(256) NOT NULL,
  `decription` varchar(1024) DEFAULT NULL,
  `createtime` datetime NOT NULL,
  PRIMARY KEY (`msgeventgroupid`)
) ENGINE=InnoDB AUTO_INCREMENT=31 DEFAULT CHARSET=utf8; 
insert into `msg_eventgroup` (`msgeventgroupid`, `platform`, `eventgroupname`, `decription`, `createtime`) values('16','0','用户信息事件组','','2018-12-14 16:51:37');
insert into `msg_eventgroup` (`msgeventgroupid`, `platform`, `eventgroupname`, `decription`, `createtime`) values('17','0','设备信息事件组','','2018-12-14 16:57:58');
insert into `msg_eventgroup` (`msgeventgroupid`, `platform`, `eventgroupname`, `decription`, `createtime`) values('18','0','二级告警事件组','','2018-12-14 16:58:08');
insert into `msg_eventgroup` (`msgeventgroupid`, `platform`, `eventgroupname`, `decription`, `createtime`) values('19','0','网关重置事件组','','2018-12-14 16:59:08');
insert into `msg_eventgroup` (`msgeventgroupid`, `platform`, `eventgroupname`, `decription`, `createtime`) values('20','0','网关电源事件组','','2018-12-14 16:59:20');
insert into `msg_eventgroup` (`msgeventgroupid`, `platform`, `eventgroupname`, `decription`, `createtime`) values('21','0','电量消息事件组','','2018-12-14 16:59:46');
insert into `msg_eventgroup` (`msgeventgroupid`, `platform`, `eventgroupname`, `decription`, `createtime`) values('22','0','设备状态事件组','','2018-12-14 17:00:48');
insert into `msg_eventgroup` (`msgeventgroupid`, `platform`, `eventgroupname`, `decription`, `createtime`) values('23','0','门铃响事件组','','2018-12-14 17:01:01');
insert into `msg_eventgroup` (`msgeventgroupid`, `platform`, `eventgroupname`, `decription`, `createtime`) values('24','0','情景执行事件组','','2018-12-14 17:01:46');
insert into `msg_eventgroup` (`msgeventgroupid`, `platform`, `eventgroupname`, `decription`, `createtime`) values('25','0','增加授权事件组','','2018-12-14 17:02:12');
insert into `msg_eventgroup` (`msgeventgroupid`, `platform`, `eventgroupname`, `decription`, `createtime`) values('26','0','授权变更事件组','','2018-12-14 17:02:23');
insert into `msg_eventgroup` (`msgeventgroupid`, `platform`, `eventgroupname`, `decription`, `createtime`) values('27','0','家人授权变更事件组','','2018-12-14 17:02:38');
insert into `msg_eventgroup` (`msgeventgroupid`, `platform`, `eventgroupname`, `decription`, `createtime`) values('28','0','一级告警事件组','','2018-12-14 17:02:52');
insert into `msg_eventgroup` (`msgeventgroupid`, `platform`, `eventgroupname`, `decription`, `createtime`) values('29','0','短信邮件事件组','','2018-12-14 17:04:08');
insert into `msg_eventgroup` (`msgeventgroupid`, `platform`, `eventgroupname`, `decription`, `createtime`) values('30','0','安防状态事件组','','2018-12-14 17:04:19');
