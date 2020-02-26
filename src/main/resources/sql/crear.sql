DROP DATABASE hulio;
CREATE DATABASE hulio;
USE hulio;

CREATE TABLE hotel(
	id INT AUTO_INCREMENT,
	nombre VARCHAR(40),
	ubicacion VARCHAR(80),
	
	PRIMARY KEY(id)
);

CREATE TABLE habitacion(
	id INT AUTO_INCREMENT,
	precio DECIMAL(4,2), /*Máximo valor posible: 9999.99*/
	tipo ENUM('normal','premium'),
	hotel_id INT,
	ocupada BOOLEAN DEFAULT false,
	
	PRIMARY KEY(id),
	FOREIGN KEY (hotel_id) REFERENCES hotel(id) 
				 		   ON UPDATE RESTRICT
					 	   ON DELETE CASCADE
);

