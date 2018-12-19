package cn.com.isurpass.iremotemessager.targetdecision;

import cn.com.isurpass.iremotemessager.common.util.IRemoteUtils;
import cn.com.isurpass.iremotemessager.domain.Gateway;
import cn.com.isurpass.iremotemessager.domain.User;
import cn.com.isurpass.iremotemessager.service.ZwaveDeviceShareService;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

/**
 * 设置网关主人目标决策类
 */
@Component("cn.com.isurpass.iremotemessager.targetdecision.RemoteOwnerSettingTargetDecision")
@Scope("prototype")
public class RemoteOwnerSettingTargetDecision extends TargetDecisionBase {
    @Resource
    private ZwaveDeviceShareService zwaveDeviceShareService;

    @Override
    protected List<User> descision() {
        Gateway gateway = (Gateway) domainobjects.get("gateway");
        if (gateway == null) {
            return Collections.EMPTY_LIST;
        }
        Integer oldOwnerId = eventparameters.getInteger("oldownerid");
        Integer newOnwerId = eventparameters.getInteger("newonwerid");

        List<User> oldOwnerUserList = findHasPermisionForTheRemote(oldOwnerId, gateway.getDeviceid());
        List<User> newOwnerUserList = findHasPermisionForTheRemote(newOnwerId, gateway.getDeviceid());

        oldOwnerUserList.addAll(newOwnerUserList);
        return oldOwnerUserList;
    }

    private List<User> findHasPermisionForTheRemote(Integer phoneuserid, String deviceid) {
        User user = userservice.findById(phoneuserid);
        if (IRemoteUtils.isBlank(user.getFamilyid())) {
            return Collections.EMPTY_LIST;
        }
        List<User> familys = super.userservice.findByFamilyid(super.phoneuser.getFamilyid());
        List<User> friends = super.userservice.findFriends(super.phoneuser.getPhoneuserid());
        List<Integer> userId = zwaveDeviceShareService.findSpecifyUserIdsByDeivceid(deviceid);
        List<User> userList = super.userservice.findByPhoneuseridIn(userId);

        List<User> list = new ArrayList<>();
        list.addAll(familys);
        list.addAll(friends);
        list.addAll(userList);
        return list;
    }
}
