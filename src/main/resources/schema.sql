drop table meeting_room if exists;

create table meeting_room (
  meeting_room_id INT auto_increment primary key,
  meeting_room_name VARCHAR(20) NOT NULL
);

drop table reservation if exists;

create table reservation (
  reservation_id INT auto_increment primary key,
  meeting_room_id INT NOT NULL,
  start_datetime TIMESTAMP NOT NULL,
  end_datetime TIMESTAMP NOT NULL,
  user_name VARCHAR(10) NOT NULL,
  is_repeated BOOL NOT NULL
);