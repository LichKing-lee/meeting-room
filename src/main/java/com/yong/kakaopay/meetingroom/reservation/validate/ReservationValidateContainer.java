package com.yong.kakaopay.meetingroom.reservation.validate;

import java.util.Collections;
import java.util.LinkedHashSet;
import java.util.Set;

import org.springframework.stereotype.Component;

import com.yong.kakaopay.meetingroom.reservation.domain.Reservation;

@Component
public class ReservationValidateContainer {
	private Set<ReservationValidator> set;

	public ReservationValidateContainer(TimeValidator timeValidator,
		EmptyRoomValidator emptyRoomValidator) {
		Set<ReservationValidator> set = new LinkedHashSet<>();
		set.add(timeValidator);
		set.add(emptyRoomValidator);

		this.set = Collections.unmodifiableSet(set);
	}

	public void checkValidate(Reservation reservation) {
		set.forEach(validator -> validator.validate(reservation));
	}
}
