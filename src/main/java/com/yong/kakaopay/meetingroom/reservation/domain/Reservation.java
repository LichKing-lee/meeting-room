package com.yong.kakaopay.meetingroom.reservation.domain;

import java.time.LocalDateTime;

import com.yong.kakaopay.meetingroom.meetingroom.domain.MeetingRoom;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class Reservation {
	private Integer id;
	private MeetingRoom meetingRoom;
	private LocalDateTime reservationDateTime;
	private String userName;

	@Data
	public static class Request {
		private Integer meetingRoomId;
		private LocalDateTime reservationDateTime;
		private String userName;
	}
}
