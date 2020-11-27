DROP TABLE IF EXISTS item;
CREATE TABLE item (
	id IDENTITY PRIMARY KEY,
	name varchar (255) NOT NULL,
	quantity INT,
	is_selected BOOLEAN NOT NULL
);