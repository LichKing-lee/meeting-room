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
	public void validate_시간단위정상() {
		timeValidator.validate(mockReservation(LocalDateTime.of(2019, 2, 21, 20, 0),
			LocalDateTime.of(2019, 2, 21, 22, 30)));
	}

	@Test(expected = ReservationException.class)
	public void validate_시간단위비정상() {
		timeValidator.validate(mockReservation(LocalDateTime.of(2019, 2, 21, 20, 10),
			LocalDateTime.of(2019, 2, 21, 22, 40)));
	}

	@Test(expected = ReservationException.class)
	public void validate_시작시간단위비정상() {
		timeValidator.validate(mockReservation(LocalDateTime.of(2019, 2, 21, 20, 10),
			LocalDateTime.of(2019, 2, 21, 22, 30)));
	}

	@Test(expected = ReservationException.class)
	public void validate_종료시간단위비정상() {
		timeValidator.validate(mockReservation(LocalDateTime.of(2019, 2, 21, 20, 0),
			LocalDateTime.of(2019, 2, 21, 22, 35)));
	}

	@Test(expected = ReservationException.class)
	public void validate_시작시간과_종료시간이_같음() {
		timeValidator.validate(mockReservation(LocalDateTime.of(2019, 2, 21, 20, 0),
			LocalDateTime.of(2019, 2, 21, 20, 0)));
	}

	@Test(expected = ReservationException.class)
	public void validate_시작시간이_종료시간_이후() {
		timeValidator.validate(mockReservation(LocalDateTime.of(2019, 2, 21, 20, 30),
			LocalDateTime.of(2019, 2, 21, 20, 0)));
	}

	@Test
	public void validate_시작시간이_종료시간_이전() {
		timeValidator.validate(mockReservation(LocalDateTime.of(2019, 2, 21, 18, 30),
			LocalDateTime.of(2019, 2, 21, 20, 0)));
	}

	private Reservation mockReservation(LocalDateTime start, LocalDateTime end) {
		Reservation reservation = new Reservation();
		reservation.setStartDateTime(start);
		reservation.setEndDateTime(end);

		return reservation;
	}
}