package com.yong.kakaopay.meetingroom.reservation.domain;

import static java.util.stream.Collectors.*;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.IntStream;

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
	private boolean isRepeated;

	public Reservation(MeetingRoom meetingRoom, LocalDateTime startDateTime, LocalDateTime endDateTime,
		String userName, boolean isRepeated) {
		this.meetingRoom = meetingRoom;
		this.startDateTime = startDateTime;
		this.endDateTime = endDateTime;
		this.userName = userName;
		this.isRepeated = isRepeated;
	}

	@Data
	public static class Request {
		private LocalDateTime startDateTime;
		private LocalDateTime endDateTime;
		private String userName;
		private int repeatCount;

		public List<Reservation> asReservations(Integer meetingRoomId) {
			MeetingRoom meetingRoom = new MeetingRoom(meetingRoomId);

			return IntStream.rangeClosed(0, repeatCount)
				.mapToObj(n -> new Reservation(meetingRoom, startDateTime.plusDays(n * 7), endDateTime.plusDays(n * 7), userName, repeatCount > 0))
				.collect(toList());
		}
	}
}
