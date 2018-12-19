package cn.com.isurpass.iremotemessager.targetdecision;


import cn.com.isurpass.iremotemessager.domain.User;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

/**
 * 网关原主人
 * @author jwzh
 */
@Component("cn.com.isurpass.iremotemessager.targetdecision.GatewayOwnerTargetDecision")
@Scope("prototype")
public class GatewayOriginalOwnerTargetDecision extends TargetDecisionBase {

    @Override
    protected List<User> descision() {
        ArrayList<User> userList = new ArrayList<>();
        //eventparameter中有 oldownerid(原主人id) 和 newownerid(新主人id)
        Integer oldownerid = eventparameters.getInteger("oldownerid");

        if (oldownerid != null) {
            ArrayList<Integer> idList = new ArrayList<>();
            idList.add(oldownerid);
        }

        return userList;
    }
}
