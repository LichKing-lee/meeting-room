package com.yong.kakaopay.meetingroom.reservation.dto;

import java.time.LocalDateTime;

import com.yong.kakaopay.meetingroom.meetingroom.domain.MeetingRoom;
import com.yong.kakaopay.meetingroom.reservation.domain.Reservation;
import lombok.Data;

@Data
public class ReservationDto {
	private Integer reservationId;
	private Integer meetingRoomId;
	private LocalDateTime startDateTime;
	private LocalDateTime endDateTime;
	private String userName;
	private boolean isRepeated;

	public Reservation asReservation() {
		Reservation reservation = new Reservation();
		reservation.setId(reservationId);
		reservation.setMeetingRoom(new MeetingRoom(meetingRoomId));
		reservation.setStartDateTime(startDateTime);
		reservation.setEndDateTime(endDateTime);
		reservation.setUserName(userName);
		reservation.setRepeated(isRepeated);

		return reservation;
	}
}
