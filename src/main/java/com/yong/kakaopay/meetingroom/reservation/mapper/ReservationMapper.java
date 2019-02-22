package com.yong.kakaopay.meetingroom.reservation.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

import com.yong.kakaopay.meetingroom.reservation.domain.Reservation;
import com.yong.kakaopay.meetingroom.reservation.dto.ReservationDto;

@Mapper
public interface ReservationMapper {
	@Insert("INSERT INTO reservation(meeting_room_id, start_datetime, end_datetime, user_name, is_repeated) "
		+ "VALUES(#{reservation.meetingRoom.id}, #{reservation.startDateTime}, #{reservation.endDateTime}, #{reservation.userName}, #{reservation.isRepeated})")
	void insert(@Param("reservation") Reservation reservation);

	@Select("SELECT reservation_id, meeting_room_id, start_datetime, end_datetime, user_name FROM reservation WHERE reservation_id = #{id}")
	ReservationDto selectOne(@Param("id") Integer id);

	@Select("SELECT reservation_id, meeting_room_id, start_datetime, end_datetime, user_name, is_repeated "
		+ "FROM reservation "
		+ "WHERE meeting_room_id = #{id} ")
	List<ReservationDto> selectByMeetingRoomId(@Param("id") Integer id);

	@Select("SELECT reservation_id, meeting_room_id, start_datetime, end_datetime, user_name "
		+ "FROM reservation "
		+ "WHERE meeting_room_id = #{reservation.meetingRoom.id} "
		+ "AND (start_datetime BETWEEN #{reservation.startDateTime} AND #{reservation.endDateTime} "
		+ "OR end_datetime BETWEEN #{reservation.startDateTime} AND #{reservation.endDateTime})")
	ReservationDto selectByMeetingRoomIdAndDateTime(@Param("reservation") Reservation reservation);
}
