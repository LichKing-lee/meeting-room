package com.yong.kakaopay.meetingroom.meetingroom.repository;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

import com.yong.kakaopay.meetingroom.meetingroom.domain.MeetingRoom;

@Mapper
public interface MeetingRoomMapper {
	@Select("SELECT * FROM meetingroom")
	List<MeetingRoom> selectAll();

	@Select("SELECT * FROM meetingroom WHERE id = #{id}")
	MeetingRoom selectOne(@Param("id") Integer id);
}
