CREATE TABLE post (
	id serial PRIMARY KEY NOT NULL,
	text VARCHAR(510),
	text TEXT,
	link TEXT UNIQUE,
	created TIMESTAMP
);