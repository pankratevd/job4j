CREATE DATABASE tracker
    WITH 
    OWNER = postgres
    ENCODING = 'UTF8'
    LC_COLLATE = 'Russian_Russia.1251'
    LC_CTYPE = 'Russian_Russia.1251'
    TABLESPACE = pg_default
    CONNECTION LIMIT = -1; 


\c tracker

CREATE TABLE rule (
	id serial PRIMARY KEY,
	name character varying(255) NOT NULL
);


CREATE TABLE role (
	id serial PRIMARY KEY,
	name character varying(255) NOT NULL
);

CREATE TABLE role_has_rule (
	role_id int NOT NULL,
	rule_id int NOT NULL
);

CREATE TABLE users (
	id serial PRIMARY KEY,
	first_name character varying(255),
	last_name character varying(255),
	login character varying(50) UNIQUE NOT NULL,
	password character varying(255) NOT NULL,
	registered_date TIMESTAMP,
	role_id int REFERENCES role(id)
);

CREATE TABLE category (
	id serial PRIMARY KEY,
	name character varying(255) NOT NULL
);

CREATE TABLE state (
	id serial PRIMARY KEY,
	name character varying(255) NOT NULL
);

CREATE TABLE task (
	id serial PRIMARY KEY,
	number character varying(100) UNIQUE NOT NULL,
	subject character varying(255),
	description TEXT,
	created TIMESTAMP NOT NULL,
	closed TIMESTAMP,
	state_id int REFERENCES state(id) NOT NULL,
	category_id int REFERENCES category(id),
	author_id int REFERENCES users(id) NOT NULL,
	responsible_id int REFERENCES users(id)
	);

CREATE TABLE comment (
	id serial PRIMARY KEY,
	description TEXT,
	task_id int REFERENCES task(id) NOT NULL,
	user_id int REFERENCES users(id) NOT NULL
);

CREATE TABLE attach (
	id serial PRIMARY KEY,
	description TEXT,
	file bytea,
	task_id int REFERENCES task(id)
);


INSERT INTO role (name) VALUES 
	('admin'),
	('user'),
	('auditor');

INSERT INTO rule (name) VALUES 
	('full'),
	('read-only'),
	('create'),
	('comment');

INSERT INTO role_has_rule (role_id, rule_id) VALUES 
	(1,1),
	(2,4),
	(2,5),
	(3,3);

INSERT INTO users (first_name, last_name, login, password, role_id, registered_date) VALUES 
	('Ivan', 'Ivanov', 'ivanov_i', 'password', 1, '2019-12-01 12:03'),
	('Sergey', 'Sergeev', 'sergeev_s', 'password', 2, '2020-01-21 11:00'),
	('Petr', 'Petrov', 'petrov_p', 'password', 3, '2020-02-10 14:00');
	
INSERT INTO category (name) VALUES 
	('software'),
	('printer'),
	('server');

INSERT INTO state (name) VALUES 
	('new'),
	('inProgress'),
	('closed');
	
INSERT INTO task (number, subject, description, created, closed, state_id, category_id, author_id, responsible_id) VALUES 
	('A202001154', 'subj task 1', 'desc task 1', '2020-01-15 12:01:02', '2020-01-16 11:05:01', 3, 1, 2, 1),
	('A202001155', 'subj task 2', 'desc task 2', '2020-01-20 14:54:00', NULL,  1, 2, 1, 3);

INSERT INTO comment (description, task_id, user_id) VALUES 
	('desc 1 to task 1', 1, 1),
	('desc 2 to task 1', 1, 2),
	('desc 1 to task 2', 2, 3);

