
DROP DATABASE IF EXISTS cm;
CREATE DATABASE cm;
USE cm;

CREATE TABLE `Hotel`(
	`id` INT NOT NULL AUTO_INCREMENT,
	`nombre` VARCHAR(100) NOT NULL,
    `continente` VARCHAR(100) NOT NULL,
    `pais` VARCHAR(100) NOT NULL,
    `ciudad` VARCHAR(100) NOT NULL,
    `direccion` VARCHAR(100) NOT NULL,
    `estrellas` INT,
    `descripcion` VARCHAR(1000) DEFAULT '',
	PRIMARY KEY (`id`)
);

/*
	Parte tipo habitacion
*/

CREATE TABLE `Tipo_Habitacion` (
	`id` INT AUTO_INCREMENT,
	`nombre_tipo` VARCHAR(100) NOT NULL,
	PRIMARY KEY (`id`)
);

CREATE TABLE `Hotel_Tipo_Habitacion` (
	`hotel_id` INT NOT NULL,
	`tipo_hab_id` INT NOT NULL,
    `num_disponibles` INT NOT NULL,
	FOREIGN KEY (`hotel_id`) REFERENCES `Hotel` (`id`) ON UPDATE CASCADE ON DELETE CASCADE,
    FOREIGN KEY (`tipo_hab_id`) REFERENCES `Tipo_Habitacion` (`id`) ON UPDATE CASCADE ON DELETE CASCADE,
    PRIMARY KEY (`hotel_id`,`tipo_hab_id`)
);

CREATE TABLE `Precio_Habitacion` (
	`fecha` DATE NOT NULL,
	`hotel_id` INT NOT NULL,
	`tipo_hab_id` INT NOT NULL,
    `precio` INT NOT NULL,
    FOREIGN KEY (`hotel_id`,`tipo_hab_id`) REFERENCES `Hotel_Tipo_Habitacion` (`hotel_id`,`tipo_hab_id`) ON UPDATE CASCADE ON DELETE CASCADE,
    PRIMARY KEY (`hotel_id`,`tipo_hab_id`,`fecha`)
);

/*
	Categoria
*/

CREATE TABLE `Categoria`(
	`id` INT AUTO_INCREMENT,
	`nombre` VARCHAR(100) NOT NULL,
	PRIMARY KEY (`id`)
);

CREATE TABLE `Hotel_Categoria`(
	`hotel_id` INT NOT NULL,
	`categoria_id` INT NOT NULL,
	FOREIGN KEY (`hotel_id`) REFERENCES `Hotel` (`id`) ON UPDATE CASCADE ON DELETE CASCADE,
	FOREIGN KEY (`categoria_id`) REFERENCES `Categoria` (`id`) ON UPDATE CASCADE ON DELETE CASCADE,
	PRIMARY KEY (`hotel_id`,`categoria_id`)
);

/*
	Parte de proveedor
*/

CREATE TABLE `Proveedor` (
	`id` INT AUTO_INCREMENT,
	`empresa` VARCHAR(100) NOT NULL,
	`CIF` VARCHAR(10) NOT NULL,
	PRIMARY KEY (`id`)
);

CREATE TABLE `Producto` (
	`id` INT AUTO_INCREMENT,
	`nombre` VARCHAR(100) NOT NULL,
	PRIMARY KEY (`id`)
);

CREATE TABLE `Proveedor_Producto` (
	`proveedor_id` INT NOT NULL,
	`producto_id` INT NOT NULL,
	FOREIGN KEY (`proveedor_id`) REFERENCES `Proveedor` (`id`) ON UPDATE CASCADE ON DELETE CASCADE,
	FOREIGN KEY (`producto_id`) REFERENCES `Producto` (`id`) ON UPDATE CASCADE ON DELETE CASCADE,
	PRIMARY KEY(`proveedor_id`,`producto_id`)
);

CREATE TABLE `Hotel_Proveedor_Producto` (
	`hotel_id` INT NOT NULL,
	`proveedor_id` INT NOT NULL,
	`producto_id` INT,
	`precio` DOUBLE,
    `unidad_medida` VARCHAR(40),
    FOREIGN KEY (`hotel_id`) REFERENCES `Hotel` (`id`) ON UPDATE CASCADE ON DELETE CASCADE,
	FOREIGN KEY (`proveedor_id`) REFERENCES `Proveedor` (`id`) ON UPDATE CASCADE ON DELETE CASCADE,
	FOREIGN KEY (`producto_id`) REFERENCES `Producto` (`id`) ON UPDATE CASCADE ON DELETE CASCADE,
	PRIMARY KEY (`hotel_id`,`proveedor_id`,`producto_id`)
);

