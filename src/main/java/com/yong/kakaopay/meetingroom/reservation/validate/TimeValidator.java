package com.yong.kakaopay.meetingroom.reservation.validate;

import java.time.LocalDateTime;

import org.springframework.stereotype.Component;

import com.yong.kakaopay.meetingroom.reservation.domain.Reservation;
import com.yong.kakaopay.meetingroom.reservation.exception.ReservationException;

@Component
public class TimeValidator implements ReservationValidator {
	@Override
	public void validate(Reservation reservation) {
		LocalDateTime startDateTime = reservation.getStartDateTime();
		LocalDateTime endDateTime = reservation.getEndDateTime();

		if(!(validateMinuteUnit(startDateTime) && validateMinuteUnit(endDateTime))) {
			throw new ReservationException("Invalid minute unit");
		}

		if(!startDateTime.isBefore(endDateTime)) {
			throw new ReservationException("Invalid end date time");
		}
	}

	private boolean validateMinuteUnit(LocalDateTime dateTime) {
		return dateTime.getMinute() % 30 == 0;
	}
}
