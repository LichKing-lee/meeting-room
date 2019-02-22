package com.yong.kakaopay.meetingroom.reservation.validate;

import static org.mockito.BDDMockito.*;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;

import com.yong.kakaopay.meetingroom.reservation.domain.Reservation;
import com.yong.kakaopay.meetingroom.reservation.dto.ReservationDto;
import com.yong.kakaopay.meetingroom.reservation.exception.ReservationException;
import com.yong.kakaopay.meetingroom.reservation.mapper.ReservationMapper;

@RunWith(MockitoJUnitRunner.class)
public class EmptyRoomValidatorTest {
	@InjectMocks
	private EmptyRoomValidator emptyRoomValidator;

	@Mock
	private ReservationMapper reservationMapper;

	@Test(expected = ReservationException.class)
	public void 이미예약데이터가_있음() {
		Reservation reservation = new Reservation();

		given(reservationMapper.selectByMeetingRoomIdAndDateTime(reservation)).willReturn(new ReservationDto());

		emptyRoomValidator.validate(reservation);
	}
}