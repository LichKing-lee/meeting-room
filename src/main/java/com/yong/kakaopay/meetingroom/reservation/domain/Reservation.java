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
	private LocalDateTime startDateTime;
	private LocalDateTime endDateTime;
	private String userName;

	public Reservation(MeetingRoom meetingRoom, LocalDateTime startDateTime, LocalDateTime endDateTime,
		String userName) {
		this.meetingRoom = meetingRoom;
		this.startDateTime = startDateTime;
		this.endDateTime = endDateTime;
		this.userName = userName;
	}

	@Data
	public static class Request {
		private LocalDateTime startDateTime;
		private LocalDateTime endDateTime;
		private String userName;

		public Reservation asReservation(Integer meetingRoomId) {
			MeetingRoom meetingRoom = new MeetingRoom(meetingRoomId);
			return new Reservation(meetingRoom, startDateTime, endDateTime, userName);
		}
	}
}
