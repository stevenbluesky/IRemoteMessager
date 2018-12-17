/*
SQLyog Ultimate v8.32 
MySQL - 5.5.49 
*********************************************************************
*/
/*!40101 SET NAMES utf8 */;

CREATE TABLE `msg_eventgroupevent` (
  `msgeventgroupeventid` int(9) NOT NULL AUTO_INCREMENT,
  `msgeventgroupid` int(9) NOT NULL,
  `platform` int(9) NOT NULL,
  `msgeventtypeid` int(9) NOT NULL,
  `eventcode` varchar(128) NOT NULL,
  `createtime` datetime NOT NULL,
  PRIMARY KEY (`msgeventgroupeventid`)
) ENGINE=InnoDB AUTO_INCREMENT=141 DEFAULT CHARSET=utf8;
insert into `msg_eventgroupevent` (`msgeventgroupeventid`, `msgeventgroupid`, `platform`, `msgeventtypeid`, `eventcode`, `createtime`) values('37','16','0','21','passwordchanged','2018-12-14 16:55:37');
insert into `msg_eventgroupevent` (`msgeventgroupeventid`, `msgeventgroupid`, `platform`, `msgeventtypeid`, `eventcode`, `createtime`) values('38','16','0','22','userinfochanged','2018-12-14 16:55:37');
insert into `msg_eventgroupevent` (`msgeventgroupeventid`, `msgeventgroupid`, `platform`, `msgeventtypeid`, `eventcode`, `createtime`) values('39','16','0','23','phoneuserattributechanged','2018-12-14 16:55:37');
insert into `msg_eventgroupevent` (`msgeventgroupeventid`, `msgeventgroupid`, `platform`, `msgeventtypeid`, `eventcode`, `createtime`) values('40','30','0','118','disarm','2018-12-14 17:11:29');
insert into `msg_eventgroupevent` (`msgeventgroupeventid`, `msgeventgroupid`, `platform`, `msgeventtypeid`, `eventcode`, `createtime`) values('41','30','0','119','partitionarm','2018-12-14 17:11:29');
insert into `msg_eventgroupevent` (`msgeventgroupeventid`, `msgeventgroupid`, `platform`, `msgeventtypeid`, `eventcode`, `createtime`) values('42','30','0','120','partitioninhomearm','2018-12-14 17:11:29');
insert into `msg_eventgroupevent` (`msgeventgroupeventid`, `msgeventgroupid`, `platform`, `msgeventtypeid`, `eventcode`, `createtime`) values('43','30','0','121','partitiondisarm','2018-12-14 17:11:29');
insert into `msg_eventgroupevent` (`msgeventgroupeventid`, `msgeventgroupid`, `platform`, `msgeventtypeid`, `eventcode`, `createtime`) values('44','30','0','122','partitionarmwithoutcode','2018-12-14 17:11:29');
insert into `msg_eventgroupevent` (`msgeventgroupeventid`, `msgeventgroupid`, `platform`, `msgeventtypeid`, `eventcode`, `createtime`) values('45','30','0','123','partitionarmwithcode','2018-12-14 17:11:29');
insert into `msg_eventgroupevent` (`msgeventgroupeventid`, `msgeventgroupid`, `platform`, `msgeventtypeid`, `eventcode`, `createtime`) values('46','30','0','116','arm','2018-12-14 17:12:03');
insert into `msg_eventgroupevent` (`msgeventgroupeventid`, `msgeventgroupid`, `platform`, `msgeventtypeid`, `eventcode`, `createtime`) values('47','30','0','117','inhomearm','2018-12-14 17:12:03');
insert into `msg_eventgroupevent` (`msgeventgroupeventid`, `msgeventgroupid`, `platform`, `msgeventtypeid`, `eventcode`, `createtime`) values('48','29','0','18','phoneuserrandcode','2018-12-14 17:16:23');
insert into `msg_eventgroupevent` (`msgeventgroupeventid`, `msgeventgroupid`, `platform`, `msgeventtypeid`, `eventcode`, `createtime`) values('49','29','0','19','mailuserrandcode','2018-12-14 17:16:24');
insert into `msg_eventgroupevent` (`msgeventgroupeventid`, `msgeventgroupid`, `platform`, `msgeventtypeid`, `eventcode`, `createtime`) values('50','29','0','20','mailresetpassword','2018-12-14 17:16:24');
insert into `msg_eventgroupevent` (`msgeventgroupeventid`, `msgeventgroupid`, `platform`, `msgeventtypeid`, `eventcode`, `createtime`) values('51','29','0','24','invation','2018-12-14 17:16:24');
insert into `msg_eventgroupevent` (`msgeventgroupeventid`, `msgeventgroupid`, `platform`, `msgeventtypeid`, `eventcode`, `createtime`) values('52','29','0','94','bulliedopenlock','2018-12-14 17:16:45');
insert into `msg_eventgroupevent` (`msgeventgroupeventid`, `msgeventgroupid`, `platform`, `msgeventtypeid`, `eventcode`, `createtime`) values('53','29','0','124','smsrunout','2018-12-14 17:16:54');
insert into `msg_eventgroupevent` (`msgeventgroupeventid`, `msgeventgroupid`, `platform`, `msgeventtypeid`, `eventcode`, `createtime`) values('54','21','0','38','battery','2018-12-14 17:21:00');
insert into `msg_eventgroupevent` (`msgeventgroupeventid`, `msgeventgroupid`, `platform`, `msgeventtypeid`, `eventcode`, `createtime`) values('55','21','0','98','lowbattery','2018-12-14 17:21:00');
insert into `msg_eventgroupevent` (`msgeventgroupeventid`, `msgeventgroupid`, `platform`, `msgeventtypeid`, `eventcode`, `createtime`) values('56','17','0','25','remoteonline','2018-12-14 17:23:59');
insert into `msg_eventgroupevent` (`msgeventgroupeventid`, `msgeventgroupid`, `platform`, `msgeventtypeid`, `eventcode`, `createtime`) values('57','17','0','27','setowner','2018-12-14 17:24:11');
insert into `msg_eventgroupevent` (`msgeventgroupeventid`, `msgeventgroupid`, `platform`, `msgeventtypeid`, `eventcode`, `createtime`) values('58','17','0','29','editgateway','2018-12-14 17:24:22');
insert into `msg_eventgroupevent` (`msgeventgroupeventid`, `msgeventgroupid`, `platform`, `msgeventtypeid`, `eventcode`, `createtime`) values('60','17','0','32','deleteremote','2018-12-14 17:24:53');
insert into `msg_eventgroupevent` (`msgeventgroupeventid`, `msgeventgroupid`, `platform`, `msgeventtypeid`, `eventcode`, `createtime`) values('61','17','0','39','addzwavedevice','2018-12-14 17:25:24');
insert into `msg_eventgroupevent` (`msgeventgroupeventid`, `msgeventgroupid`, `platform`, `msgeventtypeid`, `eventcode`, `createtime`) values('62','17','0','40','editzwavedevice','2018-12-14 17:25:24');
insert into `msg_eventgroupevent` (`msgeventgroupeventid`, `msgeventgroupid`, `platform`, `msgeventtypeid`, `eventcode`, `createtime`) values('63','17','0','41','deletezwavedevice','2018-12-14 17:25:24');
insert into `msg_eventgroupevent` (`msgeventgroupeventid`, `msgeventgroupid`, `platform`, `msgeventtypeid`, `eventcode`, `createtime`) values('64','17','0','43','remoteunlockforbidden','2018-12-14 17:25:39');
insert into `msg_eventgroupevent` (`msgeventgroupeventid`, `msgeventgroupid`, `platform`, `msgeventtypeid`, `eventcode`, `createtime`) values('65','17','0','44','remoteunlockenabled','2018-12-14 17:25:39');
insert into `msg_eventgroupevent` (`msgeventgroupeventid`, `msgeventgroupid`, `platform`, `msgeventtypeid`, `eventcode`, `createtime`) values('66','17','0','50','addinfrareddevice','2018-12-14 17:26:34');
insert into `msg_eventgroupevent` (`msgeventgroupeventid`, `msgeventgroupid`, `platform`, `msgeventtypeid`, `eventcode`, `createtime`) values('67','17','0','51','editinfrareddevice','2018-12-14 17:26:35');
insert into `msg_eventgroupevent` (`msgeventgroupeventid`, `msgeventgroupid`, `platform`, `msgeventtypeid`, `eventcode`, `createtime`) values('68','17','0','52','deleteinfrareddevice','2018-12-14 17:26:35');
insert into `msg_eventgroupevent` (`msgeventgroupeventid`, `msgeventgroupid`, `platform`, `msgeventtypeid`, `eventcode`, `createtime`) values('69','17','0','53','addcamera','2018-12-14 17:26:58');
insert into `msg_eventgroupevent` (`msgeventgroupeventid`, `msgeventgroupid`, `platform`, `msgeventtypeid`, `eventcode`, `createtime`) values('70','17','0','54','editcamera','2018-12-14 17:26:58');
insert into `msg_eventgroupevent` (`msgeventgroupeventid`, `msgeventgroupid`, `platform`, `msgeventtypeid`, `eventcode`, `createtime`) values('71','17','0','55','deletecamera','2018-12-14 17:26:58');
insert into `msg_eventgroupevent` (`msgeventgroupeventid`, `msgeventgroupid`, `platform`, `msgeventtypeid`, `eventcode`, `createtime`) values('72','17','0','60','addscene','2018-12-14 17:27:30');
insert into `msg_eventgroupevent` (`msgeventgroupeventid`, `msgeventgroupid`, `platform`, `msgeventtypeid`, `eventcode`, `createtime`) values('73','17','0','61','editscene','2018-12-14 17:27:30');
insert into `msg_eventgroupevent` (`msgeventgroupeventid`, `msgeventgroupid`, `platform`, `msgeventtypeid`, `eventcode`, `createtime`) values('74','17','0','62','deletescene','2018-12-14 17:27:30');
insert into `msg_eventgroupevent` (`msgeventgroupeventid`, `msgeventgroupid`, `platform`, `msgeventtypeid`, `eventcode`, `createtime`) values('75','17','0','64','addroom','2018-12-14 17:27:45');
insert into `msg_eventgroupevent` (`msgeventgroupeventid`, `msgeventgroupid`, `platform`, `msgeventtypeid`, `eventcode`, `createtime`) values('76','17','0','65','editroom','2018-12-14 17:27:45');
insert into `msg_eventgroupevent` (`msgeventgroupeventid`, `msgeventgroupid`, `platform`, `msgeventtypeid`, `eventcode`, `createtime`) values('77','17','0','66','addroomappliance','2018-12-14 17:27:45');
insert into `msg_eventgroupevent` (`msgeventgroupeventid`, `msgeventgroupid`, `platform`, `msgeventtypeid`, `eventcode`, `createtime`) values('78','17','0','67','deleteroomappliance','2018-12-14 17:27:45');
insert into `msg_eventgroupevent` (`msgeventgroupeventid`, `msgeventgroupid`, `platform`, `msgeventtypeid`, `eventcode`, `createtime`) values('79','17','0','68','adddevicegroup','2018-12-14 17:29:03');
insert into `msg_eventgroupevent` (`msgeventgroupeventid`, `msgeventgroupid`, `platform`, `msgeventtypeid`, `eventcode`, `createtime`) values('80','17','0','69','editdevicegroup','2018-12-14 17:29:03');
insert into `msg_eventgroupevent` (`msgeventgroupeventid`, `msgeventgroupid`, `platform`, `msgeventtypeid`, `eventcode`, `createtime`) values('81','17','0','70','deletedevicegroup','2018-12-14 17:29:03');
insert into `msg_eventgroupevent` (`msgeventgroupeventid`, `msgeventgroupid`, `platform`, `msgeventtypeid`, `eventcode`, `createtime`) values('82','17','0','71','addpartition','2018-12-14 17:29:19');
insert into `msg_eventgroupevent` (`msgeventgroupeventid`, `msgeventgroupid`, `platform`, `msgeventtypeid`, `eventcode`, `createtime`) values('83','17','0','72','editpartition','2018-12-14 17:29:19');
insert into `msg_eventgroupevent` (`msgeventgroupeventid`, `msgeventgroupid`, `platform`, `msgeventtypeid`, `eventcode`, `createtime`) values('84','17','0','73','deletepartition','2018-12-14 17:29:19');
insert into `msg_eventgroupevent` (`msgeventgroupeventid`, `msgeventgroupid`, `platform`, `msgeventtypeid`, `eventcode`, `createtime`) values('85','27','0','79','familyinvitationaccepted','2018-12-14 17:32:33');
insert into `msg_eventgroupevent` (`msgeventgroupeventid`, `msgeventgroupid`, `platform`, `msgeventtypeid`, `eventcode`, `createtime`) values('86','27','0','82','familyinvitationdeleted','2018-12-14 17:32:33');
insert into `msg_eventgroupevent` (`msgeventgroupeventid`, `msgeventgroupid`, `platform`, `msgeventtypeid`, `eventcode`, `createtime`) values('87','26','0','78','shareinvitationaccepted','2018-12-14 17:33:39');
insert into `msg_eventgroupevent` (`msgeventgroupeventid`, `msgeventgroupid`, `platform`, `msgeventtypeid`, `eventcode`, `createtime`) values('88','26','0','80','shareinvitationrejected','2018-12-14 17:33:39');
insert into `msg_eventgroupevent` (`msgeventgroupeventid`, `msgeventgroupid`, `platform`, `msgeventtypeid`, `eventcode`, `createtime`) values('89','26','0','81','shareinvitationdeleted','2018-12-14 17:33:39');
insert into `msg_eventgroupevent` (`msgeventgroupeventid`, `msgeventgroupid`, `platform`, `msgeventtypeid`, `eventcode`, `createtime`) values('90','25','0','77','shareinvitation','2018-12-14 17:34:25');
insert into `msg_eventgroupevent` (`msgeventgroupeventid`, `msgeventgroupid`, `platform`, `msgeventtypeid`, `eventcode`, `createtime`) values('91','24','0','63','sceneexecute','2018-12-14 17:35:45');
insert into `msg_eventgroupevent` (`msgeventgroupeventid`, `msgeventgroupid`, `platform`, `msgeventtypeid`, `eventcode`, `createtime`) values('92','23','0','45','lockdoorbellring','2018-12-14 17:36:38');
insert into `msg_eventgroupevent` (`msgeventgroupeventid`, `msgeventgroupid`, `platform`, `msgeventtypeid`, `eventcode`, `createtime`) values('93','23','0','59','catdoorbellring','2018-12-14 17:36:39');
insert into `msg_eventgroupevent` (`msgeventgroupeventid`, `msgeventgroupid`, `platform`, `msgeventtypeid`, `eventcode`, `createtime`) values('94','19','0','28','iremoteownerchanged','2018-12-14 17:37:23');
insert into `msg_eventgroupevent` (`msgeventgroupeventid`, `msgeventgroupid`, `platform`, `msgeventtypeid`, `eventcode`, `createtime`) values('95','20','0','31','remotepowertype','2018-12-14 17:38:38');
insert into `msg_eventgroupevent` (`msgeventgroupeventid`, `msgeventgroupid`, `platform`, `msgeventtypeid`, `eventcode`, `createtime`) values('96','18','0','26','remoteoffline','2018-12-14 17:39:58');
insert into `msg_eventgroupevent` (`msgeventgroupeventid`, `msgeventgroupid`, `platform`, `msgeventtypeid`, `eventcode`, `createtime`) values('97','18','0','56','cameradectectmove','2018-12-14 17:40:14');
insert into `msg_eventgroupevent` (`msgeventgroupeventid`, `msgeventgroupid`, `platform`, `msgeventtypeid`, `eventcode`, `createtime`) values('98','18','0','88','dooropen','2018-12-14 17:40:27');
insert into `msg_eventgroupevent` (`msgeventgroupeventid`, `msgeventgroupid`, `platform`, `msgeventtypeid`, `eventcode`, `createtime`) values('99','18','0','89','doorlockopen','2018-12-14 17:40:46');
insert into `msg_eventgroupevent` (`msgeventgroupeventid`, `msgeventgroupid`, `platform`, `msgeventtypeid`, `eventcode`, `createtime`) values('100','18','0','93','locklockerror','2018-12-14 17:41:35');
insert into `msg_eventgroupevent` (`msgeventgroupeventid`, `msgeventgroupid`, `platform`, `msgeventtypeid`, `eventcode`, `createtime`) values('101','18','0','46','poweroverload','2018-12-14 17:41:46');
insert into `msg_eventgroupevent` (`msgeventgroupeventid`, `msgeventgroupid`, `platform`, `msgeventtypeid`, `eventcode`, `createtime`) values('102','18','0','96','movein','2018-12-14 17:41:55');
insert into `msg_eventgroupevent` (`msgeventgroupeventid`, `msgeventgroupid`, `platform`, `msgeventtypeid`, `eventcode`, `createtime`) values('103','28','0','83','smoke','2018-12-14 17:43:13');
insert into `msg_eventgroupevent` (`msgeventgroupeventid`, `msgeventgroupid`, `platform`, `msgeventtypeid`, `eventcode`, `createtime`) values('104','28','0','84','waterleak','2018-12-14 17:43:13');
insert into `msg_eventgroupevent` (`msgeventgroupeventid`, `msgeventgroupid`, `platform`, `msgeventtypeid`, `eventcode`, `createtime`) values('105','28','0','85','gasleak','2018-12-14 17:43:13');
insert into `msg_eventgroupevent` (`msgeventgroupeventid`, `msgeventgroupid`, `platform`, `msgeventtypeid`, `eventcode`, `createtime`) values('106','28','0','86','tampleralarm','2018-12-14 17:43:13');
insert into `msg_eventgroupevent` (`msgeventgroupeventid`, `msgeventgroupid`, `platform`, `msgeventtypeid`, `eventcode`, `createtime`) values('107','28','0','87','sos','2018-12-14 17:43:13');
insert into `msg_eventgroupevent` (`msgeventgroupeventid`, `msgeventgroupid`, `platform`, `msgeventtypeid`, `eventcode`, `createtime`) values('108','28','0','90','passworderror5times','2018-12-14 17:43:13');
insert into `msg_eventgroupevent` (`msgeventgroupeventid`, `msgeventgroupid`, `platform`, `msgeventtypeid`, `eventcode`, `createtime`) values('109','28','0','91','lockkeyerror','2018-12-14 17:43:13');
insert into `msg_eventgroupevent` (`msgeventgroupeventid`, `msgeventgroupid`, `platform`, `msgeventtypeid`, `eventcode`, `createtime`) values('110','28','0','92','lockkeyevent','2018-12-14 17:43:13');
insert into `msg_eventgroupevent` (`msgeventgroupeventid`, `msgeventgroupid`, `platform`, `msgeventtypeid`, `eventcode`, `createtime`) values('111','28','0','95','dscalarm','2018-12-14 17:43:13');
insert into `msg_eventgroupevent` (`msgeventgroupeventid`, `msgeventgroupid`, `platform`, `msgeventtypeid`, `eventcode`, `createtime`) values('112','22','0','34','devicestatus','2018-12-14 17:48:09');
insert into `msg_eventgroupevent` (`msgeventgroupeventid`, `msgeventgroupid`, `platform`, `msgeventtypeid`, `eventcode`, `createtime`) values('113','22','0','42','subdevicestatus','2018-12-14 17:48:09');
insert into `msg_eventgroupevent` (`msgeventgroupeventid`, `msgeventgroupid`, `platform`, `msgeventtypeid`, `eventcode`, `createtime`) values('114','22','0','47','disabledevice','2018-12-14 17:48:09');
insert into `msg_eventgroupevent` (`msgeventgroupeventid`, `msgeventgroupid`, `platform`, `msgeventtypeid`, `eventcode`, `createtime`) values('115','22','0','48','enabledevice','2018-12-14 17:48:09');
insert into `msg_eventgroupevent` (`msgeventgroupeventid`, `msgeventgroupid`, `platform`, `msgeventtypeid`, `eventcode`, `createtime`) values('116','22','0','49','devicearmstatuschange','2018-12-14 17:48:09');
insert into `msg_eventgroupevent` (`msgeventgroupeventid`, `msgeventgroupid`, `platform`, `msgeventtypeid`, `eventcode`, `createtime`) values('117','22','0','57','cameraoffline','2018-12-14 17:48:09');
insert into `msg_eventgroupevent` (`msgeventgroupeventid`, `msgeventgroupid`, `platform`, `msgeventtypeid`, `eventcode`, `createtime`) values('118','22','0','58','cameraonline','2018-12-14 17:48:09');
insert into `msg_eventgroupevent` (`msgeventgroupeventid`, `msgeventgroupid`, `platform`, `msgeventtypeid`, `eventcode`, `createtime`) values('119','22','0','74','armstatuschange','2018-12-14 17:48:09');
insert into `msg_eventgroupevent` (`msgeventgroupeventid`, `msgeventgroupid`, `platform`, `msgeventtypeid`, `eventcode`, `createtime`) values('120','22','0','75','partitionarmstatuschange','2018-12-14 17:48:09');
insert into `msg_eventgroupevent` (`msgeventgroupeventid`, `msgeventgroupid`, `platform`, `msgeventtypeid`, `eventcode`, `createtime`) values('121','22','0','97','malfunction','2018-12-14 17:48:09');
insert into `msg_eventgroupevent` (`msgeventgroupeventid`, `msgeventgroupid`, `platform`, `msgeventtypeid`, `eventcode`, `createtime`) values('122','22','0','35','doorclose','2018-12-14 17:49:45');
insert into `msg_eventgroupevent` (`msgeventgroupeventid`, `msgeventgroupid`, `platform`, `msgeventtypeid`, `eventcode`, `createtime`) values('123','22','0','36','doorlockclose','2018-12-14 17:49:46');
insert into `msg_eventgroupevent` (`msgeventgroupeventid`, `msgeventgroupid`, `platform`, `msgeventtypeid`, `eventcode`, `createtime`) values('124','22','0','37','moveout','2018-12-14 17:49:46');
insert into `msg_eventgroupevent` (`msgeventgroupeventid`, `msgeventgroupid`, `platform`, `msgeventtypeid`, `eventcode`, `createtime`) values('125','22','0','100','unalarmtampleralarm','2018-12-14 17:49:46');
insert into `msg_eventgroupevent` (`msgeventgroupeventid`, `msgeventgroupid`, `platform`, `msgeventtypeid`, `eventcode`, `createtime`) values('126','22','0','101','unalarmsos','2018-12-14 17:49:46');
insert into `msg_eventgroupevent` (`msgeventgroupeventid`, `msgeventgroupid`, `platform`, `msgeventtypeid`, `eventcode`, `createtime`) values('127','22','0','102','unalarmsmoke','2018-12-14 17:49:46');
insert into `msg_eventgroupevent` (`msgeventgroupeventid`, `msgeventgroupid`, `platform`, `msgeventtypeid`, `eventcode`, `createtime`) values('128','22','0','103','unalarmgasleak','2018-12-14 17:49:46');
insert into `msg_eventgroupevent` (`msgeventgroupeventid`, `msgeventgroupid`, `platform`, `msgeventtypeid`, `eventcode`, `createtime`) values('129','22','0','104','unalarmwaterleak','2018-12-14 17:49:46');
insert into `msg_eventgroupevent` (`msgeventgroupeventid`, `msgeventgroupid`, `platform`, `msgeventtypeid`, `eventcode`, `createtime`) values('130','22','0','105','unalarmpassworderror5times','2018-12-14 17:49:46');
insert into `msg_eventgroupevent` (`msgeventgroupeventid`, `msgeventgroupid`, `platform`, `msgeventtypeid`, `eventcode`, `createtime`) values('131','22','0','106','unalarmlockkeyerror','2018-12-14 17:49:46');
insert into `msg_eventgroupevent` (`msgeventgroupeventid`, `msgeventgroupid`, `platform`, `msgeventtypeid`, `eventcode`, `createtime`) values('132','22','0','107','unalarmmovein','2018-12-14 17:49:46');
insert into `msg_eventgroupevent` (`msgeventgroupeventid`, `msgeventgroupid`, `platform`, `msgeventtypeid`, `eventcode`, `createtime`) values('133','22','0','109','unalarmdoorlockopen','2018-12-14 17:49:46');
insert into `msg_eventgroupevent` (`msgeventgroupeventid`, `msgeventgroupid`, `platform`, `msgeventtypeid`, `eventcode`, `createtime`) values('134','22','0','113','unalarmpoweroverload','2018-12-14 17:49:46');
insert into `msg_eventgroupevent` (`msgeventgroupeventid`, `msgeventgroupid`, `platform`, `msgeventtypeid`, `eventcode`, `createtime`) values('135','22','0','114','unalarmcameradectectmove','2018-12-14 17:49:46');
insert into `msg_eventgroupevent` (`msgeventgroupeventid`, `msgeventgroupid`, `platform`, `msgeventtypeid`, `eventcode`, `createtime`) values('136','22','0','115','unalarmdscalarm','2018-12-14 17:49:46');
insert into `msg_eventgroupevent` (`msgeventgroupeventid`, `msgeventgroupid`, `platform`, `msgeventtypeid`, `eventcode`, `createtime`) values('137','22','0','108','unalarmdooropen','2018-12-14 17:50:41');
insert into `msg_eventgroupevent` (`msgeventgroupeventid`, `msgeventgroupid`, `platform`, `msgeventtypeid`, `eventcode`, `createtime`) values('138','22','0','110','unalarmbulliedopenlock','2018-12-14 17:50:41');
insert into `msg_eventgroupevent` (`msgeventgroupeventid`, `msgeventgroupid`, `platform`, `msgeventtypeid`, `eventcode`, `createtime`) values('139','22','0','111','unalarmlocklockerror','2018-12-14 17:50:41');
insert into `msg_eventgroupevent` (`msgeventgroupeventid`, `msgeventgroupid`, `platform`, `msgeventtypeid`, `eventcode`, `createtime`) values('140','22','0','112','unalarmlockkeyevent','2018-12-14 17:50:41');
