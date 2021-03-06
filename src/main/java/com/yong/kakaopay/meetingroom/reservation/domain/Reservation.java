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
	private int repeatCount;

	public Reservation(MeetingRoom meetingRoom, LocalDateTime startDateTime, LocalDateTime endDateTime,
		String userName) {
		this(meetingRoom, startDateTime, endDateTime, userName ,0);
	}

	public Reservation(MeetingRoom meetingRoom, LocalDateTime startDateTime, LocalDateTime endDateTime,
		String userName, int repeatCount) {
		this.meetingRoom = meetingRoom;
		this.startDateTime = startDateTime;
		this.endDateTime = endDateTime;
		this.userName = userName;
		this.repeatCount = repeatCount;
	}

	@Data
	public static class Dto {
		private Integer reservationId;
		private Integer meetingRoomId;
		private LocalDateTime startDateTime;
		private LocalDateTime endDateTime;
		private String userName;
		private int repeatCount;

		public Reservation asReservation() {
			MeetingRoom meetingRoom = new MeetingRoom(meetingRoomId);

			return new Reservation(reservationId, meetingRoom, startDateTime, endDateTime, userName, repeatCount);
		}

		public List<Reservation> asReservations(Integer meetingRoomId) {
			MeetingRoom meetingRoom = new MeetingRoom(meetingRoomId);

			return IntStream.rangeClosed(0, repeatCount)
				.mapToObj(n -> new Reservation(meetingRoom, startDateTime.plusDays(n * 7), endDateTime.plusDays(n * 7), userName, repeatCount))
				.collect(toList());
		}
	}
}
