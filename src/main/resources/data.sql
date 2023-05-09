



CREATE TABLE tutorials (
    id INT AUTO_INCREMENT PRIMARY KEY,
    title VARCHAR,
	description VARCHAR,
	published BOOLEAN
);

INSERT INTO tutorials (title, description, published) VALUES ('title1', 'description1', false ), ('title2', 'description2', true );


