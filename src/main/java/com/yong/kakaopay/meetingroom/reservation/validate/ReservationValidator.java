package com.yong.kakaopay.meetingroom.reservation.validate;

import com.yong.kakaopay.meetingroom.reservation.domain.Reservation;

public interface ReservationValidator {
	void validate(Reservation reservation);
}
