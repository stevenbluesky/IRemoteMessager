package cn.com.isurpass.iremotemessager;

import cn.com.isurpass.iremotemessager.service.UserShareDeviceService;
import cn.com.isurpass.iremotemessager.service.UserShareService;
import com.alibaba.fastjson.JSONObject;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import javax.annotation.Resource;
import javax.transaction.Transactional;
import java.util.ArrayList;
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
		JSONObject json = new JSONObject();
		ArrayList<String> s = new ArrayList<>();
		s.add("86");
		s.add("123123");
		ArrayList<String> s1 = new ArrayList<>();
		s1.add("86");
		s1.add("123123");
		ArrayList<List> lists = new ArrayList<>();
		lists.add(s);
		lists.add(s1);
		json.put("phone", lists);
		System.out.println(json.toJSONString());

		String[][] phones = json.getObject("phone", String[][].class);

		for (int i = 0; i < phones.length; i++) {
			for (int j = 0; j < phones[i].length; j++) {
				System.out.println(phones[i][j]);
			}
		}
	}

}
