package com.yong.kakaopay.meetingroom.reservation.service;

import static java.util.stream.Collectors.*;

import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.yong.kakaopay.meetingroom.reservation.domain.Reservation;
import com.yong.kakaopay.meetingroom.reservation.mapper.ReservationMapper;
import com.yong.kakaopay.meetingroom.reservation.validate.ReservationValidateContainer;
import lombok.AllArgsConstructor;

@Service
@AllArgsConstructor
public class ReservationService {
	private ReservationMapper reservationMapper;
	private ReservationValidateContainer reservationValidateContainer;

	@Transactional
	public void reserve(List<Reservation> reservations) {
		reservations.forEach(reservation -> {
			reservationValidateContainer.checkValidate(reservation);
			reservationMapper.insert(reservation);
		});
	}

	public List<Reservation> getReservationsByMeetingRoom(Integer meetingRoomId) {
		return reservationMapper.selectByMeetingRoomId(meetingRoomId).stream()
			.map(Reservation.Dto::asReservation)
			.collect(toList());
	}

	public Reservation.Dto getReservationDto(Integer reservationId) {
		return reservationMapper.selectOne(reservationId);
	}
}
