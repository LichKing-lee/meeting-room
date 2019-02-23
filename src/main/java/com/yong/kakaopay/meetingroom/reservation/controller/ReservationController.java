package com.yong.kakaopay.meetingroom.reservation.controller;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.yong.kakaopay.meetingroom.reservation.domain.Reservation;
import com.yong.kakaopay.meetingroom.reservation.dto.ReservationDto;
import com.yong.kakaopay.meetingroom.reservation.exception.ReservationException;
import com.yong.kakaopay.meetingroom.reservation.service.ReservationService;
import lombok.AllArgsConstructor;

@RestController
@AllArgsConstructor
public class ReservationController {
	private ReservationService reservationService;

	@PostMapping("/meeting-rooms/{id}/reservation")
	public void reserve(@PathVariable Integer id, @RequestBody Reservation.Dto dto) {
		reservationService.reserve(dto.asReservations(id));
	}

	@GetMapping("/reservation/{reservationId}")
	public ReservationDto reserve(@PathVariable Integer reservationId) {
		return reservationService.getReservationDto(reservationId);
	}

	@ExceptionHandler(ReservationException.class)
	@ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
	public String handler(ReservationException e) {
		return e.getMessage();
	}
}
