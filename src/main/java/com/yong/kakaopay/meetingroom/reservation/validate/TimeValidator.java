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
			throw new ReservationException("30분 단위로 예약가능합니다.");
		}

		if(!startDateTime.isBefore(endDateTime)) {
			throw new ReservationException("유효하지않은 시간입니다.");
		}
	}

	private boolean validateMinuteUnit(LocalDateTime dateTime) {
		return dateTime.getMinute() % 30 == 0;
	}
}
