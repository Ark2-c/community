package cn.ark.community;

import cn.ark.community.controller.DemoController;
import org.junit.jupiter.api.Test;
import org.springframework.beans.BeansException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.test.context.ContextConfiguration;

import java.text.SimpleDateFormat;
import java.util.Date;

@SpringBootTest
@ContextConfiguration(classes = CommunityApplication.class)
class CommunityApplicationTests implements ApplicationContextAware {


	ApplicationContext applicationContext;
	@Override
	public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
		this.applicationContext = applicationContext;
	}
	@Test
	void contextLoads() {
		//主动获取容器中的bean
		DemoController controller = applicationContext.getBean(DemoController.class);
		System.out.println(controller);
	}
	@Autowired
	SimpleDateFormat simpleDateFormat;//依赖注入方式
	@Test
	void testOut(){
		System.out.println(simpleDateFormat.format(new Date()));
	}
}
