package cn.com.isurpass.iremotemessager.dao;

import cn.com.isurpass.iremotemessager.domain.PhoneUserAttribute;
import org.springframework.data.repository.CrudRepository;

/**
 * @author liwenxiang
 * Date:2018/11/15
 * Time:16:22
 */
public interface PhoneUserAttributeDao extends CrudRepository<PhoneUserAttribute,Integer> {
    PhoneUserAttribute findByPhoneuseridAndCode(int phoneuserid, String admin);
}
