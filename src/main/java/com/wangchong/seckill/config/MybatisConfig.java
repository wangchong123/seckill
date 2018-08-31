package com.wangchong.seckill.config;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.context.annotation.Configuration;

/**
 * @Author: wangchong
 * @Description
 * @Date : Created in 10:04 2018/8/31
 */
@Configuration
@MapperScan(basePackages = "com.wangchong.seckill.dao")
public class MybatisConfig {
}
