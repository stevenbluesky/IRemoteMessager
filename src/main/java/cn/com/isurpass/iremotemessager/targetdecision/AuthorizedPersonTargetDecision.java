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
 * 被授权人
 * @author jwzh
 */
@Component("cn.com.isurpass.iremotemessager.targetdecision.AuthorizedPersonTargetDecision")
@Scope("prototype")
public class AuthorizedPersonTargetDecision extends TargetDecisionBase {
    @Resource
    private UserShareService userShareService;

    @Override
    protected List<User> descision() {
        ArrayList<User> users = new ArrayList<>();
        Integer shareid = eventparameters.getInteger("shareid");

        if (shareid != null) {
            UserShare userShare = userShareService.find(shareid);
            if (userShare != null) {
                users.add(userservice.findById(userShare.getTouserid()));
            }
        } else {
            Integer touserid = eventparameters.getInteger("touserid");
            if (touserid != null) {
                users.add(userservice.findById(touserid));
            }
        }

        return users;
    }
}
