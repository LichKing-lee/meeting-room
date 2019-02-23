package com.yong.kakaopay.meetingroom.meetingroom.service;

import static java.util.stream.Collectors.toList;

import java.util.List;

import org.springframework.stereotype.Service;

import com.yong.kakaopay.meetingroom.meetingroom.domain.MeetingRoom;
import com.yong.kakaopay.meetingroom.meetingroom.mapper.MeetingRoomMapper;
import com.yong.kakaopay.meetingroom.reservation.service.ReservationService;
import lombok.AllArgsConstructor;

@Service
@AllArgsConstructor
public class MeetingRoomService {
	private MeetingRoomMapper meetingRoomMapper;
	private ReservationService reservationService;

	public List<MeetingRoom> getAll() {
		List<MeetingRoom> meetingRooms = meetingRoomMapper.selectAll();

		return meetingRooms.stream()
			.peek(room -> room.setReservations(reservationService.getReservationsByMeetingRoom(room.getId())))
			.collect(toList());
	}

	public MeetingRoom getMeetingRoomWithReservation(Integer id) {
		MeetingRoom meetingRoom = meetingRoomMapper.selectOne(id);
		meetingRoom.setReservations(reservationService.getReservationsByMeetingRoom(id));

		return meetingRoom;
	}
}
