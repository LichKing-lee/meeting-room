package com.yong.kakaopay.meetingroom.reservation.validate;

import java.time.LocalDateTime;

import org.junit.Before;
import org.junit.Test;

import com.yong.kakaopay.meetingroom.reservation.domain.Reservation;
import com.yong.kakaopay.meetingroom.reservation.exception.ReservationException;

public class TimeValidatorTest {
	private TimeValidator timeValidator;

	@Before
	public void setUp() {
		timeValidator = new TimeValidator();
	}

	@Test
	public void validate_0분() {
		timeValidator.validate(mockReservation(0));
	}

	@Test(expected = ReservationException.class)
	public void validate_15분() {
		timeValidator.validate(mockReservation(15));
	}

	@Test
	public void validate_30분() {
		timeValidator.validate(mockReservation(30));
	}

	@Test(expected = ReservationException.class)
	public void validate_45분() {
		timeValidator.validate(mockReservation(45));
	}

	private Reservation mockReservation(int minute) {
		Reservation reservation = new Reservation();
		reservation.setReservationDateTime(LocalDateTime.of(2018, 2, 21, 19, minute));

		return reservation;
	}
}