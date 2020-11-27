CREATE TABLE item (
	id BIGSERIAL PRIMARY KEY,
	name varchar (255) NOT NULL,
	quantity INT,
	is_selected BOOLEAN NOT NULL
);