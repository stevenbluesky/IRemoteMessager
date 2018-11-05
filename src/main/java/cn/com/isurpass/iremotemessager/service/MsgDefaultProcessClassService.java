package cn.com.isurpass.iremotemessager.service;

import cn.com.isurpass.iremotemessager.common.constant.IRemoteConstantDefine;
import cn.com.isurpass.iremotemessager.dao.MsgDefaultProcessClassDao;
import cn.com.isurpass.iremotemessager.domain.MsgDefaultProcessClass;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

@Service
public class MsgDefaultProcessClassService {
    @Resource
    private MsgDefaultProcessClassDao msgDefaultProcessClassDao;

    public String findDefaultTargetDesicionName(Integer platform, String eventcode) {
        MsgDefaultProcessClass defaultProcessorClass = findDefaultProcessorClass(platform, eventcode, IRemoteConstantDefine.PROCESSOR_CLASS_TYPE_PUSH_TARGET_DECISION);
        if (defaultProcessorClass == null || defaultProcessorClass.getMsgProcessClass() == null) {
            return null;
        }
        return defaultProcessorClass.getMsgProcessClass().getClassname();
    }

    public String findDefaultPushMethod(Integer platform, String eventcode) {
        MsgDefaultProcessClass defaultProcessorClass = findDefaultProcessorClass(platform, eventcode, IRemoteConstantDefine.PROCESSOR_CLASS_TYPE_PUSH_METHOD);
        if (defaultProcessorClass == null || defaultProcessorClass.getMsgProcessClass() == null) {
            return null;
        }
        return defaultProcessorClass.getMsgProcessClass().getClassname();
    }

    public MsgDefaultProcessClass findDefaultProcessorClass(Integer platform, String eventcode, Integer type) {
        return msgDefaultProcessClassDao.findByPlatformAndEventcodeAndType(platform, eventcode, type);
    }
}
