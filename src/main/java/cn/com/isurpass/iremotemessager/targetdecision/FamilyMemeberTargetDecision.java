package cn.com.isurpass.iremotemessager.targetdecision;

import java.util.List;

import org.springframework.stereotype.Component;

import cn.com.isurpass.iremotemessager.domain.User;
import cn.com.isurpass.iremotemessager.vo.MsgUser;

@Component
public class FamilyMemeberTargetDecision extends OwnerTargetDecision 
{

	@Override
	protected List<MsgUser> descision()
	{
		List<MsgUser> lst = super.descision();
		
		if ( super.phoneuser.getFamilyid() == null
			|| super.phoneuser.getFamilyid() == 0 )
			return lst;
		
		List<User> ul = super.userservice.findByFamilyid(super.phoneuser.getFamilyid());
		
		if ( ul == null || ul.size() == 0 )
			return lst ;
		
		for ( User u : ul )
		{
			if ( u.getPhoneuserid() == super.phoneuser.getPhoneuserid())
				continue;
			MsgUser mu = super.createMsgUser(u);
			lst.add(mu);
		}
		
		return lst;
	}

}
