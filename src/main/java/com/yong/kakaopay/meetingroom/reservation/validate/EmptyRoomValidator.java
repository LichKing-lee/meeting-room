package com.yong.kakaopay.meetingroom.reservation.validate;

import org.springframework.stereotype.Component;

import com.yong.kakaopay.meetingroom.reservation.domain.Reservation;
import com.yong.kakaopay.meetingroom.reservation.dto.ReservationDto;
import com.yong.kakaopay.meetingroom.reservation.exception.ReservationException;
import com.yong.kakaopay.meetingroom.reservation.mapper.ReservationMapper;
import lombok.AllArgsConstructor;

@Component
@AllArgsConstructor
public class EmptyRoomValidator implements ReservationValidator {
	private ReservationMapper reservationMapper;

	@Override
	public void validate(Reservation reservation) {
		ReservationDto dto = reservationMapper.selectByMeetingRoomIdAndDateTime(reservation);

		if(dto != null) {
			if(!dto.getEndDatetime().isEqual(reservation.getStartDateTime())) {
				throw new ReservationException("Reserved room");
			}
		}
	}
}
