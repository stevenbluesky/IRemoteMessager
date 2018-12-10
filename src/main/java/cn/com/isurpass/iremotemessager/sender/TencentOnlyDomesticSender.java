package cn.com.isurpass.iremotemessager.sender;

import cn.com.isurpass.iremotemessager.common.constant.IRemoteConstantDefine;
import org.springframework.stereotype.Component;

@Component("cn.com.isurpass.iremotemessager.sender.TencentOnlyDomesticSender")
public class TencentOnlyDomesticSender extends TencentSender0 {
    @Override
    protected boolean check(String countryCode) {
        return IRemoteConstantDefine.DEFAULT_COUNTRYCODE.equals(removePrefix(countryCode));
    }

    private String removePrefix(String countryCode) {
        return countryCode != null
                ? countryCode.replace("+", "")
                : countryCode;
    }
}
