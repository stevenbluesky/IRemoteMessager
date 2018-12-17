/*
SQLyog Ultimate v8.32 
MySQL - 5.5.49 
*********************************************************************
*/
/*!40101 SET NAMES utf8 */;

CREATE TABLE `msg_eventtype` (
  `msgeventtypeid` int(9) NOT NULL AUTO_INCREMENT,
  `eventtypename` varchar(256) NOT NULL,
  `eventcode` varchar(128) NOT NULL,
  `decription` varchar(1024) DEFAULT NULL,
  `createtime` datetime NOT NULL,
  PRIMARY KEY (`msgeventtypeid`)
) ENGINE=InnoDB AUTO_INCREMENT=130 DEFAULT CHARSET=utf8; 
insert into `msg_eventtype` (`msgeventtypeid`, `eventtypename`, `eventcode`, `decription`, `createtime`) values('18','手机注册和重置密码随机码','phoneuserrandcode','手机注册和重置密码随机码','2018-11-23 15:11:38');
insert into `msg_eventtype` (`msgeventtypeid`, `eventtypename`, `eventcode`, `decription`, `createtime`) values('19','邮箱注册随机码','mailuserrandcode','邮箱注册随机码','2018-11-23 15:12:13');
insert into `msg_eventtype` (`msgeventtypeid`, `eventtypename`, `eventcode`, `decription`, `createtime`) values('20','邮箱用户密码找回','mailresetpassword','邮箱用户密码找回','2018-11-23 15:12:53');
insert into `msg_eventtype` (`msgeventtypeid`, `eventtypename`, `eventcode`, `decription`, `createtime`) values('21','修改密码','passwordchanged','修改密码','2018-11-23 15:13:24');
insert into `msg_eventtype` (`msgeventtypeid`, `eventtypename`, `eventcode`, `decription`, `createtime`) values('22','修改用户资料','userinfochanged','修改用户资料（修改头像，名称，时间段设置变化，短信条数变更，用户城市）','2018-11-23 15:14:07');
insert into `msg_eventtype` (`msgeventtypeid`, `eventtypename`, `eventcode`, `decription`, `createtime`) values('23','修改属性','phoneuserattributechanged','修改属性（用户是否注册乐橙）','2018-11-23 15:14:27');
insert into `msg_eventtype` (`msgeventtypeid`, `eventtypename`, `eventcode`, `decription`, `createtime`) values('24','注册邀请','invation','注册邀请','2018-11-23 15:14:52');
insert into `msg_eventtype` (`msgeventtypeid`, `eventtypename`, `eventcode`, `decription`, `createtime`) values('25','上线','remoteonline','网关上线','2018-11-23 15:15:23');
insert into `msg_eventtype` (`msgeventtypeid`, `eventtypename`, `eventcode`, `decription`, `createtime`) values('26','离线','remoteoffline','网关离线','2018-11-23 15:16:14');
insert into `msg_eventtype` (`msgeventtypeid`, `eventtypename`, `eventcode`, `decription`, `createtime`) values('27','设置主人','setowner','设置主人','2018-11-23 15:16:32');
insert into `msg_eventtype` (`msgeventtypeid`, `eventtypename`, `eventcode`, `decription`, `createtime`) values('28','网关主人变更','iremoteownerchanged','网关主人变更，智能配置把其它账号的网关配置到自己账号','2018-11-23 15:19:14');
insert into `msg_eventtype` (`msgeventtypeid`, `eventtypename`, `eventcode`, `decription`, `createtime`) values('29','修改名称','editgateway','修改名称','2018-11-23 15:19:39');
insert into `msg_eventtype` (`msgeventtypeid`, `eventtypename`, `eventcode`, `decription`, `createtime`) values('31','切换电源','remotepowertype','切换电源','2018-11-23 15:21:03');
insert into `msg_eventtype` (`msgeventtypeid`, `eventtypename`, `eventcode`, `decription`, `createtime`) values('32','删除网关','deleteremote','删除网关','2018-11-23 15:21:51');
insert into `msg_eventtype` (`msgeventtypeid`, `eventtypename`, `eventcode`, `decription`, `createtime`) values('33','网关能力集变更','remotecapability???','网关能力集变更','2018-11-23 15:23:15');
insert into `msg_eventtype` (`msgeventtypeid`, `eventtypename`, `eventcode`, `decription`, `createtime`) values('34','设备状态','devicestatus','设备状态','2018-11-23 15:23:30');
insert into `msg_eventtype` (`msgeventtypeid`, `eventtypename`, `eventcode`, `decription`, `createtime`) values('35','门磁关','doorclose','门磁关消息','2018-11-23 15:25:59');
insert into `msg_eventtype` (`msgeventtypeid`, `eventtypename`, `eventcode`, `decription`, `createtime`) values('36','门锁关','doorlockclose','门锁关消息','2018-11-23 15:26:19');
insert into `msg_eventtype` (`msgeventtypeid`, `eventtypename`, `eventcode`, `decription`, `createtime`) values('37','人员离开','moveout','人员离开感应区消息','2018-11-23 15:26:35');
insert into `msg_eventtype` (`msgeventtypeid`, `eventtypename`, `eventcode`, `decription`, `createtime`) values('38','电池电量','battery','电池电量','2018-11-23 15:26:51');
insert into `msg_eventtype` (`msgeventtypeid`, `eventtypename`, `eventcode`, `decription`, `createtime`) values('39','增加设备','addzwavedevice','增加zwave设备','2018-11-23 15:27:10');
insert into `msg_eventtype` (`msgeventtypeid`, `eventtypename`, `eventcode`, `decription`, `createtime`) values('40','修改设备','editzwavedevice','修改zwave设备','2018-11-23 15:27:24');
insert into `msg_eventtype` (`msgeventtypeid`, `eventtypename`, `eventcode`, `decription`, `createtime`) values('41','删除设备','deletezwavedevice','删除zwave设备','2018-11-23 15:27:40');
insert into `msg_eventtype` (`msgeventtypeid`, `eventtypename`, `eventcode`, `decription`, `createtime`) values('42','子设备状态变化','subdevicestatus','子设备状态变化','2018-11-23 15:29:21');
insert into `msg_eventtype` (`msgeventtypeid`, `eventtypename`, `eventcode`, `decription`, `createtime`) values('43','禁止远程开锁功能','remoteunlockforbidden','禁止远程开锁功能','2018-11-23 15:29:50');
insert into `msg_eventtype` (`msgeventtypeid`, `eventtypename`, `eventcode`, `decription`, `createtime`) values('44','开启远程开锁功能','remoteunlockenabled','开启远程开锁功能','2018-11-23 15:30:06');
insert into `msg_eventtype` (`msgeventtypeid`, `eventtypename`, `eventcode`, `decription`, `createtime`) values('45','门锁门铃响','lockdoorbellring','门锁上有门铃按钮一按后收到消息通知','2018-11-23 15:31:21');
insert into `msg_eventtype` (`msgeventtypeid`, `eventtypename`, `eventcode`, `decription`, `createtime`) values('46','功率过载','poweroverload','功率过载告警','2018-11-23 15:31:51');
insert into `msg_eventtype` (`msgeventtypeid`, `eventtypename`, `eventcode`, `decription`, `createtime`) values('47','失效','disabledevice','设备失效','2018-11-23 15:32:09');
insert into `msg_eventtype` (`msgeventtypeid`, `eventtypename`, `eventcode`, `decription`, `createtime`) values('48','恢复','enabledevice','设备恢复','2018-11-23 15:32:24');
insert into `msg_eventtype` (`msgeventtypeid`, `eventtypename`, `eventcode`, `decription`, `createtime`) values('49','设备设防、撤防','devicearmstatuschange','设备设防、撤防','2018-11-23 15:33:11');
insert into `msg_eventtype` (`msgeventtypeid`, `eventtypename`, `eventcode`, `decription`, `createtime`) values('50','增加红外设备','addinfrareddevice','增加红外设备','2018-11-23 15:33:31');
insert into `msg_eventtype` (`msgeventtypeid`, `eventtypename`, `eventcode`, `decription`, `createtime`) values('51','修改红外设备','editinfrareddevice','修改红外设备','2018-11-23 15:33:44');
insert into `msg_eventtype` (`msgeventtypeid`, `eventtypename`, `eventcode`, `decription`, `createtime`) values('52','删除红外设备','deleteinfrareddevice','删除红外设备','2018-11-23 15:34:01');
insert into `msg_eventtype` (`msgeventtypeid`, `eventtypename`, `eventcode`, `decription`, `createtime`) values('53','增加摄像头设备','addcamera','增加摄像头设备','2018-11-23 15:34:23');
insert into `msg_eventtype` (`msgeventtypeid`, `eventtypename`, `eventcode`, `decription`, `createtime`) values('54','修改摄像头设备','editcamera','修改摄像头设备','2018-11-23 15:34:40');
insert into `msg_eventtype` (`msgeventtypeid`, `eventtypename`, `eventcode`, `decription`, `createtime`) values('55','删除摄像头设备','deletecamera','删除摄像头设备','2018-11-23 15:34:56');
insert into `msg_eventtype` (`msgeventtypeid`, `eventtypename`, `eventcode`, `decription`, `createtime`) values('56','移动事件','cameradectectmove','移动事件','2018-11-23 15:35:12');
insert into `msg_eventtype` (`msgeventtypeid`, `eventtypename`, `eventcode`, `decription`, `createtime`) values('57','摄像头离线','cameraoffline','摄像头离线','2018-11-23 15:35:38');
insert into `msg_eventtype` (`msgeventtypeid`, `eventtypename`, `eventcode`, `decription`, `createtime`) values('58','摄像头上线','cameraonline','摄像头上线','2018-11-23 15:35:57');
insert into `msg_eventtype` (`msgeventtypeid`, `eventtypename`, `eventcode`, `decription`, `createtime`) values('59','猫眼门铃','catdoorbellring','猫眼门铃','2018-11-23 16:41:06');
insert into `msg_eventtype` (`msgeventtypeid`, `eventtypename`, `eventcode`, `decription`, `createtime`) values('60','增加情景','addscene','增加情景','2018-11-23 16:41:24');
insert into `msg_eventtype` (`msgeventtypeid`, `eventtypename`, `eventcode`, `decription`, `createtime`) values('61','修改情景','editscene','修改情景','2018-11-23 16:41:41');
insert into `msg_eventtype` (`msgeventtypeid`, `eventtypename`, `eventcode`, `decription`, `createtime`) values('62','删除情景','deletescene','删除情景','2018-11-23 16:42:21');
insert into `msg_eventtype` (`msgeventtypeid`, `eventtypename`, `eventcode`, `decription`, `createtime`) values('63','执行情景','sceneexecute','执行情景','2018-11-23 16:42:38');
insert into `msg_eventtype` (`msgeventtypeid`, `eventtypename`, `eventcode`, `decription`, `createtime`) values('64','增加房间','addroom','增加房间','2018-11-23 16:42:58');
insert into `msg_eventtype` (`msgeventtypeid`, `eventtypename`, `eventcode`, `decription`, `createtime`) values('65','修改房间','editroom','修改房间','2018-11-23 16:43:10');
insert into `msg_eventtype` (`msgeventtypeid`, `eventtypename`, `eventcode`, `decription`, `createtime`) values('66','增加房间设备','addroomappliance','增加房间设备','2018-11-23 16:43:30');
insert into `msg_eventtype` (`msgeventtypeid`, `eventtypename`, `eventcode`, `decription`, `createtime`) values('67','删除房间设备','deleteroomappliance','删除房间设备','2018-11-23 16:43:47');
insert into `msg_eventtype` (`msgeventtypeid`, `eventtypename`, `eventcode`, `decription`, `createtime`) values('68','增加设备组','adddevicegroup','增加设备组','2018-11-23 16:44:03');
insert into `msg_eventtype` (`msgeventtypeid`, `eventtypename`, `eventcode`, `decription`, `createtime`) values('69','修改设备组','editdevicegroup','修改设备组','2018-11-23 16:44:17');
insert into `msg_eventtype` (`msgeventtypeid`, `eventtypename`, `eventcode`, `decription`, `createtime`) values('70','删除设备组','deletedevicegroup','删除设备组','2018-11-23 16:44:34');
insert into `msg_eventtype` (`msgeventtypeid`, `eventtypename`, `eventcode`, `decription`, `createtime`) values('71','增加防区','addpartition','增加防区','2018-11-23 16:46:38');
insert into `msg_eventtype` (`msgeventtypeid`, `eventtypename`, `eventcode`, `decription`, `createtime`) values('72','修改防区','editpartition','修改防区','2018-11-23 16:46:53');
insert into `msg_eventtype` (`msgeventtypeid`, `eventtypename`, `eventcode`, `decription`, `createtime`) values('73','删除防区','deletepartition','删除防区','2018-11-23 16:47:06');
insert into `msg_eventtype` (`msgeventtypeid`, `eventtypename`, `eventcode`, `decription`, `createtime`) values('74','设防、撤防','armstatuschange','设防、撤防','2018-11-23 16:49:41');
insert into `msg_eventtype` (`msgeventtypeid`, `eventtypename`, `eventcode`, `decription`, `createtime`) values('75','防区设防、撤防、延时设防、延时撤防','partitionarmstatuschange','防区设防、撤防、延时设防、延时撤防','2018-11-23 16:50:24');
insert into `msg_eventtype` (`msgeventtypeid`, `eventtypename`, `eventcode`, `decription`, `createtime`) values('76','防区告警','partitionwarningstatus???','防区告警','2018-11-23 16:50:46');
insert into `msg_eventtype` (`msgeventtypeid`, `eventtypename`, `eventcode`, `decription`, `createtime`) values('77','增加授权','shareinvitation','增加授权','2018-11-23 16:51:16');
insert into `msg_eventtype` (`msgeventtypeid`, `eventtypename`, `eventcode`, `decription`, `createtime`) values('78','接受朋友授权','shareinvitationaccepted','接受朋友授权','2018-11-23 16:52:14');
insert into `msg_eventtype` (`msgeventtypeid`, `eventtypename`, `eventcode`, `decription`, `createtime`) values('79','接受家人授权','familyinvitationaccepted','接受家人授权','2018-11-23 16:52:30');
insert into `msg_eventtype` (`msgeventtypeid`, `eventtypename`, `eventcode`, `decription`, `createtime`) values('80','拒绝授权','shareinvitationrejected','拒绝授权','2018-11-23 16:52:49');
insert into `msg_eventtype` (`msgeventtypeid`, `eventtypename`, `eventcode`, `decription`, `createtime`) values('81','删除朋友授权','shareinvitationdeleted','删除朋友授权(全部、单个设备)','2018-11-23 16:53:08');
insert into `msg_eventtype` (`msgeventtypeid`, `eventtypename`, `eventcode`, `decription`, `createtime`) values('82','删除家人授权','familyinvitationdeleted','删除家人授权','2018-11-23 16:53:24');
insert into `msg_eventtype` (`msgeventtypeid`, `eventtypename`, `eventcode`, `decription`, `createtime`) values('83','烟雾告警','smoke','烟雾告警','2018-12-14 15:53:24');
insert into `msg_eventtype` (`msgeventtypeid`, `eventtypename`, `eventcode`, `decription`, `createtime`) values('84','漏水告警','waterleak','漏水告警','2018-12-14 15:53:47');
insert into `msg_eventtype` (`msgeventtypeid`, `eventtypename`, `eventcode`, `decription`, `createtime`) values('85','煤气泄漏告警','gasleak','煤气泄漏告警','2018-12-14 15:54:12');
insert into `msg_eventtype` (`msgeventtypeid`, `eventtypename`, `eventcode`, `decription`, `createtime`) values('86','防拆告警','tampleralarm','防拆告警','2018-12-14 15:54:39');
insert into `msg_eventtype` (`msgeventtypeid`, `eventtypename`, `eventcode`, `decription`, `createtime`) values('87','sos告警','sos','sos告警','2018-12-14 15:54:56');
insert into `msg_eventtype` (`msgeventtypeid`, `eventtypename`, `eventcode`, `decription`, `createtime`) values('88','门磁开告警','dooropen','门磁开告警','2018-12-14 15:55:52');
insert into `msg_eventtype` (`msgeventtypeid`, `eventtypename`, `eventcode`, `decription`, `createtime`) values('89','门锁开告警','doorlockopen','门锁开告警','2018-12-14 15:56:13');
insert into `msg_eventtype` (`msgeventtypeid`, `eventtypename`, `eventcode`, `decription`, `createtime`) values('90','试错告警','passworderror5times','连续5次输错门锁密码告警','2018-12-14 15:56:59');
insert into `msg_eventtype` (`msgeventtypeid`, `eventtypename`, `eventcode`, `decription`, `createtime`) values('91','机械钥匙不匹配告警','lockkeyerror','机械钥匙不匹配告警','2018-12-14 15:57:28');
insert into `msg_eventtype` (`msgeventtypeid`, `eventtypename`, `eventcode`, `decription`, `createtime`) values('92','机械钥匙告警','lockkeyevent','机械钥匙告警','2018-12-14 15:57:55');
insert into `msg_eventtype` (`msgeventtypeid`, `eventtypename`, `eventcode`, `decription`, `createtime`) values('93','关锁失败告警','locklockerror','关锁失败告警','2018-12-14 15:58:47');
insert into `msg_eventtype` (`msgeventtypeid`, `eventtypename`, `eventcode`, `decription`, `createtime`) values('94','门锁胁迫告警','bulliedopenlock','门锁胁迫告警','2018-12-14 15:59:35');
insert into `msg_eventtype` (`msgeventtypeid`, `eventtypename`, `eventcode`, `decription`, `createtime`) values('95','DSC安防设备告警','dscalarm','DSC上报告警(火警，医疗，紧急告警等)','2018-12-14 16:04:22');
insert into `msg_eventtype` (`msgeventtypeid`, `eventtypename`, `eventcode`, `decription`, `createtime`) values('96','移动告警','movein','移动告警','2018-12-14 16:05:12');
insert into `msg_eventtype` (`msgeventtypeid`, `eventtypename`, `eventcode`, `decription`, `createtime`) values('97','设备故障告警','malfunction','设备故障告警','2018-12-14 16:05:57');
insert into `msg_eventtype` (`msgeventtypeid`, `eventtypename`, `eventcode`, `decription`, `createtime`) values('98','低电量告警','lowbattery','电池电量不足告警','2018-12-14 16:06:45');
insert into `msg_eventtype` (`msgeventtypeid`, `eventtypename`, `eventcode`, `decription`, `createtime`) values('99','设备恢复','recovery','设备恢复','2018-12-14 16:09:03');
insert into `msg_eventtype` (`msgeventtypeid`, `eventtypename`, `eventcode`, `decription`, `createtime`) values('100','解除防拆告警','unalarmtampleralarm','解除防拆告警','2018-12-14 16:11:25');
insert into `msg_eventtype` (`msgeventtypeid`, `eventtypename`, `eventcode`, `decription`, `createtime`) values('101','解除sos告警','unalarmsos','解除sos告警','2018-12-14 16:12:15');
insert into `msg_eventtype` (`msgeventtypeid`, `eventtypename`, `eventcode`, `decription`, `createtime`) values('102','解除烟雾告警','unalarmsmoke','解除烟雾告警','2018-12-14 16:12:49');
insert into `msg_eventtype` (`msgeventtypeid`, `eventtypename`, `eventcode`, `decription`, `createtime`) values('103','解除煤气泄漏告警','unalarmgasleak','解除煤气泄漏告警','2018-12-14 16:14:16');
insert into `msg_eventtype` (`msgeventtypeid`, `eventtypename`, `eventcode`, `decription`, `createtime`) values('104','解除漏水告警','unalarmwaterleak','解除漏水告警','2018-12-14 16:14:41');
insert into `msg_eventtype` (`msgeventtypeid`, `eventtypename`, `eventcode`, `decription`, `createtime`) values('105','解除试错告警','unalarmpassworderror5times','解除试错告警','2018-12-14 16:15:42');
insert into `msg_eventtype` (`msgeventtypeid`, `eventtypename`, `eventcode`, `decription`, `createtime`) values('106','解除机械钥匙不匹配告警','unalarmlockkeyerror','解除机械钥匙不匹配告警','2018-12-14 16:16:32');
insert into `msg_eventtype` (`msgeventtypeid`, `eventtypename`, `eventcode`, `decription`, `createtime`) values('107','解除移动告警','unalarmmovein','解除移动告警','2018-12-14 16:16:59');
insert into `msg_eventtype` (`msgeventtypeid`, `eventtypename`, `eventcode`, `decription`, `createtime`) values('108','解除门磁开告警','unalarmdooropen','解除门磁开告警','2018-12-14 16:17:27');
insert into `msg_eventtype` (`msgeventtypeid`, `eventtypename`, `eventcode`, `decription`, `createtime`) values('109','解除门锁开告警','unalarmdoorlockopen','解除门锁开告警','2018-12-14 16:17:59');
insert into `msg_eventtype` (`msgeventtypeid`, `eventtypename`, `eventcode`, `decription`, `createtime`) values('110','解除门锁胁迫告警','unalarmbulliedopenlock','解除门锁胁迫告警','2018-12-14 16:19:06');
insert into `msg_eventtype` (`msgeventtypeid`, `eventtypename`, `eventcode`, `decription`, `createtime`) values('111','解除门锁关门失败告警','unalarmlocklockerror','解除门锁关门失败告警','2018-12-14 16:19:30');
insert into `msg_eventtype` (`msgeventtypeid`, `eventtypename`, `eventcode`, `decription`, `createtime`) values('112','解除门锁钥匙开门告警','unalarmlockkeyevent','解除门锁钥匙开门告警','2018-12-14 16:20:07');
insert into `msg_eventtype` (`msgeventtypeid`, `eventtypename`, `eventcode`, `decription`, `createtime`) values('113','解除功率过载告警','unalarmpoweroverload','解除功率过载告警','2018-12-14 16:21:41');
insert into `msg_eventtype` (`msgeventtypeid`, `eventtypename`, `eventcode`, `decription`, `createtime`) values('114','解除摄像头检测到物体移动告警','unalarmcameradectectmove','解除摄像头检测到物体移动告警','2018-12-14 16:22:18');
insert into `msg_eventtype` (`msgeventtypeid`, `eventtypename`, `eventcode`, `decription`, `createtime`) values('115','解除DSC安防设备告警','unalarmdscalarm','解除DSC安防设备告警','2018-12-14 16:23:20');
insert into `msg_eventtype` (`msgeventtypeid`, `eventtypename`, `eventcode`, `decription`, `createtime`) values('116','离家设防','arm','离家设防','2018-12-14 16:24:01');
insert into `msg_eventtype` (`msgeventtypeid`, `eventtypename`, `eventcode`, `decription`, `createtime`) values('117','在家设防','inhomearm','在家设防','2018-12-14 16:24:18');
insert into `msg_eventtype` (`msgeventtypeid`, `eventtypename`, `eventcode`, `decription`, `createtime`) values('118','撤防','disarm','撤防','2018-12-14 16:24:36');
insert into `msg_eventtype` (`msgeventtypeid`, `eventtypename`, `eventcode`, `decription`, `createtime`) values('119','防区设防','partitionarm','防区设防','2018-12-14 16:24:53');
insert into `msg_eventtype` (`msgeventtypeid`, `eventtypename`, `eventcode`, `decription`, `createtime`) values('120','防区在家设防','partitioninhomearm','防区在家设防','2018-12-14 16:25:25');
insert into `msg_eventtype` (`msgeventtypeid`, `eventtypename`, `eventcode`, `decription`, `createtime`) values('121','防区撤防','partitiondisarm','防区撤防','2018-12-14 16:25:47');
insert into `msg_eventtype` (`msgeventtypeid`, `eventtypename`, `eventcode`, `decription`, `createtime`) values('122','*9设防','partitionarmwithoutcode','*9设防','2018-12-14 16:26:09');
insert into `msg_eventtype` (`msgeventtypeid`, `eventtypename`, `eventcode`, `decription`, `createtime`) values('123','密码设防','partitionarmwithcode','密码设防','2018-12-14 16:26:25');
insert into `msg_eventtype` (`msgeventtypeid`, `eventtypename`, `eventcode`, `decription`, `createtime`) values('124','短信用完','smsrunout','短信用完','2018-12-14 16:28:01');
insert into `msg_eventtype` (`msgeventtypeid`, `eventtypename`, `eventcode`, `decription`, `createtime`) values('125','dsc设防成功','dscpartitionarmstatusnotification','dsc设防成功','2018-12-14 16:28:37');
insert into `msg_eventtype` (`msgeventtypeid`, `eventtypename`, `eventcode`, `decription`, `createtime`) values('126','设防成功部分设备设防失败','armsuccessdevicefailed','设防成功,但部分设备处于打开状态, 处于打开的设备变量为 pname.','2018-12-14 16:38:34');
insert into `msg_eventtype` (`msgeventtypeid`, `eventtypename`, `eventcode`, `decription`, `createtime`) values('127','门锁联动防区设防失败','partitionpasswordwrong','门锁联动防区设防失败','2018-12-14 16:42:12');
insert into `msg_eventtype` (`msgeventtypeid`, `eventtypename`, `eventcode`, `decription`, `createtime`) values('128','情景联动防区设防失败','setpartitionarmstatusfailed','情景联动防区设防失败','2018-12-14 16:42:40');
insert into `msg_eventtype` (`msgeventtypeid`, `eventtypename`, `eventcode`, `decription`, `createtime`) values('129','网关上线（推app）','pashremoteonline','网关上线（推app）','2018-12-14 16:43:22');