CREATE TABLE `Pedido` (
	`id` INT AUTO_INCREMENT,
    `fecha` DATE NOT NULL,
    `hotel_id` INT NOT NULL,
	PRIMARY KEY (`id`),
	FOREIGN KEY (`hotel_id`) REFERENCES `Hotel` (`id`) ON UPDATE CASCADE ON DELETE CASCADE
);

CREATE TABLE `Pedido_Producto` (
	`pedido_id` INT NOT NULL,
	`producto_id` INT NOT NULL,
	`cantidad` DOUBLE NOT NULL,
	FOREIGN KEY (`pedido_id`) REFERENCES `Pedido` (`id`) ON UPDATE CASCADE ON DELETE CASCADE,
	FOREIGN KEY (`producto_id`) REFERENCES `Producto` (`id`) ON UPDATE CASCADE ON DELETE CASCADE,
	PRIMARY KEY (`pedido_id`,`producto_id`)
);


/*
	Parte Servicio/Profesion
*/

CREATE TABLE `Profesion` (
	`id` INT AUTO_INCREMENT,
    `nombre` VARCHAR(100) NOT NULL,
    PRIMARY KEY (`id`)
);

CREATE TABLE `Servicio` (
	`id` INT  AUTO_INCREMENT,
    `nombre` VARCHAR(100) NOT NULL,
    PRIMARY KEY (`id`)
);

CREATE TABLE `Servicio_Profesion` (
	`profesion_id` INT,
    `servicio_id` INT,
    FOREIGN KEY (`profesion_id`) REFERENCES `Profesion` (`id`) ON UPDATE CASCADE ON DELETE CASCADE,
    FOREIGN KEY (`servicio_id`) REFERENCES `Servicio` (`id`) ON UPDATE CASCADE ON DELETE CASCADE,
    PRIMARY KEY (`profesion_id`,`servicio_id`)
);

CREATE TABLE `Hotel_Servicio` (
	`hotel_id` INT,
    `servicio_id` INT,
    `precio` INT,
    `unidad_medida` VARCHAR(40),
    FOREIGN KEY (`hotel_id`) REFERENCES `Hotel` (`id`) ON UPDATE CASCADE ON DELETE CASCADE,
    FOREIGN KEY (`servicio_id`) REFERENCES `Servicio` (`id`) ON UPDATE CASCADE ON DELETE CASCADE,
    PRIMARY KEY (`hotel_id`,`servicio_id`)
);

/*
	Parte empleado
*/

CREATE TABLE `Empleado` (
	`id` INT  AUTO_INCREMENT,
    `nombre` VARCHAR(100) NOT NULL,
    `apellidos` VARCHAR(100) NOT NULL,
    `email` VARCHAR(100) NOT NULL,
    `telefono` VARCHAR(15) NOT NULL,
    `sueldo` DOUBLE,
    PRIMARY KEY (`id`)
);

CREATE TABLE `Hotel_Empleado`(
	`empleado_id` INT,
    `hotel_id` INT,
    `fecha_contratacion` DATE,
    FOREIGN KEY (`hotel_id`) REFERENCES `Hotel` (`id`) ON UPDATE CASCADE ON DELETE CASCADE,
    FOREIGN KEY (`empleado_id`) REFERENCES `Empleado` (`id`) ON UPDATE CASCADE ON DELETE CASCADE,
    PRIMARY KEY (`empleado_id`,`hotel_id`)
);

CREATE TABLE `Ausencia`	(
	`id` INT AUTO_INCREMENT,
    `motivo` VARCHAR(100),
    `fecha_inicio` DATE,
    `fecha_fin` DATE,
    `estado` ENUM('denegada', 'aprobada', 'pendiente'),
    `empleado_id` INT,
    PRIMARY KEY (`id`),
    FOREIGN KEY (`empleado_id`) REFERENCES `Empleado` (`id`) ON UPDATE CASCADE ON DELETE CASCADE
);

/*
	Parte cliente
*/

CREATE TABLE `Cliente` (
	`id` INT  AUTO_INCREMENT,
    `nombre` VARCHAR(100) NOT NULL,
    `apellidos` VARCHAR(100),
    `DNI` VARCHAR(8),
    `nacionalidad` VARCHAR(20),    /*Está en país: España, México, etc*/
    `telefono` VARCHAR(15),
    `email` VARCHAR(100) NOT NULL,
    `password` VARCHAR(100) NOT NULL,
    PRIMARY KEY (id)
);

