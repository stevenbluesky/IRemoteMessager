package cn.com.isurpass.iremotemessager;

import cn.com.isurpass.iremotemessager.domain.UserShare;
import cn.com.isurpass.iremotemessager.domain.UserShareDevice;
import cn.com.isurpass.iremotemessager.service.UserShareDeviceService;
import cn.com.isurpass.iremotemessager.service.UserShareService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import javax.annotation.Resource;
import javax.transaction.Transactional;
import java.util.List;

@RunWith(SpringRunner.class)
@SpringBootTest
public class IremotemessagerApplicationTests {
	@Resource
	private UserShareService userShareService;
	@Resource
	private UserShareDeviceService userShareDeviceService;

	@Transactional
	@Test
	public void contextLoads() {
		List<UserShareDevice> specifyUser = userShareDeviceService.findSpecifyUser(441, 15949);
		for (UserShareDevice user : specifyUser) {
			System.out.println(user.getUserShare().getTouserid());
		}
	}

}
