package com.yong.kakaopay.meetingroom.reservation.dto;

import java.time.LocalDateTime;

import lombok.Data;

@Data
public class ReservationDto {
	private Integer reservationId;
	private Integer meetingRoomId;
	private LocalDateTime startDatetime;
	private LocalDateTime endDatetime;
	private String userName;
}