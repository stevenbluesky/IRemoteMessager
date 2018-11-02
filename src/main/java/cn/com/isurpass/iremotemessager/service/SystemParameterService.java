package cn.com.isurpass.iremotemessager.service;

import cn.com.isurpass.iremotemessager.dao.SystemParameterDao;
import cn.com.isurpass.iremotemessager.domain.SystemParameter;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

@Service
public class SystemParameterService{
	@Resource
	private SystemParameterDao systemParameterDao;

	public String getStringValue(String key) {
		SystemParameter systemParameter = systemParameterDao.findByKey(key);
		return systemParameter.getStrvalue();
	}

	public Integer getIntValue(String key, Integer defaultValue) {
		SystemParameter systemParameter = systemParameterDao.findByKey(key);
		if (systemParameter.getIntvalue() != null) {
			return systemParameter.getIntvalue();
		}
		return defaultValue;
	}

	public Integer getIntValue(String key) {
		return getIntValue(key, null);
	}
}
