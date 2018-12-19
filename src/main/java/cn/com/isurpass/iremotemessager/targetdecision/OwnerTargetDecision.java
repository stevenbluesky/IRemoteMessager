package cn.com.isurpass.iremotemessager.targetdecision;

import java.util.ArrayList;
import java.util.List;

import cn.com.isurpass.iremotemessager.domain.User;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

/**
 * 主人
 * @author jwzh
 */
@Component("cn.com.isurpass.iremotemessager.targetdecision.OwnerTargetDecision")
@Scope("prototype")
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
