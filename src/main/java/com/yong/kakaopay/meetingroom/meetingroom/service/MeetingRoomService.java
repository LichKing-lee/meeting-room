package com.yong.kakaopay.meetingroom.meetingroom.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.yong.kakaopay.meetingroom.meetingroom.domain.MeetingRoom;
import com.yong.kakaopay.meetingroom.meetingroom.mapper.MeetingRoomMapper;
import lombok.AllArgsConstructor;

@Service
@AllArgsConstructor
public class MeetingRoomService {
	private MeetingRoomMapper meetingRoomMapper;

	public List<MeetingRoom> getAll() {
		return meetingRoomMapper.selectAll();
	}

	public MeetingRoom getOne(Integer id) {
		return meetingRoomMapper.selectOne(id);
	}
}
