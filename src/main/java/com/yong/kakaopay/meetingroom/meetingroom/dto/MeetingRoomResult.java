package com.yong.kakaopay.meetingroom.meetingroom.dto;

import java.util.List;

import com.yong.kakaopay.meetingroom.meetingroom.domain.MeetingRoom;
import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class MeetingRoomResult {
	private List<MeetingRoom> meetingRooms;
}
