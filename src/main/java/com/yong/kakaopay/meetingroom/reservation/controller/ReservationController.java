package com.yong.kakaopay.meetingroom.reservation.controller;

import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.yong.kakaopay.meetingroom.reservation.domain.Reservation;
import com.yong.kakaopay.meetingroom.reservation.service.ReservationService;
import lombok.AllArgsConstructor;

@RestController
@RequestMapping("/meeting-rooms/{meetingRoomId}/reservation")
@AllArgsConstructor
public class ReservationController {
	private ReservationService reservationService;

	@PostMapping
	public void reserve(@PathVariable Integer meetingRoomId, @RequestBody Reservation.Request request) {
		request.setMeetingRoomId(meetingRoomId);
		reservationService.reserve(request);
	}
}
