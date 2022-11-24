CREATE DATABASE web;
USE web;
SET GLOBAL time_zone = '+7:00';

create table record(
	id binary(16),
    title varchar(64) not null,
    address varchar(64) not null,
    berth tinyint not null,
    rent int not null,
    description text,
    image varchar(1024) default "/tmp/default.jpg",
    primary key (id)
);