package com.yong.kakaopay.meetingroom.meetingroom.controller;

import static org.springframework.restdocs.mockmvc.MockMvcRestDocumentation.*;
import static org.springframework.restdocs.mockmvc.RestDocumentationRequestBuilders.*;
import static org.springframework.restdocs.payload.PayloadDocumentation.*;
import static org.springframework.restdocs.request.RequestDocumentation.*;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

import org.junit.Test;
import org.springframework.restdocs.payload.JsonFieldType;

import com.yong.kakaopay.meetingroom.support.AbstractIntegTest;

public class MeetingRoomControllerIntegTest extends AbstractIntegTest {
	@Test
	public void 회의실_전체_조회_document() throws Exception {
		mockMvc.perform(get("/meeting-rooms"))
			.andDo(print())
			.andExpect(status().isOk())
			.andExpect(jsonPath("$.meetingRooms").isArray())
			.andExpect(jsonPath("$.meetingRooms[0].id").value(1))
			.andExpect(jsonPath("$.meetingRooms[0].name").value("회의실1"))
			.andExpect(jsonPath("$.meetingRooms[0].reservations").isArray())
			.andDo(document("{method-name}",
				responseFields(
					fieldWithPath("meetingRooms").type(JsonFieldType.ARRAY).description("회의실 목록 array"),
					fieldWithPath("meetingRooms[].id").type(JsonFieldType.NUMBER).description("회의실 id"),
					fieldWithPath("meetingRooms[].name").type(JsonFieldType.STRING).description("회의실 이름"),
					fieldWithPath("meetingRooms[].reservations").type(JsonFieldType.ARRAY).description("회의실 예약 목록 array"),
					fieldWithPath("meetingRooms[].reservations[].id").type(JsonFieldType.NUMBER).description("예약 id"),
					fieldWithPath("meetingRooms[].reservations[].meetingRoom").type(JsonFieldType.OBJECT).description("회의실 object"),
					fieldWithPath("meetingRooms[].reservations[].meetingRoom.id").type(JsonFieldType.NUMBER).description("회의실 id"),
					fieldWithPath("meetingRooms[].reservations[].meetingRoom.name").type(JsonFieldType.NULL).description("null"),
					fieldWithPath("meetingRooms[].reservations[].meetingRoom.reservations").type(JsonFieldType.NULL).description("null"),
					fieldWithPath("meetingRooms[].reservations[].startDateTime").type(JsonFieldType.STRING).description("예약 시작 시간"),
					fieldWithPath("meetingRooms[].reservations[].endDateTime").type(JsonFieldType.STRING).description("예약 종료 시간"),
					fieldWithPath("meetingRooms[].reservations[].userName").type(JsonFieldType.STRING).description("예약자 이름"),
					fieldWithPath("meetingRooms[].reservations[].repeated").type(JsonFieldType.BOOLEAN).description("반복 예약 여부")
				)
			));
	}

	@Test
	public void 회의실_한건_조회_document() throws Exception {
		mockMvc.perform(get("/meeting-rooms/{meetingRoomId}", 1))
			.andDo(print())
			.andExpect(status().isOk())
			.andExpect(jsonPath("$.id").value(1))
			.andExpect(jsonPath("$.name").value("회의실1"))
			.andExpect(jsonPath("$.reservations").isArray())
			.andDo(document("{method-name}",
				pathParameters(
					parameterWithName("meetingRoomId").description("조회할 회의실 id")
				),

				responseFields(
					fieldWithPath("id").type(JsonFieldType.NUMBER).description("회의실 id"),
					fieldWithPath("name").type(JsonFieldType.STRING).description("회의실 이름"),
					fieldWithPath("reservations").type(JsonFieldType.ARRAY).description("회의실 예약 목록 array"),
					fieldWithPath("reservations[].id").type(JsonFieldType.NUMBER).description("예약 id"),
					fieldWithPath("reservations[].meetingRoom").type(JsonFieldType.OBJECT).description("회의실 object"),
					fieldWithPath("reservations[].meetingRoom.id").type(JsonFieldType.NUMBER).description("회의실 id"),
					fieldWithPath("reservations[].meetingRoom.name").type(JsonFieldType.NULL).description("null"),
					fieldWithPath("reservations[].meetingRoom.reservations").type(JsonFieldType.NULL).description("null"),
					fieldWithPath("reservations[].startDateTime").type(JsonFieldType.STRING).description("예약 시작 시간"),
					fieldWithPath("reservations[].endDateTime").type(JsonFieldType.STRING).description("예약 종료 시간"),
					fieldWithPath("reservations[].userName").type(JsonFieldType.STRING).description("예약자 이름"),
					fieldWithPath("reservations[].repeated").type(JsonFieldType.BOOLEAN).description("반복 예약 여부")
				)
			));
	}
}