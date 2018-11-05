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

    public MsgDefaultProcessClass findDefaultProcessorClassBySubtype(Integer platform, String eventcode, Integer type, Integer subtype) {
        return  msgDefaultProcessClassDao.findByPlatformAndEventcodeAndTypeAndSubtype(platform, eventcode, type, subtype);
    }

    public String findDefaultProcessorClassNameBaseBySubtype(Integer platform, String eventcode, Integer type, Integer subtype) {
        MsgDefaultProcessClass processClass = msgDefaultProcessClassDao.findByPlatformAndEventcodeAndTypeAndSubtype(platform, eventcode, type, subtype);
        if (processClass == null || processClass.getMsgProcessClass() == null) {
            return null;
        }
        return processClass.getMsgProcessClass().getClassname();
    }
    public String findDefaultProcessorClassNameBySubtype(Integer platform, String eventcode, Integer type, Integer subtype) {
        String name = findDefaultProcessorClassNameBaseBySubtype(platform, eventcode, type, subtype);
        if (name == null) {
            name = findDefaultProcessorClassNameBaseBySubtype(IRemoteConstantDefine.DEFAULT_PLATFORM, eventcode, type, subtype);
            if (name == null) {
                name = findDefaultProcessorClassNameBaseBySubtype(platform, IRemoteConstantDefine.DEFAULT_EVENT_CODE, type, subtype);
                if (name == null) {
                    name = findDefaultProcessorClassNameBaseBySubtype(IRemoteConstantDefine.DEFAULT_PLATFORM, IRemoteConstantDefine.DEFAULT_EVENT_CODE, type, subtype);
                }
            }
        }
        return name;
    }
}
