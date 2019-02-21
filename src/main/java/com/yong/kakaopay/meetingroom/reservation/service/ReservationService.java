package com.yong.kakaopay.meetingroom.reservation.service;

import org.springframework.stereotype.Service;

import com.yong.kakaopay.meetingroom.reservation.domain.Reservation;
import com.yong.kakaopay.meetingroom.reservation.mapper.ReservationMapper;
import lombok.AllArgsConstructor;

@Service
@AllArgsConstructor
public class ReservationService {
	private ReservationMapper reservationMapper;

	public void reserve(Reservation reservation) {
		reservationMapper.insert(reservation);
	}
}
