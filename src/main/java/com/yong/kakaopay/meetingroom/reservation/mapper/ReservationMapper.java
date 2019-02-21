package com.yong.kakaopay.meetingroom.reservation.mapper;

import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

import com.yong.kakaopay.meetingroom.reservation.domain.Reservation;
import com.yong.kakaopay.meetingroom.reservation.dto.ReservationDto;

@Mapper
public interface ReservationMapper {
	@Insert("INSERT INTO reservation(meeting_room_id, start_datetime, end_datetime, user_name) "
		+ "VALUES(#{reservation.meetingRoom.id}, #{reservation.startDateTime}, #{reservation.endDateTime}, #{reservation.userName})")
	void insert(@Param("reservation") Reservation reservation);

	@Select("SELECT reservation_id, meeting_room_id, start_datetime, end_datetime, user_name FROM reservation WHERE reservation_id = #{id}")
	ReservationDto selectOne(@Param("id") Integer id);
}
