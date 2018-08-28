package cn.com.isurpass.zufang.typeincustomer;

import cn.com.isurpass.zufang.typeincustomer.util.HttpsUtils;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest
public class TypeinCustomerApplicationTests {

	@Test
	public void contextLoads() {
	}

	@Test
	public void teststatic(){
		System.out.println(HttpsUtils.getToken());
	}
}
