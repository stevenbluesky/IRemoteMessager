package cn.com.isurpass.iremotemessager.framework;

import java.util.List;

import cn.com.isurpass.iremotemessager.vo.EventData;
import cn.com.isurpass.iremotemessager.vo.MsgUser;

public interface IMessageTargetDecision {

	List<MsgUser> messageTarget( EventData	data ) ;
}
