package com.yong.kakaopay.meetingroom.reservation.validate;

import java.time.LocalDateTime;

import org.springframework.stereotype.Component;

import com.yong.kakaopay.meetingroom.reservation.domain.Reservation;
import com.yong.kakaopay.meetingroom.reservation.exception.ReservationException;

@Component
public class TimeValidator implements ReservationValidator {
	@Override
	public void validate(Reservation reservation) {
		LocalDateTime dateTime = reservation.getReservationDateTime();

		if(dateTime.getMinute() % 30 != 0) {
			throw new ReservationException("Invalid time");
		}
	}
}
