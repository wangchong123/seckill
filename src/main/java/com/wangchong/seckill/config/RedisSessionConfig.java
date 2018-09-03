package com.wangchong.seckill.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.session.data.redis.config.annotation.web.http.EnableRedisHttpSession;

/**
 * @Author: wangchong
 * @Description
 * @Date : Created in 14:00 2018/9/3
 */
@Configuration
@EnableRedisHttpSession
public class RedisSessionConfig {
}
