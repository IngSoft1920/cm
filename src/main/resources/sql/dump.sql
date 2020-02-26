/*hotel*/
INSERT INTO hotel (nombre, ubicacion) VALUES ('Hotel Emperador', 'Calle Atocha,21');
INSERT INTO hotel (nombre, ubicacion) VALUES ('Villa Rosa', 'Calle Euro,1');
INSERT INTO hotel (nombre, ubicacion) VALUES ('Punta Cana SS', 'Calle Salvador,7');
INSERT INTO hotel (nombre, ubicacion) VALUES ('The REST', 'Calle New York,11');
INSERT INTO hotel (nombre, ubicacion) VALUES ('KickBax', 'Calle Naipe, 21');
INSERT INTO hotel (nombre, ubicacion) VALUES ('Taza Rosada', 'Calle Felipe,9');
INSERT INTO hotel (nombre, ubicacion) VALUES ('Noche y Día', 'Calle Cañada,14');
INSERT INTO hotel (nombre, ubicacion) VALUES ('El emperador', 'Calle Nevada,17');
INSERT INTO hotel (nombre, ubicacion) VALUES ('Lockstar', 'Calle Langosta,22');
INSERT INTO hotel (nombre, ubicacion) VALUES ('Avatar: the experience', 'Calle Pet,1');
INSERT INTO hotel (nombre, ubicacion) VALUES ('Ajo y Agua', 'Calle de Monterrey,5');
INSERT INTO hotel (nombre, ubicacion) VALUES ('Devify', 'Calle de Northwestern,2');
INSERT INTO hotel (nombre, ubicacion) VALUES ('El coyote', 'Calle Correcaminos,67');
INSERT INTO hotel (nombre, ubicacion) VALUES ('Yamia', 'Calle Samurai,2');

DROP PROCEDURE IF EXISTS add_rooms;
/*Habitación*/
DELIMITER #
CREATE PROCEDURE add_rooms(IN max_habs INT,IN type VARCHAR(30))
	BEGIN		
		DECLARE id_hotel INT;
		DECLARE num_habs_agregar INT;
		
		SET id_hotel = (SELECT COUNT(*) FROM hotel);
	
		WHILE id_hotel > 0
			DO	
				SET num_habs_agregar = FLOOR( 1 + RAND() * max_habs ); /*Entero entre 1 y max_habs*/
				
				WHILE num_habs_agregar > 0
					DO
						INSERT INTO habitacion (tipo,hotel_id) VALUES (type,id_hotel);
						SET num_habs_agregar = num_habs_agregar - 1;
					END WHILE;
				
				SET id_hotel = id_hotel - 1;
			END WHILE;
		
	END #
DELIMITER ;
CALL add_rooms(3,'premium');
CALL add_rooms(10,'normal');



		
		
