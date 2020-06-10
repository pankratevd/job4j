
create TABLE rule (
	id serial PRIMARY KEY,
	name character varying(255) NOT NULL
);


create TABLE role (
	id serial PRIMARY KEY,
	name character varying(255) NOT NULL
);

create TABLE role_has_rule (
	role_id int NOT NULL,
	rule_id int NOT NULL
);

create TABLE users (
	id serial PRIMARY KEY,
	first_name character varying(255),
	last_name character varying(255),
	login character varying(50) UNIQUE NOT NULL,
	password character varying(255) NOT NULL,
	registered_date TIMESTAMP,
	role_id int REFERENCES role(id)
);

create TABLE category (
	id serial PRIMARY KEY,
	name character varying(255) NOT NULL
);

create TABLE state (
	id serial PRIMARY KEY,
	name character varying(255) NOT NULL
);

create TABLE task (
	id serial PRIMARY KEY,
	number character varying(100) UNIQUE,
	subject character varying(255),
	description TEXT,
	created TIMESTAMP,
	closed TIMESTAMP,
	state_id int REFERENCES state(id),
	category_id int REFERENCES category(id),
	author_id int REFERENCES users(id),
	responsible_id int REFERENCES users(id)
	);

create TABLE comment (
	id serial PRIMARY KEY,
	description TEXT,
	task_id int REFERENCES task(id) NOT NULL,
	user_id int REFERENCES users(id) NOT NULL
);

create TABLE attach (
	id serial PRIMARY KEY,
	description TEXT,
	file bytea,
	task_id int REFERENCES task(id)
);