/*QUE PASA CON LA VALORACION SI SE ELIMINA UN CLIENTE*/
CREATE TABLE `Valoracion` (
	`id` INT  AUTO_INCREMENT,
    `cabecera` VARCHAR(100),
    `cuerpo` VARCHAR(500),
    `nota` INT,
    `cliente_id` INT,
    `hotel_id` INT NOT NULL,
    PRIMARY KEY (`id`),
    FOREIGN KEY (`hotel_id`) REFERENCES `Hotel` (`id`) ON UPDATE CASCADE ON DELETE CASCADE,
    FOREIGN KEY (`cliente_id`) REFERENCES `Cliente` (`id`) ON UPDATE CASCADE ON DELETE SET NULL
);

CREATE TABLE `Reserva`	(
	`id` INT AUTO_INCREMENT,
    `fecha_entrada` DATE NOT NULL,
    `fecha_salida` DATE NOT NULL,
    `importe` INT NOT NULL,
    `regimen_comida` ENUM('no_aplica', 'media_pension', 'pension_completa', 'todo_incluido') NOT NULL,
    `numero_acompanantes` INT NOT NULL,
    `hotel_id` INT NOT NULL,
    `cliente_id` INT NOT NULL,
    `tipo_hab_id` INT NOT NULL,
    PRIMARY KEY (`id`),
    FOREIGN KEY (`cliente_id`) REFERENCES `Cliente` (`id`) ON UPDATE CASCADE ON DELETE CASCADE,
    FOREIGN KEY (`tipo_hab_id`) REFERENCES `Tipo_Habitacion` (`id`) ON UPDATE CASCADE ON DELETE CASCADE,
    FOREIGN KEY (`hotel_id`) REFERENCES `Hotel` (`id`) ON UPDATE CASCADE ON DELETE CASCADE
);

/*PARA PODER ASOCIAR VARIOS CIENTES A UNA RESERVA*/
CREATE TABLE `Cliente_Reserva` (
	`reserva_id` INT NOT NULL,
    `cliente_id` INT NOT NULL,
    FOREIGN KEY (`cliente_id`) REFERENCES `Cliente` (`id`) ON UPDATE CASCADE ON DELETE CASCADE,
    FOREIGN KEY (`reserva_id`) REFERENCES `Reserva` (`id`) ON UPDATE CASCADE ON DELETE CASCADE,
	PRIMARY KEY (`reserva_id`,`cliente_id`)
);

/*FUTURO: No habria que asociarla tabien a un cliente*/
CREATE TABLE `Factura` (
	`id` INT AUTO_INCREMENT,
    `importe` DOUBLE NOT NULL,
    `fecha` DATE NOT NULL,
    `pagado` BOOLEAN DEFAULT FALSE,
    `cantidad_consumida` INT NOT NULL,
    `reserva_id` INT NOT NULL,
    `servicio_id` INT NOT NULL,
    PRIMARY KEY (`id`),
    FOREIGN KEY (`reserva_id`) REFERENCES `Reserva` (`id`) ON UPDATE CASCADE ON DELETE CASCADE,
    FOREIGN KEY (`servicio_id`) REFERENCES `Servicio` (`id`) ON UPDATE CASCADE ON DELETE CASCADE
);



/*
---------------------------------------------------  PARTE PARA RM  ---------------------------------------------------
*/
CREATE TABLE `Peticion` (
    `id`INT AUTO_INCREMENT,
    `ciudad` VARCHAR(100) NOT NULL,
    `fecha` DATE NOT NULL,
    `tipo_hab_id` INT NOT NULL,
    `estado` BOOLEAN NOT NULL,
    PRIMARY KEY (`id`),
    FOREIGN KEY (`tipo_hab_id`) REFERENCES `Hotel_Tipo_Habitacion` (`tipo_hab_id`) ON UPDATE CASCADE ON DELETE CASCADE
);

CREATE TABLE `Datos_Precio` (
    `id` INT AUTO_INCREMENT,
    `precio` DOUBLE NOT NULL,
    `puntuacion` DOUBLE NOT NULL,
    `estado` BOOLEAN NOT NULL,
    `peticion_id` INT NOT NULL,
    PRIMARY KEY (`id`),
    FOREIGN KEY (`peticion_id`) REFERENCES `Peticion` (`id`) ON UPDATE CASCADE ON DELETE CASCADE
);