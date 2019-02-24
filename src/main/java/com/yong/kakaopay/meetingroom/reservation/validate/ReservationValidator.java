package com.yong.kakaopay.meetingroom.reservation.validate;

import com.yong.kakaopay.meetingroom.reservation.domain.Reservation;

// 유효성체크가 필요하면 구현
public interface ReservationValidator {
	void validate(Reservation reservation);
}
