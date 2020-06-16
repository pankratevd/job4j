CREATE TABLE post (
	id serial PRIMARY KEY NOT NULL,
	name TEXT,
	text TEXT,
	link TEXT UNIQUE,
	created TIMESTAMP
);