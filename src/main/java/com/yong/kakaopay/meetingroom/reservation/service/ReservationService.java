package com.yong.kakaopay.meetingroom.reservation.service;

import org.springframework.stereotype.Service;

import com.yong.kakaopay.meetingroom.reservation.domain.Reservation;
import com.yong.kakaopay.meetingroom.reservation.mapper.ReservationMapper;
import com.yong.kakaopay.meetingroom.reservation.validate.ReservationValidateContainer;
import lombok.AllArgsConstructor;

@Service
@AllArgsConstructor
public class ReservationService {
	private ReservationMapper reservationMapper;
	private ReservationValidateContainer reservationValidateContainer;

	public void reserve(Reservation reservation) {
		reservationValidateContainer.checkValidate(reservation);

		reservationMapper.insert(reservation);
	}
}
