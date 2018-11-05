package cn.com.isurpass.iremotemessager.service;

import cn.com.isurpass.iremotemessager.common.constant.IRemoteConstantDefine;
import cn.com.isurpass.iremotemessager.dao.MsgEventGroupEventDao;
import cn.com.isurpass.iremotemessager.dao.MsgProcessClassDao;
import cn.com.isurpass.iremotemessager.domain.MsgDefaultProcessClass;
import cn.com.isurpass.iremotemessager.domain.MsgEventGroupEvent;
import cn.com.isurpass.iremotemessager.domain.MsgProcessClass;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

@Service
public class MsgEventGroupeventService {
    @Resource
    private MsgEventGroupEventDao msgEventGroupEventDao;
    @Resource
    private MsgProcessClassDao msgProcessClassDao;
    @Resource
    private MsgDefaultProcessClassService msgDefaultProcessClassService;

    public MsgEventGroupEvent findByEventCodeAndPlatform(String eventCode, Integer platform) {
        return msgEventGroupEventDao.findByMsgEventType_EventcodeAndPlatform(eventCode, platform);
    }

    public MsgProcessClass findMsgPushTargetDecision(String eventCode, Integer platform) {
        Integer pushTargetDecisionId = msgEventGroupEventDao.findMsgPushTargetDecisionId(eventCode, platform);
        if (pushTargetDecisionId == null) {
            return null;
        }

        return msgProcessClassDao.findById(pushTargetDecisionId).orElse(null);
    }

    public MsgProcessClass findMsgPushMethod(String eventCode, Integer platform) {
        Integer pushTargetDecisionId = msgEventGroupEventDao.findMsgPushMethodId(eventCode, platform);
        if (pushTargetDecisionId == null) {
            return null;
        }

        return msgProcessClassDao.findById(pushTargetDecisionId).orElse(null);
    }

    public String findMsgPushTargetDecisionClassName(String eventCode, Integer platform) {
        MsgProcessClass msgPushTargetDecision = findMsgPushTargetDecision(eventCode, platform);
        if (msgPushTargetDecision == null) {
            String className = msgDefaultProcessClassService.findDefaultTargetDesicionName(platform, eventCode);
            if (className == null) {
                className = msgDefaultProcessClassService.findDefaultTargetDesicionName(IRemoteConstantDefine.DEFAULT_PLATFORM, eventCode);
                if (className == null) {
                    className = msgDefaultProcessClassService.findDefaultTargetDesicionName(platform, IRemoteConstantDefine.DEFAULT_EVENT_CODE);
                    if (className == null) {
                        className = msgDefaultProcessClassService.findDefaultTargetDesicionName(IRemoteConstantDefine.DEFAULT_PLATFORM, IRemoteConstantDefine.DEFAULT_EVENT_CODE);
                    }
                }
            }
            return className;
        }

        return  msgPushTargetDecision.getClassname();
    }

    public String findMsgPushMethodClassName(String eventCode, Integer platform) {
        MsgProcessClass pushMethod = findMsgPushMethod(eventCode, platform);
        if (pushMethod == null) {
            String className = msgDefaultProcessClassService.findDefaultPushMethod(platform, eventCode);
            if (className == null) {
                className = msgDefaultProcessClassService.findDefaultPushMethod(IRemoteConstantDefine.DEFAULT_PLATFORM, eventCode);
                if (className == null) {
                    className = msgDefaultProcessClassService.findDefaultPushMethod(platform, IRemoteConstantDefine.DEFAULT_EVENT_CODE);
                    if (className == null) {
                        className = msgDefaultProcessClassService.findDefaultPushMethod(IRemoteConstantDefine.DEFAULT_PLATFORM, IRemoteConstantDefine.DEFAULT_EVENT_CODE);
                    }
                }
            }
            return className;
        }

        return  pushMethod.getClassname();
    }
}
