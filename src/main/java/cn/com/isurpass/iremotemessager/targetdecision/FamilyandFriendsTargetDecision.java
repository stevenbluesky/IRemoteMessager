package cn.com.isurpass.iremotemessager.targetdecision;

import java.util.List;

import cn.com.isurpass.iremotemessager.domain.User;
import cn.com.isurpass.iremotemessager.common.util.IRemoteUtils;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

/**
 * 家庭成员和朋友
 * @author jwzh
 */
@Component("cn.com.isurpass.iremotemessager.targetdecision.FamilyandFriendsTargetDecision")
@Scope("prototype")
public class FamilyandFriendsTargetDecision extends FamilyMemeberTargetDecision
{

	@Override
	protected List<User> descision()
	{
		List<User> lst = super.descision();

		if (super.phoneuser == null) {
			return lst;
		}

		List<User> friends = super.userservice.findFriends(super.phoneuser.getPhoneuserid());

		if (IRemoteUtils.isNotBlank(friends)) {
			lst.addAll(friends);
		}

		return lst;
	}
}
