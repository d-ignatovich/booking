set names utf8mb3;
create database web;
use web;
set global time_zone = '+7:00';

create table user(
	id int not null auto_increment,
    email varchar(64),
    username varchar(64),
    phone varchar(64),
    password varchar(64),
    primary key (id)
);

create table record(
	id binary(16),
    title varchar(64) not null,
    address varchar(64) not null,
    berth tinyint not null,
    rent int not null,
    description text,
    image varchar(64),
    primary key (id),
    user_id int,
    foreign key (user_id) references user (id)
);

create table booking(
	id binary(16),
    title varchar(64) not null,
    address varchar(64) not null,
    berth tinyint not null,
    rent int not null,
    description text,
    image varchar(64),
    primary key (id),
    user_id int,
    foreign key (user_id) references user (id)
    -- может быть есть смысл сделать через связь booking и record, а не ввиде самостоятельных таблиц
    -- foreign key (id) references record (id)
);

create table role(
	id int not null auto_increment,
    name varchar(64),
    primary key (id)
);

create table user_roles (
    user_id int not null,
    roles_id int not null
);

INSERT INTO role(id, name)
  VALUES (1, 'ROLE_USER'), (2, 'ROLE_ADMIN');
  
select * from record;
select * from user;
select * from role;
select * from user_roles;
select * from booking;
