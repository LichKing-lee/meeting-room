package com.yong.kakaopay.meetingroom.reservation.controller;

import static org.springframework.restdocs.mockmvc.MockMvcRestDocumentation.*;
import static org.springframework.restdocs.mockmvc.RestDocumentationRequestBuilders.*;
import static org.springframework.restdocs.payload.PayloadDocumentation.*;
import static org.springframework.restdocs.request.RequestDocumentation.*;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

import java.time.LocalDateTime;

import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.restdocs.payload.JsonFieldType;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.yong.kakaopay.meetingroom.reservation.domain.Reservation;
import com.yong.kakaopay.meetingroom.support.AbstractIntegTest;

public class ReservationControllerIntegTest extends AbstractIntegTest {
	@Autowired
	private ObjectMapper objectMapper;

	@Test
	public void 정상_예약_document() throws Exception {
		Reservation.Dto dto = new Reservation.Dto();
		dto.setStartDateTime(LocalDateTime.of(2019, 2, 22, 20, 0));
		dto.setEndDateTime(LocalDateTime.of(2019, 2, 22, 21, 0));
		dto.setUserName("changyong");

		String json = objectMapper.writeValueAsString(dto);

		mockMvc.perform(post("/meeting-rooms/{meetingRoomId}/reservation", 1)
			.contentType(MediaType.APPLICATION_JSON_UTF8)
			.content(json))
			.andDo(print())
			.andExpect(status().isOk())
			.andDo(document("{method-name}",
				pathParameters(
					parameterWithName("meetingRoomId").description("예약할 회의실 id")
				),

				requestFields(
					fieldWithPath("reservationId").type(JsonFieldType.NULL).description("null"),
					fieldWithPath("meetingRoomId").type(JsonFieldType.NULL).description("null"),
					fieldWithPath("startDateTime").type(JsonFieldType.STRING).description("예약 시작 시간"),
					fieldWithPath("endDateTime").type(JsonFieldType.STRING).description("예약 종료 시간"),
					fieldWithPath("userName").type(JsonFieldType.STRING).description("예약자 이름"),
					fieldWithPath("repeatCount").type(JsonFieldType.NUMBER).description("예약 반복 횟수 (default 0)")
				)
			));
	}

	@Test
	public void 예약_조회_document() throws Exception {
		mockMvc.perform(get("/reservation/{reservationId}", 1))
			.andDo(print())
			.andExpect(status().isOk())
			.andExpect(jsonPath("$.reservationId").value(1))
			.andExpect(jsonPath("$.meetingRoomId").value(1))
			.andExpect(jsonPath("$.startDateTime").value("2019-02-21T20:30:00"))
			.andExpect(jsonPath("$.endDateTime").value("2019-02-21T22:00:00"))
			.andExpect(jsonPath("$.userName").value("창용"))
			.andExpect(jsonPath("$.repeatCount").value(0))
			.andDo(document("{method-name}",
				pathParameters(
					parameterWithName("reservationId").description("조회할 예약 id")
				),

				responseFields(
					fieldWithPath("reservationId").type(JsonFieldType.NUMBER).description("예약 id"),
					fieldWithPath("meetingRoomId").type(JsonFieldType.NUMBER).description("예약한 회의실 id"),
					fieldWithPath("startDateTime").type(JsonFieldType.STRING).description("예약 시작 시간"),
					fieldWithPath("endDateTime").type(JsonFieldType.STRING).description("예약 종료 시간"),
					fieldWithPath("userName").type(JsonFieldType.STRING).description("예약자 이름"),
					fieldWithPath("repeatCount").type(JsonFieldType.NUMBER).description("반복횟수")
				)
			));
	}

	@Test
	public void 종료시간이_시작시간보다_빠르면_예약불가() throws Exception {
		Reservation.Dto dto = new Reservation.Dto();
		dto.setStartDateTime(LocalDateTime.of(2019, 2, 21, 20, 0));
		dto.setEndDateTime(LocalDateTime.of(2019, 2, 21, 19, 0));
		dto.setUserName("changyong");

		String json = objectMapper.writeValueAsString(dto);

		mockMvc.perform(post("/meeting-rooms/{meetingRoomId}/reservation", 1)
			.contentType(MediaType.APPLICATION_JSON_UTF8)
			.content(json))
			.andDo(print())
			.andExpect(status().is5xxServerError());
	}

