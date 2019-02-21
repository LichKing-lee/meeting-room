package com.yong.kakaopay.meetingroom.meetingroom.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import com.yong.kakaopay.meetingroom.meetingroom.domain.MeetingRoom;
import com.yong.kakaopay.meetingroom.meetingroom.dto.MeetingRoomResult;
import com.yong.kakaopay.meetingroom.meetingroom.service.MeetingRoomService;
import lombok.AllArgsConstructor;

@RestController
@AllArgsConstructor
public class MeetingRoomController {
	private MeetingRoomService meetingRoomService;

	@GetMapping("/meeting-rooms")
	public MeetingRoomResult meetingRooms() {
		return new MeetingRoomResult(meetingRoomService.getAll());
	}

	@GetMapping("/meeting-rooms/{id}")
	public MeetingRoom meetingRoom(@PathVariable Integer id) {
		return meetingRoomService.getOne(id);
	}
}
