package com.yong.kakaopay.meetingroom.reservation.controller;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.yong.kakaopay.meetingroom.reservation.domain.Reservation;
import com.yong.kakaopay.meetingroom.reservation.exception.ReservationException;
import com.yong.kakaopay.meetingroom.reservation.service.ReservationService;
import lombok.AllArgsConstructor;

@RestController
@RequestMapping("/meeting-rooms/{meetingRoomId}/reservation")
@AllArgsConstructor
public class ReservationController {
	private ReservationService reservationService;

	@PostMapping
	public void reserve(@PathVariable Integer meetingRoomId, @RequestBody Reservation.Request request) {
		reservationService.reserve(request.asReservations(meetingRoomId));
	}

	@ExceptionHandler(ReservationException.class)
	@ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
	public String handler(ReservationException e) {
		return e.getMessage();
	}
}
