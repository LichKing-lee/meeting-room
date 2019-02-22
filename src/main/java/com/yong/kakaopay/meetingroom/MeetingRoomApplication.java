package com.yong.kakaopay.meetingroom;

import javax.sql.DataSource;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.jdbc.datasource.DataSourceTransactionManager;
import org.springframework.transaction.PlatformTransactionManager;

@SpringBootApplication
public class MeetingRoomApplication {

	public static void main(String[] args) {
		SpringApplication.run(MeetingRoomApplication.class, args);
	}

	@Bean
	public PlatformTransactionManager dataSourceTransactionManager(DataSource dataSource) {
		DataSourceTransactionManager dataSourceTransactionManager = new DataSourceTransactionManager(dataSource);
		dataSourceTransactionManager.setGlobalRollbackOnParticipationFailure(false);
		return dataSourceTransactionManager;
	}
}
