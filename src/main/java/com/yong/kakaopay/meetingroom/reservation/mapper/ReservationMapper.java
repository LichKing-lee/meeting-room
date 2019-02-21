package com.yong.kakaopay.meetingroom.reservation.mapper;

import java.time.LocalDateTime;

import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

import com.yong.kakaopay.meetingroom.reservation.dto.ReservationDto;

@Mapper
public interface ReservationMapper {
	@Insert("INSERT INTO reservation(meeting_room_id, reservation_datetime, user_name) VALUES(#{meetingRoomId}, #{reservationDateTime}, #{userName})")
	void insert(@Param("meetingRoomId") Integer meetingRoomId, @Param("reservationDateTime") LocalDateTime reservationDateTime, @Param("userName") String userName);

	@Select("SELECT reservation_id, meeting_room_id, reservation_datetime, user_name FROM reservation WHERE reservation_id = #{id}")
	ReservationDto selectOne(@Param("id") Integer id);
}
