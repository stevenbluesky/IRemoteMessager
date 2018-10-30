package cn.com.isurpass.iremotemessager.service;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Component;

import cn.com.isurpass.iremotemessager.dao.UserDao;
import cn.com.isurpass.iremotemessager.domain.User;

@Component
public class UserService
{
	@Resource
	protected UserDao userdao;
	
	public User findById(Integer phoneuserid)
	{
		return userdao.findById(phoneuserid).orElse(null);
	}
	
	public List<User> findByFamilyid(Integer familyid)
	{
		return userdao.findByFamilyid(familyid);
	}
}
