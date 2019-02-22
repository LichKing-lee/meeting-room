insert into meeting_room(meeting_room_name) values('회의실1');
insert into meeting_room(meeting_room_name) values('회의실2');
insert into meeting_room(meeting_room_name) values('회의실3');
insert into meeting_room(meeting_room_name) values('회의실4');

INSERT INTO reservation(meeting_room_id, start_datetime, end_datetime, user_name, is_repeated)
VALUES(1, '2019-02-21 20:30:00', '2019-02-21 22:00:00', '창용', false);