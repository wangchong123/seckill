package com.wangchong.seckill;

import com.wangchong.seckill.rabbitmq.Sender;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.data.redis.serializer.StringRedisSerializer;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest
public class SeckillApplicationTests {

	@Autowired
	private Sender sender;

	@Autowired
	private RedisTemplate redisTemplate;


	@Test
	public void contextLoads() {
		String s = redisTemplate.opsForValue().get("_kc" + 1).toString();
		System.out.println(s);

	}

}
