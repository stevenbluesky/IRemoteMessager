package cn.com.isurpass.iremotemessager.targetdecision;

import java.util.ArrayList;
import java.util.List;

import cn.com.isurpass.iremotemessager.domain.User;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.stereotype.Component;

@Component
public class OwnerTargetDecision extends TargetDecisionBase 
{
	private static Log log = LogFactory.getLog(OwnerTargetDecision.class);


	@Override
	protected List<User> descision()
	{
		List<User> lst = new ArrayList<User>();

		if (phoneuser != null) {
			lst.add(phoneuser);
		}
		
		return lst;
	}
}
