package com.yong.kakaopay.meetingroom.meetingroom.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

import com.yong.kakaopay.meetingroom.meetingroom.domain.MeetingRoom;

@Mapper
public interface MeetingRoomMapper {
	@Select("SELECT meeting_room_id as id, meeting_room_name as name FROM meeting_room")
	List<MeetingRoom> selectAll();

	@Select("SELECT meeting_room_id as id, meeting_room_name as name FROM meeting_room WHERE meeting_room_id = #{id}")
	MeetingRoom selectOne(@Param("id") Integer id);
}
