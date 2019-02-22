package com.yong.kakaopay.meetingroom.reservation.controller;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

import java.time.LocalDateTime;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.yong.kakaopay.meetingroom.reservation.domain.Reservation;

@RunWith(SpringRunner.class)
@SpringBootTest
@AutoConfigureMockMvc
public class ReservationControllerIntegTest {
	@Autowired
	private MockMvc mockMvc;
	@Autowired
	private ObjectMapper objectMapper;

	@Test
	public void 정상_예약() throws Exception {
		Reservation.Request request = new Reservation.Request();
		request.setStartDateTime(LocalDateTime.of(2019, 2, 22, 20, 0));
		request.setEndDateTime(LocalDateTime.of(2019, 2, 22, 21, 0));
		request.setUserName("changyong");

		String json = objectMapper.writeValueAsString(request);

		mockMvc.perform(post("/meeting-rooms/{meetingRoomId}/reservation", 1)
			.contentType(MediaType.APPLICATION_JSON_UTF8)
			.content(json))
			.andDo(print())
			.andExpect(status().isOk());
	}

	@Test
	public void 종료시간이_시작시간보다_빠름() throws Exception {
		Reservation.Request request = new Reservation.Request();
		request.setStartDateTime(LocalDateTime.of(2019, 2, 21, 20, 0));
		request.setEndDateTime(LocalDateTime.of(2019, 2, 21, 19, 0));
		request.setUserName("changyong");

		String json = objectMapper.writeValueAsString(request);

		mockMvc.perform(post("/meeting-rooms/{meetingRoomId}/reservation", 1)
			.contentType(MediaType.APPLICATION_JSON_UTF8)
			.content(json))
			.andDo(print())
			.andExpect(status().is5xxServerError());
	}

	@Test
	public void 이미_예약된_회의실() throws Exception {
		Reservation.Request request = new Reservation.Request();
		request.setStartDateTime(LocalDateTime.of(2019, 2, 21, 20, 0));
		request.setEndDateTime(LocalDateTime.of(2019, 2, 21, 21, 0));
		request.setUserName("changyong");

		String json = objectMapper.writeValueAsString(request);

		mockMvc.perform(post("/meeting-rooms/{meetingRoomId}/reservation", 1)
			.contentType(MediaType.APPLICATION_JSON_UTF8)
			.content(json))
			.andDo(print())
			.andExpect(status().is5xxServerError());
	}

	@Test
	public void 정상_반복_예약() throws Exception {
		Reservation.Request request = new Reservation.Request();
		request.setStartDateTime(LocalDateTime.of(2019, 2, 23, 20, 0));
		request.setEndDateTime(LocalDateTime.of(2019, 2, 23, 21, 0));
		request.setUserName("changyong");
		request.setRepeatCount(5);

		String json = objectMapper.writeValueAsString(request);

		mockMvc.perform(post("/meeting-rooms/{meetingRoomId}/reservation", 3)
			.contentType(MediaType.APPLICATION_JSON_UTF8)
			.content(json))
			.andDo(print())
			.andExpect(status().isOk());

		mockMvc.perform(get("/meeting-rooms/{meetingRoomId}", 3)
			.contentType(MediaType.APPLICATION_JSON_UTF8)
			.content(json))
			.andDo(print())
			.andExpect(status().isOk())
			.andExpect(jsonPath("$.reservations").isArray())
			.andExpect(jsonPath("$.reservations.length()").value(6));
	}
}