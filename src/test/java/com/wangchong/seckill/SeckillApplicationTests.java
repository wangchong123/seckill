package com.wangchong.seckill;

import com.wangchong.seckill.rabbitmq.Sender;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest
public class SeckillApplicationTests {

	@Autowired
	private Sender sender;


	@Test
	public void contextLoads() {

		sender.send(100 + "");
	}

}
