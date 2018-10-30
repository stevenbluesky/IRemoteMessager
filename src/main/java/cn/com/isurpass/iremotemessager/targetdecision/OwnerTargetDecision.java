package cn.com.isurpass.iremotemessager.targetdecision;

import java.util.ArrayList;
import java.util.List;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.stereotype.Component;

import cn.com.isurpass.iremotemessager.vo.MsgUser;

@Component
public class OwnerTargetDecision extends TargetDecisionBase 
{
	private static Log log = LogFactory.getLog(OwnerTargetDecision.class);
	
	
	@Override
	protected List<MsgUser> descision() 
	{
		List<MsgUser> lst = new ArrayList<MsgUser>();
		
		if ( phoneuser == null )
			return lst ;
		
		MsgUser mu = super.createMsgUser(phoneuser);
		
		if ( mu != null )
			lst.add(mu);
		
		return lst;
	}

}
