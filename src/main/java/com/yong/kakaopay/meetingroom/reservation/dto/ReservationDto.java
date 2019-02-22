package com.yong.kakaopay.meetingroom.reservation.dto;

import java.time.LocalDateTime;

import com.yong.kakaopay.meetingroom.reservation.domain.Reservation;
import lombok.Data;

@Data
public class ReservationDto {
	private Integer reservationId;
	private Integer meetingRoomId;
	private LocalDateTime startDatetime;
	private LocalDateTime endDatetime;
	private String userName;
	private boolean isRepeated;

	public Reservation asReservation() {
		Reservation reservation = new Reservation();
		reservation.setId(reservationId);
		reservation.setStartDateTime(startDatetime);
		reservation.setEndDateTime(endDatetime);
		reservation.setUserName(userName);
		reservation.setRepeated(isRepeated);

		return reservation;
	}
}
