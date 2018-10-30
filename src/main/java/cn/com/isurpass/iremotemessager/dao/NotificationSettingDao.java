package cn.com.isurpass.iremotemessager.dao;

import java.util.List;

import org.springframework.data.repository.CrudRepository;

import cn.com.isurpass.iremotemessager.domain.NotificationSetting;

public interface NotificationSettingDao extends CrudRepository<NotificationSetting, Integer> 
{
	List<NotificationSetting> findByPhoneuseridIn(List<Integer> phoneuserid);
}
