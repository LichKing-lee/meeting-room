package com.yong.kakaopay.meetingroom.reservation.mapper;

import static org.assertj.core.api.Assertions.assertThat;

import java.time.LocalDateTime;
import java.util.List;

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
			LocalDateTime.of(2019, 2, 21, 18, 30), "changyong");

		reservationMapper.insert(reservation);
		ReservationDto dto = reservationMapper.selectOne(1);

		assertThat(dto.getReservationId()).isEqualTo(1);
		assertThat(dto.getMeetingRoomId()).isEqualTo(1);
		assertThat(dto.getStartDatetime()).isEqualTo(LocalDateTime.of(2019, 2, 21, 18, 0));
		assertThat(dto.getEndDatetime()).isEqualTo(LocalDateTime.of(2019, 2, 21, 18, 30));
		assertThat(dto.getUserName()).isEqualTo("changyong");
	}

	@Test
	public void selectByMeetingRoomId() {
		Reservation reservation1 = new Reservation(null, new MeetingRoom(1, "회의실1"),
			LocalDateTime.of(2019, 2, 21, 18, 0),
			LocalDateTime.of(2019, 2, 21, 18, 30), "changyong");

		Reservation reservation2 = new Reservation(null, new MeetingRoom(1, "회의실1"),
			LocalDateTime.of(2019, 2, 22, 18, 0),
			LocalDateTime.of(2019, 2, 22, 18, 30), "changyong");

		Reservation reservation3 = new Reservation(null, new MeetingRoom(1, "회의실1"),
			LocalDateTime.of(2019, 2, 23, 18, 0),
			LocalDateTime.of(2019, 2, 23, 18, 30), "changyong");

		reservationMapper.insert(reservation1);
		reservationMapper.insert(reservation2);
		reservationMapper.insert(reservation3);
		List<ReservationDto> dtos = reservationMapper.selectByMeetingRoomId(1);

		assertThat(dtos.size()).isEqualTo(3);
		assertThat(dtos.get(0).getReservationId()).isEqualTo(1);
		assertThat(dtos.get(1).getReservationId()).isEqualTo(2);
		assertThat(dtos.get(2).getReservationId()).isEqualTo(3);
	}

	@Test
	public void selectByMeetingRoomIdAndDateTime() {
		Reservation reservation = new Reservation(null, new MeetingRoom(1, "회의실1"),
			LocalDateTime.of(2019, 2, 21, 18, 0),
			LocalDateTime.of(2019, 2, 21, 18, 30), "changyong");

		ReservationDto dto = reservationMapper.selectByMeetingRoomIdAndDateTime(reservation);

		assertThat(dto).isNull();
	}
}