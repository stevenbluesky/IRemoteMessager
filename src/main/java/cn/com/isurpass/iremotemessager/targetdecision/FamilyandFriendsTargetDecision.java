package cn.com.isurpass.iremotemessager.targetdecision;

import java.util.List;

import cn.com.isurpass.iremotemessager.domain.User;
import cn.com.isurpass.iremotemessager.util.IRemoteUtils;
import org.springframework.stereotype.Component;

@Component("cn.com.isurpass.iremotemessager.targetdecision.FamilyandFriendsTargetDecision")
public class FamilyandFriendsTargetDecision extends FamilyMemeberTargetDecision
{

	@Override
	protected List<User> descision()
	{
		List<User> lst = super.descision();

		List<User> friends = super.userservice.findFriends(super.phoneuser.getPhoneuserid());

		if (IRemoteUtils.isNotBlank(friends)) {
			lst.addAll(friends);
		}

		return lst;
	}
}
