package com.yong.kakaopay.meetingroom.meetingroom.dto;

import java.util.List;

import com.yong.kakaopay.meetingroom.meetingroom.domain.MeetingRoom;
import lombok.Data;

@Data
public class MeetingRoomResult {
	private List<MeetingRoom> meetingRooms;
}
