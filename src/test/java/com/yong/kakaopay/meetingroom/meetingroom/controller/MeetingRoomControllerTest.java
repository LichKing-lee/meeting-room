package com.yong.kakaopay.meetingroom.meetingroom.controller;

import static org.mockito.BDDMockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

import java.util.List;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

import com.yong.kakaopay.meetingroom.meetingroom.domain.MeetingRoom;
import com.yong.kakaopay.meetingroom.meetingroom.mapper.MeetingRoomMapper;
import com.yong.kakaopay.meetingroom.meetingroom.service.MeetingRoomService;

@RunWith(SpringRunner.class)
@WebMvcTest(controllers = MeetingRoomController.class)
public class MeetingRoomControllerTest {
	@Autowired
	private MockMvc mockMvc;
	@MockBean
	private MeetingRoomService meetingRoomService;
	@MockBean
	private MeetingRoomMapper meetingRoomMapper;

	@Test
	public void 회의실목록_조회() throws Exception {
		given(meetingRoomService.getAll()).willReturn(List.of(new MeetingRoom(), new MeetingRoom()));

		mockMvc.perform(get("/meeting-rooms"))
			.andDo(print())
			.andExpect(status().isOk())
			.andExpect(jsonPath("$.meetingRooms").isArray())
			.andExpect(jsonPath("$.meetingRooms.length()").value(2));
	}

	@Test
	public void 회의실하나_조회() throws Exception {
		Integer id = 1;

		given(meetingRoomService.getMeetingRoomWithReservation(id)).willReturn(newMockMeetingRoom(id));

		mockMvc.perform(get("/meeting-rooms/{id}", id))
			.andDo(print())
			.andExpect(status().isOk())
			.andExpect(jsonPath("$.id").value(id));
	}

	private MeetingRoom newMockMeetingRoom(Integer id) {
		MeetingRoom room = new MeetingRoom();
		room.setId(id);

		return room;
	}
}