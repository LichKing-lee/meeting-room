package com.yong.kakaopay.meetingroom.meetingroom.domain;

import java.util.List;

import com.yong.kakaopay.meetingroom.reservation.domain.Reservation;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class MeetingRoom {
	private Integer id;
	private String name;
	private List<Reservation> reservations;

	public MeetingRoom(Integer id) {
		this.id = id;
	}

	public MeetingRoom(Integer id, String name) {
		this(id);
		this.name = name;
	}
}
