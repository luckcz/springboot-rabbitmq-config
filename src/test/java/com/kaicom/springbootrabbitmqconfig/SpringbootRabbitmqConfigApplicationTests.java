package com.kaicom.springbootrabbitmqconfig;

import com.kaicom.springbootrabbitmqconfig.bean.Message;
import com.kaicom.springbootrabbitmqconfig.produce.SendTopic;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.Date;
import java.util.UUID;

@RunWith(SpringRunner.class)
@SpringBootTest
public class SpringbootRabbitmqConfigApplicationTests {
	@Autowired
	private SendTopic sendTopic ;
	@Test
	public void contextLoads() {
	}


	@Test
	public void sendMessage() throws InterruptedException {
		for(int i = 0 ; i < 10 ; i++){
			Message message = new Message();
			message.setId(i);
			message.setContent("请通知设备科的科长"+i);
			message.setTitle("紧急通知"+i);
			message.setSendTime(new Date());
			message.setSendUserName("张刷"+i);
			sendTopic.sendMessage(message);
			Thread.sleep(2000);
		}
	}
}
