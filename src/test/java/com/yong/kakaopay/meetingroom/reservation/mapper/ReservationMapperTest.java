package com.yong.kakaopay.meetingroom.reservation.mapper;

import static org.assertj.core.api.Assertions.assertThat;

import java.time.LocalDateTime;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import com.yong.kakaopay.meetingroom.meetingroom.domain.MeetingRoom;
import com.yong.kakaopay.meetingroom.reservation.domain.Reservation;
import com.yong.kakaopay.meetingroom.reservation.dto.ReservationDto;

@RunWith(SpringRunner.class)
@SpringBootTest
public class ReservationMapperTest {
	@Autowired
	private ReservationMapper reservationMapper;

	@Test
	public void insert() {
		Reservation reservation = new Reservation(null, new MeetingRoom(1, "회의실1"),
			LocalDateTime.of(2019, 2, 21, 18, 0),
			LocalDateTime.of(2019, 2, 21, 18, 3), "changyong");

		reservationMapper.insert(reservation);
		ReservationDto dto = reservationMapper.selectOne(1);

		assertThat(dto.getReservationId()).isEqualTo(1);
		assertThat(dto.getMeetingRoomId()).isEqualTo(1);
		assertThat(dto.getStartDatetime()).isEqualTo(LocalDateTime.of(2019, 2, 21, 18, 0));
		assertThat(dto.getEndDatetime()).isEqualTo(LocalDateTime.of(2019, 2, 21, 18, 3));
		assertThat(dto.getUserName()).isEqualTo("changyong");
	}
}