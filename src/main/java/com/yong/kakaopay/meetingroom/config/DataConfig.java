package com.yong.kakaopay.meetingroom.config;

import javax.sql.DataSource;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.jdbc.datasource.DataSourceTransactionManager;
import org.springframework.transaction.annotation.EnableTransactionManagement;

@Configuration
@MapperScan(basePackages = "com.yong.kakaopay.meetingroom")
@EnableTransactionManagement
public class DataConfig {
	@Bean
	public DataSourceTransactionManager dataSourceTransactionManager(DataSource dataSource) {
		return new DataSourceTransactionManager(dataSource);
	}
}
