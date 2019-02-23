package com.yong.kakaopay.meetingroom.meetingroom.mapper;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.List;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import com.yong.kakaopay.meetingroom.meetingroom.domain.MeetingRoom;

@RunWith(SpringRunner.class)
@SpringBootTest
public class MeetingRoomMapperTest {
	@Autowired
	private MeetingRoomMapper meetingRoomMapper;

	@Test
	public void selectAll() {
		List<MeetingRoom> meetingRooms = meetingRoomMapper.selectAll();

		assertThat(meetingRooms.size()).isEqualTo(5);
	}

	@Test
	public void selectOne() {
		MeetingRoom meetingRoom = meetingRoomMapper.selectOne(3);

		MeetingRoom actual = new MeetingRoom(3, "회의실3");

		assertThat(meetingRoom).isEqualTo(actual);
	}
}