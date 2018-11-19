package cn.com.isurpass.iremotemessager.service;

import cn.com.isurpass.iremotemessager.dao.PhoneUserAttributeDao;
import cn.com.isurpass.iremotemessager.dao.PhoneUserDao;
import cn.com.isurpass.iremotemessager.domain.PhoneUser;
import cn.com.isurpass.iremotemessager.domain.PhoneUserAttribute;
import org.apache.commons.lang.StringUtils;
import org.jasypt.digest.PooledStringDigester;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

/**
 * @author liwenxiang
 * Date:2018/11/15
 * Time:16:23
 */
@Service
public class PhoneUserService {
    @Resource
    private PhoneUserDao pd;
    @Resource
    private PhoneUserAttributeDao puad;
    private static PooledStringDigester digester = new PooledStringDigester();

    static {
        digester.setPoolSize(8);
        digester.setIterations(1059);
    }

    public PhoneUser checkLoginData(String account, String password,String platform) {
        if(StringUtils.isBlank(account)||StringUtils.isBlank(password)){
            return null;
        }
        int pf = 0;
        if(StringUtils.isNotBlank(platform)){
            pf = Integer.parseInt(platform);
        }
        PhoneUser p = pd.findByPhonenumberAndPlatform(account,pf);
        if(p==null||StringUtils.isBlank(account)||StringUtils.isBlank(password)||!checkPassword(p,account,password,p.getPassword())){
            return null;
        }
        PhoneUserAttribute pua = puad.findByPhoneuseridAndCode(p.getPhoneuserid(),"admin");
        if(pua!=null&&"true".equals(pua.getValue())){
            return p;
        }
        return null;
    }
    public boolean checkPassword(PhoneUser p,String loginname , String password , String enpassword) {
        try {
            return digester.matches(loginname + password, enpassword);
        }
        catch(Throwable t){
            return false ;
        }
    }
}
