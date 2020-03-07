
INSERT INTO Hotel (id,nombre,continente,pais,ciudad,direccion,descripcion,estrellas) VALUES (1,'Hotel Debod','Europa','España','Madrid','Calle Gran vía,21','Una experiencia milenaria',4);
INSERT INTO Hotel (id,nombre,continente,pais,ciudad,direccion,descripcion,estrellas) VALUES (2,'Hotel Madroño','Europa','España','Madrid','Calle Sol,19','Como si estuvieses en la playa',3);

INSERT INTO Tipo_Habitacion (id,nombre_tipo) VALUES (1,'normal');
INSERT INTO Tipo_Habitacion (id,nombre_tipo) VALUES (2,'premium');
INSERT INTO Tipo_Habitacion (id,nombre_tipo) VALUES (3,'presidencial');

INSERT INTO Hotel_Tipo_Habitacion (hotel_id,tipo_hab_id,num_disponibles) VALUES (1,1,50); 
INSERT INTO Hotel_Tipo_Habitacion (hotel_id,tipo_hab_id,num_disponibles) VALUES (1,2,20);
INSERT INTO Hotel_Tipo_Habitacion (hotel_id,tipo_hab_id,num_disponibles) VALUES (1,3,5);
INSERT INTO Hotel_Tipo_Habitacion (hotel_id,tipo_hab_id,num_disponibles) VALUES (2,1,100); 

/*TODO Precio_Habitacion dump*/

INSERT INTO Categoria (id,nombre) VALUES (1,'ecologico');

INSERT INTO Hotel_Categoria (hotel_id,categoria_id) VALUES (1,1);

INSERT INTO Proveedor (id,empresa,CIF) VALUES (1,'Vegetales SA','12345A');

INSERT INTO Producto (id,nombre) VALUES (1,'tomates');

INSERT INTO Proveedor_Producto (producto_id,proveedor_id) VALUES (1,1);

INSERT INTO Hotel_Proveedor_Producto (hotel_id,proveedor_id,producto_id,precio,unidad_medida) VALUES (1,1,1,200,'por_tonelada');

/*YYYY-MM-DD*/
INSERT INTO Pedido (id,fecha,hotel_id) VALUES (1,'2020-03-21',1);

INSERT INTO Pedido_Producto (pedido_id,producto_id,cantidad) VALUES (1,1,30);

INSERT INTO Profesion(id,nombre) VALUES (1,'camarero');
INSERT INTO Profesion(id,nombre) VALUES (2,'chef');

INSERT INTO Servicio(id,nombre) VALUES (1,'restaurante');
INSERT INTO Servicio(id,nombre) VALUES (2,'piscina');

INSERT INTO Servicio_Profesion(servicio_id,profesion_id) VALUES (1,1);
INSERT INTO Servicio_Profesion(servicio_id,profesion_id) VALUES (1,2);

INSERT INTO Hotel_Servicio(hotel_id,servicio_id,precio,unidad_medida) VALUES (1,1,NULL,NULL);
INSERT INTO Hotel_Servicio(hotel_id,servicio_id,precio,unidad_medida) VALUES (1,2,25,'por_hora');
INSERT INTO Hotel_Servicio(hotel_id,servicio_id,precio,unidad_medida) VALUES (2,1,NULL,NULL);

INSERT INTO Empleado(id,nombre,apellidos,email,telefono,sueldo) VALUES (1,'Pepe','Rodríguez López','pepe@gmail.com','300300300','1200');

INSERT INTO Hotel_Empleado(empleado_id,hotel_id,fecha_contratacion) VALUES (1,1,'2020-10-30');

INSERT INTO Ausencia(id,motivo,fecha_inicio,fecha_fin,estado,empleado_id) VALUES (1,'enfermedad','2020-10-01','2020-10-10','pendiente',1);

INSERT INTO Cliente(id,nombre,DNI,email,`password`) VALUES (1,'Juan','321222F','juan@gmail.com','juanito123');

INSERT INTO Valoracion(id,cabecera,cuerpo,nota,cliente_id,hotel_id) VALUES (1,'Muy bueno','Genial experiencia, especialmente la comida',5,1,1);

INSERT INTO Reserva(id,fecha_entrada,fecha_salida,importe,regimen_comida,hotel_id,cliente_id,tipo_hab_id) VALUES (1,'2020-05-01','2020-05-10',300,'pension_completa',1,1,1);

INSERT INTO Cliente_Reserva(reserva_id,cliente_id) VALUES (1,1);

INSERT INTO Factura(id,importe,fecha,pagado,reserva_id,servicio_id) VALUES (1,50,'2020-05-04',false,1,1);
