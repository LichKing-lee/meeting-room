package com.yong.kakaopay.meetingroom.meetingroom.domain;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class MeetingRoom {
	private Integer id;
	private String name;

	public MeetingRoom(Integer id) {
		this.id = id;
	}
}
