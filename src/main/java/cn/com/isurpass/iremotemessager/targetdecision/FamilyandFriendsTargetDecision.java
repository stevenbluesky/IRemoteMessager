package cn.com.isurpass.iremotemessager.targetdecision;

import java.util.List;

import org.springframework.stereotype.Component;

import cn.com.isurpass.iremotemessager.domain.User;
import cn.com.isurpass.iremotemessager.vo.MsgUser;

@Component
public class FamilyandFriendsTargetDecision extends OwnerTargetDecision 
{

	@Override
	protected List<MsgUser> descision()
	{
		List<MsgUser> lst = super.descision();
		
		//TODO: 
		
		return lst;
	}

}
