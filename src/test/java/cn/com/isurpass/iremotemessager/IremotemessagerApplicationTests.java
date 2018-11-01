package cn.com.isurpass.iremotemessager;

import cn.com.isurpass.iremotemessager.domain.UserShare;
import cn.com.isurpass.iremotemessager.domain.UserShareDevice;
import cn.com.isurpass.iremotemessager.service.UserShareDeviceService;
import cn.com.isurpass.iremotemessager.service.UserShareService;
import cn.com.isurpass.iremotemessager.util.IRemoteUtils;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import javax.annotation.Resource;
import javax.imageio.event.IIOReadProgressListener;
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
		int[] ints = {1, 2, 3};
		Integer[] add = IRemoteUtils.add(ints, 5);
		System.out.println(add);
	}

}
