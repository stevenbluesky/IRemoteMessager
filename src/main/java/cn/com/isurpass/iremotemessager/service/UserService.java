package cn.com.isurpass.iremotemessager.service;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import javax.annotation.Resource;

import cn.com.isurpass.iremotemessager.domain.UserShare;
import cn.com.isurpass.iremotemessager.common.util.IRemoteUtils;
import org.springframework.stereotype.Component;

import cn.com.isurpass.iremotemessager.dao.UserDao;
import cn.com.isurpass.iremotemessager.domain.User;

@Component
public class UserService
{
	@Resource
	protected UserDao userdao;
	@Resource
	protected UserShareService userShareService;
	
	public User findById(Integer phoneuserid){
		return userdao.findById(phoneuserid).orElse(new User());
	}
	
	public List<User> findByFamilyid(Integer familyid){
		if (familyid == null) {
			return new ArrayList<>();
		}
		return userdao.findByFamilyid(familyid);
	}

	public List<User> findByPhoneuseridIn(Collection<Integer> phoneuserIds) {
		return userdao.findByPhoneuseridIn(phoneuserIds);
	}

	public List<User> findFriends(Integer phoneuserid){
		List<UserShare> userShareList = userShareService.findFriends(phoneuserid);

		if (IRemoteUtils.isNotBlank(userShareList)) {
			List<Integer> ids = new ArrayList<>();

			for (UserShare userShare : userShareList) {
				ids.add(userShare.getTouserid());
			}

			return findByPhoneuseridIn(ids);
		}

		return new ArrayList<>();
	}

	public List<User> find(Integer... userIds) {
		ArrayList<Integer> ids = new ArrayList<>();
		if (userIds == null || userIds.length == 0) {
			return null;
		}

		for (Integer userId : userIds) {
			if (userId != null) {
				ids.add(userId);
			}
		}

		return findByPhoneuseridIn(ids);
	}
}
