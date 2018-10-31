package cn.com.isurpass.iremotemessager.framework;

import java.util.List;

import cn.com.isurpass.iremotemessager.domain.User;
import cn.com.isurpass.iremotemessager.vo.EventData;

public interface IMessageTargetDecision {

	List<User> messageTarget(EventData	data ) ;
}
