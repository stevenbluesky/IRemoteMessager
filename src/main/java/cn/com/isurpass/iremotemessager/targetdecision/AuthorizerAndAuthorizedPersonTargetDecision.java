package cn.com.isurpass.iremotemessager.targetdecision;

import cn.com.isurpass.iremotemessager.domain.User;
import cn.com.isurpass.iremotemessager.domain.UserShare;
import cn.com.isurpass.iremotemessager.service.UserShareService;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.List;

/**
 *  授权人和被授权人
 * @author jwzh
 */
@Component("cn.com.isurpass.iremotemessager.targetdecision.AuthorizerAndAuthorizedPersonTargetDecision")
@Scope("prototype")
public class AuthorizerAndAuthorizedPersonTargetDecision extends TargetDecisionBase {
    @Resource
    private UserShareService userShareService;

    @Override
    protected List<User> descision() {
        Integer shareid = eventparameters.getInteger("shareid");

        if (shareid != null) {
            UserShare userShare = userShareService.find(shareid);
            if (userShare != null) {
                return userservice.find(userShare.getShareuserid(), userShare.getTouserid());
            }
        }

        Integer shareuserid = eventparameters.getInteger("shareuserid");
        Integer touserid = eventparameters.getInteger("touserid");

        if (shareuserid == null || touserid == null) {
            return new ArrayList<>();
        }
        return userservice.find(shareuserid, touserid);
    }
}
