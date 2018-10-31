package cn.com.isurpass.iremotemessager.dao;

import java.util.Collection;
import java.util.List;

import org.springframework.data.repository.CrudRepository;

import cn.com.isurpass.iremotemessager.domain.User;

public interface UserDao extends CrudRepository<User, Integer> 
{
	List<User> findByFamilyid(Integer familyid);

	List<User> findByPhoneuseridIn(Collection<Integer> phoneuserIdList);
}
