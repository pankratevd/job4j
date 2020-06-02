create table body (
	id serial PRIMARY KEY,
	name varchar(200));
	
create table engine (
	id serial PRIMARY KEY,
	name varchar(200));
	
create table transmition (
	id serial PRIMARY KEY,
	name varchar(200));

	
create table car (
	id serial PRIMARY KEY,
	name varchar(200) NOT NULL,
	body_id int REFERENCES body(id) NOT NULL,
	engine_id int REFERENCES engine(id) NOT NULL,
	transmition_id int REFERENCES transmition(id) NOT NULL
);

insert into body (name) VALUES
	('sedan'),
	('hatchback'),
	('coupe'),
	('wagon');
	
insert into engine (name) VALUES
	('mt'),
	('at'),
	('amt'),
	('dct');
	
insert into transmition (name) VALUES
	('combustion'),
	('internal combustion'),
	('external combustion'),
	('air-breathing combustion');





insert into car (name, body_id, engine_id, transmition_id) VALUES 
	('car 1', 1 , 3, 1),
	('car 2', 2 , 2, 3),
	('car 3', 1 , 1, 1),
	('car 4', 2 , 3, 3),
	('car 5', 2 , 3, 3),
	('car 6', 4 , 2, 2);



-- 1. Вывести список всех машин и все привязанные к ним детали.

SELECT c.name as name, b.name as body, e.name as engine, t.name as transmition FROM car as c
LEFT JOIN body as b ON b.id = c.body_id
LEFT JOIN engine as e ON e.id = c.engine_id
LEFT JOIN transmition as t ON t.id = c.transmition_id;

-- 2. Вывести отдельно детали, которые не используются в машине, кузова, двигатели, коробки передач.

-- engine
SELECT e.name FROM engine as e
LEFT JOIN car as c ON c.engine_id = e.id
WHERE c.name IS NULL


-- body
SELECT b.name FROM body as b
LEFT JOIN car as c ON c.body_id = b.id
WHERE c.name IS NULL


-- transmition
SELECT t.name FROM transmition as t
LEFT JOIN car as c ON c.transmition_id = t.id
WHERE c.name IS NULL