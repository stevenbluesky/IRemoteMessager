package cn.com.isurpass.iremotemessager.targetdecision;

import java.util.List;

import cn.com.isurpass.iremotemessager.domain.User;
import cn.com.isurpass.iremotemessager.common.util.IRemoteUtils;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.stereotype.Component;

/**
 * 家庭成员
 * @author jwzh
 */
@Component("cn.com.isurpass.iremotemessager.targetdecision.FamilyMemeberTargetDecision")
public class FamilyMemeberTargetDecision extends OwnerTargetDecision
{
	private static Log log = LogFactory.getLog(FamilyMemeberTargetDecision.class);

	@Override
	protected List<User> descision()
	{
		List<User> lst = super.descision();

		/*if (!check()) {
			log.info("push fail, can't find decision by this parameter");
			return lst;
		}*/

		if (super.phoneuser == null || IRemoteUtils.isBlank(super.phoneuser.getFamilyid())) {
			return lst;
		}
		
		List<User> ul = super.userservice.findByFamilyid(super.phoneuser.getFamilyid());

		lst = copyUser(lst, ul);
		return lst;
	}

	private boolean check() {
		return eventparameters.containsKey("zwavedeviceid")
				|| eventparameters.containsKey("zwavesubdeviceid")
				|| eventparameters.containsKey("infrareddeviceid")
				|| eventparameters.containsKey("cameraid")
				|| eventparameters.containsKey("deviceid")
				|| eventparameters.containsKey("partitionid");
	}
}
