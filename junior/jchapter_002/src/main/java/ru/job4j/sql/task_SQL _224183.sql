CREATE TABLE meeting (
	id serial PRIMARY KEY,
	name VARCHAR(100)
);

CREATE TABLE users (
	id serial PRIMARY KEY,
	name VARCHAR(100)
);

CREATE TABLE userstatus (
	id serial PRIMARY KEY,
	name VARCHAR(100)
);


CREATE TABLE meeting_users_userstatus (
	meeting_id int REFERENCES meeting(id),
	user_id int REFERENCES users(id),
	userstatus_id int REFERENCES userstatus(id),
	UNIQUE (meeting_id, user_id)
	);



INSERT INTO users (name) VALUES 
	('Ivan'),
	('Mike'),
	('Alex'),
	('Andrew');


INSERT INTO userstatus (name) VALUES 
	('approved'),
	('rejected');
	
INSERT INTO meeting (name) VALUES 
	('meet1'),
	('meet2'),
	('meet3');


INSERT INTO meeting_users_userstatus (meeting_id, user_id, userstatus_id) VALUES 
(1, 1, 1),
(1, 2, 2),
(2, 2, 1),
(2, 3, 1);


-- Список всех встреч и количество подтвердивших участников.

select m.name, sum(case when us.name = 'approved' then 1 else 0 end)
from meeting as m
left join meeting_users_userstatus as mus ON m.id = mus.meeting_id
left join userstatus as us ON mus.userstatus_id = us.id
GROUP BY (m.name)



-- все совещания, где не было ни одной заявки на посещения

select m.name from meeting as m
left join meeting_users_userstatus as mus ON m.id = mus.meeting_id
WHERE mus.meeting_id IS NULL;