	@Test
	public void 이미_예약된_회의실은_예약불가() throws Exception {
		Reservation.Dto dto = new Reservation.Dto();
		dto.setStartDateTime(LocalDateTime.of(2019, 2, 21, 20, 0));
		dto.setEndDateTime(LocalDateTime.of(2019, 2, 21, 21, 0));
		dto.setUserName("changyong");

		String json = objectMapper.writeValueAsString(dto);

		mockMvc.perform(post("/meeting-rooms/{meetingRoomId}/reservation", 1)
			.contentType(MediaType.APPLICATION_JSON_UTF8)
			.content(json))
			.andDo(print())
			.andExpect(status().is5xxServerError());
	}

	@Test
	public void 정상_반복_예약() throws Exception {
		Reservation.Dto dto = new Reservation.Dto();
		dto.setStartDateTime(LocalDateTime.of(2019, 3, 23, 20, 0));
		dto.setEndDateTime(LocalDateTime.of(2019, 3, 23, 21, 0));
		dto.setUserName("changyong");
		dto.setRepeatCount(5);

		String json = objectMapper.writeValueAsString(dto);

		mockMvc.perform(post("/meeting-rooms/{meetingRoomId}/reservation", 5)
			.contentType(MediaType.APPLICATION_JSON_UTF8)
			.content(json))
			.andDo(print())
			.andExpect(status().isOk());

		mockMvc.perform(get("/meeting-rooms/{meetingRoomId}", 5)
			.contentType(MediaType.APPLICATION_JSON_UTF8)
			.content(json))
			.andDo(print())
			.andExpect(status().isOk())
			.andExpect(jsonPath("$.reservations").isArray())
			.andExpect(jsonPath("$.reservations.length()").value(6));
	}

	@Test
	public void 이전예약_종료시간과_다음예약_시간시간이같으면_예약가능() throws Exception {
		Reservation.Dto dto = new Reservation.Dto();
		dto.setStartDateTime(LocalDateTime.of(2019, 2, 23, 20, 0));
		dto.setEndDateTime(LocalDateTime.of(2019, 2, 23, 21, 0));
		dto.setUserName("changyong");

		String json = objectMapper.writeValueAsString(dto);

		mockMvc.perform(post("/meeting-rooms/{meetingRoomId}/reservation", 3)
			.contentType(MediaType.APPLICATION_JSON_UTF8)
			.content(json))
			.andDo(print())
			.andExpect(status().isOk());

		Reservation.Dto dto2 = new Reservation.Dto();
		dto2.setStartDateTime(LocalDateTime.of(2019, 2, 23, 21, 0));
		dto2.setEndDateTime(LocalDateTime.of(2019, 2, 23, 22, 0));
		dto2.setUserName("changyong");

		String json2 = objectMapper.writeValueAsString(dto2);

		mockMvc.perform(post("/meeting-rooms/{meetingRoomId}/reservation", 3)
			.contentType(MediaType.APPLICATION_JSON_UTF8)
			.content(json2))
			.andDo(print())
			.andExpect(status().isOk());
	}

	@Test
	public void 시작시간과_종료시간이같으면_예약불가() throws Exception {
		Reservation.Dto dto = new Reservation.Dto();
		dto.setStartDateTime(LocalDateTime.of(2019, 2, 21, 20, 0));
		dto.setEndDateTime(LocalDateTime.of(2019, 2, 21, 20, 0));
		dto.setUserName("changyong");

		String json = objectMapper.writeValueAsString(dto);

		mockMvc.perform(post("/meeting-rooms/{meetingRoomId}/reservation", 1)
			.contentType(MediaType.APPLICATION_JSON_UTF8)
			.content(json))
			.andDo(print())
			.andExpect(status().is5xxServerError());
	}

	@Test
	public void 예약시간이_30분_단위가아니면_예약불가() throws Exception {
		Reservation.Dto dto = new Reservation.Dto();
		dto.setStartDateTime(LocalDateTime.of(2019, 2, 21, 20, 10));
		dto.setEndDateTime(LocalDateTime.of(2019, 2, 21, 20, 0));
		dto.setUserName("changyong");

		String json = objectMapper.writeValueAsString(dto);

		mockMvc.perform(post("/meeting-rooms/{meetingRoomId}/reservation", 1)
			.contentType(MediaType.APPLICATION_JSON_UTF8)
			.content(json))
			.andDo(print())
			.andExpect(status().is5xxServerError());
	}
}