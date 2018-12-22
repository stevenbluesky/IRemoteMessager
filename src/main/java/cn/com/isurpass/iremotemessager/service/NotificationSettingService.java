package cn.com.isurpass.iremotemessager.service;

import java.util.List;

import javax.annotation.Resource;

import cn.com.isurpass.iremotemessager.common.util.IRemoteUtils;
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

	public NotificationSetting findById(Integer id) {
		if (IRemoteUtils.isBlank(id)) {
			return new NotificationSetting();
		}
		return dao.findById(id).orElse(new NotificationSetting());
	}
}
