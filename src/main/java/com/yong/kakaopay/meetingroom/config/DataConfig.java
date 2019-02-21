package com.yong.kakaopay.meetingroom.config;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.context.annotation.Configuration;

@Configuration
@MapperScan(basePackages = "com.yong.kakaopay.meetingroom")
public class DataConfig {
}
