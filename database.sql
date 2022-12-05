set names utf8;
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
    image varchar(64),
    primary key (id)
);

create table role(
	id int,
    name varchar(64),
    primary key (id)
);

create table user(
	id int,
    username varchar(64),
    password varchar(64),
    passwordConfirm varchar(64),
    primary key (id)
);

INSERT INTO role(id, name)
  VALUES (1, 'ROLE_USER'), (2, 'ROLE_ADMIN');