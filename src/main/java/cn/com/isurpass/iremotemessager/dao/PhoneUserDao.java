package cn.com.isurpass.iremotemessager.dao;

import cn.com.isurpass.iremotemessager.domain.PhoneUser;
import org.springframework.data.repository.CrudRepository;
/**
 * @author liwenxiang
 * Date:2018/11/15
 * Time:16:22
 */
public interface PhoneUserDao extends CrudRepository<PhoneUser,Integer> {
    PhoneUser findByPhonenumberAndPlatform(String account, int i);
}
