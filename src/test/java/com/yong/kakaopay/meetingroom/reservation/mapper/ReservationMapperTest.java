package com.yong.kakaopay.meetingroom.reservation.mapper;

import static org.assertj.core.api.Assertions.assertThat;

import java.time.LocalDateTime;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import com.yong.kakaopay.meetingroom.reservation.dto.ReservationDto;

@RunWith(SpringRunner.class)
@SpringBootTest
public class ReservationMapperTest {
	@Autowired
	private ReservationMapper reservationMapper;

	@Test
	public void insert() {
		reservationMapper.insert(1, LocalDateTime.of(2019, 2, 21, 17, 49), "changyong");
		ReservationDto dto = reservationMapper.selectOne(1);

		assertThat(dto.getReservationId()).isEqualTo(1);
		assertThat(dto.getMeetingRoomId()).isEqualTo(1);
		assertThat(dto.getReservationDatetime()).isEqualTo(LocalDateTime.of(2019, 2, 21, 17, 49));
		assertThat(dto.getUserName()).isEqualTo("changyong");
	}
}