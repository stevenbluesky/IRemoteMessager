package cn.com.isurpass.iremotemessager.service;

import javax.annotation.Resource;

import org.apache.commons.lang.StringUtils;
import org.springframework.stereotype.Component;

import cn.com.isurpass.iremotemessager.dao.GatewayDao;
import cn.com.isurpass.iremotemessager.domain.Gateway;

@Component
public class GatewayService
{
	@Resource
	private GatewayDao dao ;
	
	public Gateway findById(String deviceid) {
		return StringUtils.isBlank(deviceid)
				? new Gateway()
				:dao.findById(deviceid).orElse(new Gateway());
	}
}
