package cn.com.isurpass.iremotemessager.service;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Component;

import cn.com.isurpass.iremotemessager.dao.NotificationSettingDao;
import cn.com.isurpass.iremotemessager.domain.NotificationSetting;

@Component
public class NotificationSettingService
{
	@Resource
	private NotificationSettingDao dao ;
	
	public List<NotificationSetting> findByPhoneuserid(List<Integer> phoneuserid)
	{
		return dao.findByPhoneuseridIn(phoneuserid);
	}

	public NotificationSetting findByPhoneuseridAndType(Integer phoneuserid, int notificationType) {
		if (phoneuserid == null) {
			return null;
		}
		return dao.findByPhoneuseridAndNotificationtype(phoneuserid, notificationType);
	}
}
