-- MySQL dump 10.13  Distrib 8.0.15, for Win64 (x86_64)
--
-- Host: localhost    Database: bd-cm-reservas
-- ------------------------------------------------------
-- Server version	8.0.15

/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
 SET NAMES utf8 ;
/*!40103 SET @OLD_TIME_ZONE=@@TIME_ZONE */;
/*!40103 SET TIME_ZONE='+00:00' */;
/*!40014 SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0 */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;
/*!40111 SET @OLD_SQL_NOTES=@@SQL_NOTES, SQL_NOTES=0 */;

--
-- Table structure for table `cliente`
--

DROP TABLE IF EXISTS `cliente`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
 SET character_set_client = utf8mb4 ;
CREATE TABLE `cliente` (
  `id` bigint(20) NOT NULL,
  `apellido1` varchar(255) DEFAULT NULL,
  `apellido2` varchar(255) DEFAULT NULL,
  `dni` varchar(255) DEFAULT NULL,
  `email` varchar(255) DEFAULT NULL,
  `nacionalidad` varchar(255) DEFAULT NULL,
  `nombre` varchar(255) DEFAULT NULL,
  `pasaporte` varchar(255) DEFAULT NULL,
  `password` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `UK_jlcg5nhnauli1hu4ojldsedaw` (`dni`),
  UNIQUE KEY `UK_cmxo70m08n43599l3h0h07cc6` (`email`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `cliente`
--

LOCK TABLES `cliente` WRITE;
/*!40000 ALTER TABLE `cliente` DISABLE KEYS */;
INSERT INTO `cliente` VALUES (1,NULL,NULL,NULL,'pep21e@hotmail.com',NULL,NULL,NULL,'123432'),(4,'Perez','Pedreira',NULL,'pepe21e@hotmail.com',NULL,'Pepe',NULL,'123432'),(6,'Perez','Pedreira','092847218','pepe2132e@hotmail.com','British','Pepe',NULL,'123432'),(8,'Perez','Pedreira','092347218','pepe132e@hotmail.com','British','Pepe',NULL,'123432'),(9,'Perez','Pedreira','0923447218','pepe13243e@hotmail.com','British','Luis',NULL,'123432'),(10,'Monzon','Pedreira','09234447218','pepe1243e@hotmail.com','British','Luis',NULL,'123432'),(11,'Monzon','Pedreira','094447218','pepe43e@hotmail.com','British','Luis',NULL,'123432'),(12,'Monzon','Pedreira','0947218','mariano@hotmail.com','British','Mariano',NULL,'123432'),(14,'Monzon','Pedreira','09218','mariano21@hotmail.com','British','Mariano',NULL,'123432'),(15,'Monzon','Pedreira','094242344218','mariano2143@hotmail.com','British','Mariano',NULL,'123432');
/*!40000 ALTER TABLE `cliente` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `datos_precios`
--

DROP TABLE IF EXISTS `datos_precios`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
 SET character_set_client = utf8mb4 ;
CREATE TABLE `datos_precios` (
  `id` bigint(20) NOT NULL,
  `estado` int(11) DEFAULT NULL,
  `precio` float NOT NULL,
  `puntacion` int(11) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `datos_precios`
--

LOCK TABLES `datos_precios` WRITE;
/*!40000 ALTER TABLE `datos_precios` DISABLE KEYS */;
/*!40000 ALTER TABLE `datos_precios` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `empleado`
--

DROP TABLE IF EXISTS `empleado`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
 SET character_set_client = utf8mb4 ;
CREATE TABLE `empleado` (
  `id` bigint(20) NOT NULL,
  `apellido1` varchar(255) DEFAULT NULL,
  `apellido2` varchar(255) DEFAULT NULL,
  `email` varchar(255) DEFAULT NULL,
  `nombre` varchar(255) DEFAULT NULL,
  `nomina` float NOT NULL,
  `ocupacion` varchar(255) DEFAULT NULL,
  `telefono` int(11) DEFAULT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `UK_nihg474u49g6e8aolp4lwrj6e` (`email`),
  UNIQUE KEY `UK_oinctok6ayd7sbigtv9j1itj6` (`telefono`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `empleado`
--

LOCK TABLES `empleado` WRITE;
/*!40000 ALTER TABLE `empleado` DISABLE KEYS */;
/*!40000 ALTER TABLE `empleado` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `factura`
--

DROP TABLE IF EXISTS `factura`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
 SET character_set_client = utf8mb4 ;
CREATE TABLE `factura` (
  `id` bigint(20) NOT NULL,
  `descripcion` varchar(255) DEFAULT NULL,
  `fecha_factura` datetime(6) DEFAULT NULL,
  `importe` float NOT NULL,
  `pagado` bit(1) NOT NULL,
  `cliente_id` bigint(20) NOT NULL,
  PRIMARY KEY (`id`),
  KEY `FK2602efsrpmevi8yxg464stfn5` (`cliente_id`),
  CONSTRAINT `FK2602efsrpmevi8yxg464stfn5` FOREIGN KEY (`cliente_id`) REFERENCES `cliente` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `factura`
--

LOCK TABLES `factura` WRITE;
/*!40000 ALTER TABLE `factura` DISABLE KEYS */;
/*!40000 ALTER TABLE `factura` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `feedback`
--

DROP TABLE IF EXISTS `feedback`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
 SET character_set_client = utf8mb4 ;
CREATE TABLE `feedback` (
  `id` bigint(20) NOT NULL,
  `cabecera` varchar(255) DEFAULT NULL,
  `cuerpo` varchar(255) DEFAULT NULL,
  `nota` float NOT NULL,
  `cliente_id` bigint(20) NOT NULL,
  `hotel_id` bigint(20) NOT NULL,
  PRIMARY KEY (`id`),
  KEY `FK74fgwqxcx4kvrgc2q8cseukpw` (`cliente_id`),
  KEY `FKt9lta62m3mgg7eh81ed3umcj2` (`hotel_id`),
  CONSTRAINT `FK74fgwqxcx4kvrgc2q8cseukpw` FOREIGN KEY (`cliente_id`) REFERENCES `cliente` (`id`),
  CONSTRAINT `FKt9lta62m3mgg7eh81ed3umcj2` FOREIGN KEY (`hotel_id`) REFERENCES `hotel` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `feedback`
--

LOCK TABLES `feedback` WRITE;
/*!40000 ALTER TABLE `feedback` DISABLE KEYS */;
/*!40000 ALTER TABLE `feedback` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `habitacion`
--

DROP TABLE IF EXISTS `habitacion`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
 SET character_set_client = utf8mb4 ;
CREATE TABLE `habitacion` (
  `id` bigint(20) NOT NULL,
  `numero` int(11) NOT NULL,
  `hotel_id` bigint(20) NOT NULL,
  PRIMARY KEY (`id`),
  KEY `FKk3l154yy3cd6te71b3vc7wlp7` (`hotel_id`),
  CONSTRAINT `FKk3l154yy3cd6te71b3vc7wlp7` FOREIGN KEY (`hotel_id`) REFERENCES `hotel` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `habitacion`
--

LOCK TABLES `habitacion` WRITE;
/*!40000 ALTER TABLE `habitacion` DISABLE KEYS */;
/*!40000 ALTER TABLE `habitacion` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `hibernate_sequence`
--

DROP TABLE IF EXISTS `hibernate_sequence`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
 SET character_set_client = utf8mb4 ;
CREATE TABLE `hibernate_sequence` (
  `next_val` bigint(20) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `hibernate_sequence`
--

LOCK TABLES `hibernate_sequence` WRITE;
/*!40000 ALTER TABLE `hibernate_sequence` DISABLE KEYS */;
INSERT INTO `hibernate_sequence` VALUES (16),(16),(16),(16),(16);
/*!40000 ALTER TABLE `hibernate_sequence` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `hibernate_sequences`
--

DROP TABLE IF EXISTS `hibernate_sequences`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
 SET character_set_client = utf8mb4 ;
CREATE TABLE `hibernate_sequences` (
  `sequence_name` varchar(255) NOT NULL,
  `next_val` bigint(20) DEFAULT NULL,
  PRIMARY KEY (`sequence_name`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `hibernate_sequences`
--

LOCK TABLES `hibernate_sequences` WRITE;
/*!40000 ALTER TABLE `hibernate_sequences` DISABLE KEYS */;
INSERT INTO `hibernate_sequences` VALUES ('default',8);
/*!40000 ALTER TABLE `hibernate_sequences` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `hotel`
--

DROP TABLE IF EXISTS `hotel`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
 SET character_set_client = utf8mb4 ;
CREATE TABLE `hotel` (
  `id` bigint(20) NOT NULL,
  `ciudad` varchar(255) DEFAULT NULL,
  `continente` varchar(255) DEFAULT NULL,
  `direccion` varchar(255) DEFAULT NULL,
  `nombre` varchar(255) DEFAULT NULL,
  `pais` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `UK_k66yxxve7uteg35xecxhk2urd` (`direccion`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `hotel`
--

LOCK TABLES `hotel` WRITE;
/*!40000 ALTER TABLE `hotel` DISABLE KEYS */;
INSERT INTO `hotel` VALUES (1,'Cadiz','Europa',NULL,'Pepe Arriba','Espana'),(2,'Cadiz','Europa',NULL,'Pepe Playa','Espana'),(3,'Cadiz','Europa',NULL,'Barcelo Playa','Espana'),(4,'Malaga','Europa',NULL,'Barcelo Playa','Espana'),(5,'BArcelona','Europa',NULL,'Barcelo Playa','Espana'),(6,'BArcelona','Europa',NULL,'Barcelo Number one','Espana'),(7,'Madrid','Europa',NULL,'Madrid Number one','Espana'),(8,'Madrid','Europa',NULL,'Madrid Plaza','Espana');
/*!40000 ALTER TABLE `hotel` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `hotel_empleados`
--

DROP TABLE IF EXISTS `hotel_empleados`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
 SET character_set_client = utf8mb4 ;
CREATE TABLE `hotel_empleados` (
  `hotel_id` bigint(20) NOT NULL,
  `empleados_id` bigint(20) NOT NULL,
  KEY `FKlumpi08vmf0op9ykflx1acukb` (`empleados_id`),
  KEY `FKp91hy5x0kloalo0x7qpips4lu` (`hotel_id`),
  CONSTRAINT `FKlumpi08vmf0op9ykflx1acukb` FOREIGN KEY (`empleados_id`) REFERENCES `hotel` (`id`),
  CONSTRAINT `FKp91hy5x0kloalo0x7qpips4lu` FOREIGN KEY (`hotel_id`) REFERENCES `empleado` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `hotel_empleados`
--

LOCK TABLES `hotel_empleados` WRITE;
/*!40000 ALTER TABLE `hotel_empleados` DISABLE KEYS */;
/*!40000 ALTER TABLE `hotel_empleados` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `hotel_proveedor`
--

DROP TABLE IF EXISTS `hotel_proveedor`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
 SET character_set_client = utf8mb4 ;
CREATE TABLE `hotel_proveedor` (
  `hotel_id` bigint(20) NOT NULL,
  `proveedores_id` bigint(20) NOT NULL,
  KEY `FK413i8ywpygy3yvgr65ptp0cj3` (`proveedores_id`),
  KEY `FKnl8191o13n59wbgavmnlrrrf9` (`hotel_id`),
  CONSTRAINT `FK413i8ywpygy3yvgr65ptp0cj3` FOREIGN KEY (`proveedores_id`) REFERENCES `hotel` (`id`),
  CONSTRAINT `FKnl8191o13n59wbgavmnlrrrf9` FOREIGN KEY (`hotel_id`) REFERENCES `proveedor` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `hotel_proveedor`
--

LOCK TABLES `hotel_proveedor` WRITE;
/*!40000 ALTER TABLE `hotel_proveedor` DISABLE KEYS */;
/*!40000 ALTER TABLE `hotel_proveedor` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `peticiones`
--

DROP TABLE IF EXISTS `peticiones`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
 SET character_set_client = utf8mb4 ;
CREATE TABLE `peticiones` (
  `id` bigint(20) NOT NULL,
  `ciudad` varchar(255) DEFAULT NULL,
  `estado` int(11) DEFAULT NULL,
  `fecha_peticiones` datetime(6) DEFAULT NULL,
  `tipo_habitacion` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `peticiones`
--

LOCK TABLES `peticiones` WRITE;
/*!40000 ALTER TABLE `peticiones` DISABLE KEYS */;
/*!40000 ALTER TABLE `peticiones` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `precio`
--

DROP TABLE IF EXISTS `precio`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
 SET character_set_client = utf8mb4 ;
CREATE TABLE `precio` (
  `id` bigint(20) NOT NULL,
  `fecha` datetime(6) NOT NULL,
  `valor` varchar(255) DEFAULT NULL,
  `tipo_habitacion_id` bigint(20) NOT NULL,
  PRIMARY KEY (`id`),
  KEY `FK6e28katsdsweinfsil7y3s2o7` (`tipo_habitacion_id`),
  CONSTRAINT `FK6e28katsdsweinfsil7y3s2o7` FOREIGN KEY (`tipo_habitacion_id`) REFERENCES `tipo_habitacion` (`id`),
  CONSTRAINT `FKnhukde14pyumj9sjlyjtghqe2` FOREIGN KEY (`id`) REFERENCES `tipo_habitacion` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `precio`
--

LOCK TABLES `precio` WRITE;
/*!40000 ALTER TABLE `precio` DISABLE KEYS */;
/*!40000 ALTER TABLE `precio` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `proveedor`
--

DROP TABLE IF EXISTS `proveedor`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
 SET character_set_client = utf8mb4 ;
CREATE TABLE `proveedor` (
  `id` bigint(20) NOT NULL,
  `empresa` varchar(255) DEFAULT NULL,
  `producto` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `proveedor`
--

LOCK TABLES `proveedor` WRITE;
/*!40000 ALTER TABLE `proveedor` DISABLE KEYS */;
/*!40000 ALTER TABLE `proveedor` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `reserva`
--

DROP TABLE IF EXISTS `reserva`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
 SET character_set_client = utf8mb4 ;
CREATE TABLE `reserva` (
  `id` bigint(20) NOT NULL,
  `fecha_fin` date DEFAULT NULL,
  `fecha_inicio` date DEFAULT NULL,
  `importe` float NOT NULL,
  `regimen_comida` varchar(255) DEFAULT NULL,
  `cliente_id` bigint(20) NOT NULL,
  `hotel_id` bigint(20) NOT NULL,
  `tipo_habitacion_id` bigint(20) NOT NULL,
  PRIMARY KEY (`id`),
  KEY `FK7cg2jiyn5cf6f6elccvb6963k` (`cliente_id`),
  KEY `FKgve13jsoas3vq4xoo73lur8kv` (`hotel_id`),
  KEY `FK1yd4ab5vb7l6calqbyqrppaib` (`tipo_habitacion_id`),
  CONSTRAINT `FK1yd4ab5vb7l6calqbyqrppaib` FOREIGN KEY (`tipo_habitacion_id`) REFERENCES `tipo_habitacion` (`id`),
  CONSTRAINT `FK7cg2jiyn5cf6f6elccvb6963k` FOREIGN KEY (`cliente_id`) REFERENCES `cliente` (`id`),
  CONSTRAINT `FKdyeoguefgcauebh4ocufjapu6` FOREIGN KEY (`id`) REFERENCES `tipo_habitacion` (`id`),
  CONSTRAINT `FKgve13jsoas3vq4xoo73lur8kv` FOREIGN KEY (`hotel_id`) REFERENCES `hotel` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `reserva`
--

LOCK TABLES `reserva` WRITE;
/*!40000 ALTER TABLE `reserva` DISABLE KEYS */;
/*!40000 ALTER TABLE `reserva` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `tipo_habitacion`
--

DROP TABLE IF EXISTS `tipo_habitacion`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
 SET character_set_client = utf8mb4 ;
CREATE TABLE `tipo_habitacion` (
  `id` bigint(20) NOT NULL,
  `tipo` varchar(255) DEFAULT NULL,
  `total_habitaciones` int(11) NOT NULL,
  `hotel_id` bigint(20) NOT NULL,
  PRIMARY KEY (`id`),
  KEY `FKs2vh7wnovk8bur9j6u9bdebra` (`hotel_id`),
  CONSTRAINT `FKs2vh7wnovk8bur9j6u9bdebra` FOREIGN KEY (`hotel_id`) REFERENCES `hotel` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `tipo_habitacion`
--

LOCK TABLES `tipo_habitacion` WRITE;
/*!40000 ALTER TABLE `tipo_habitacion` DISABLE KEYS */;
/*!40000 ALTER TABLE `tipo_habitacion` ENABLE KEYS */;
UNLOCK TABLES;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2020-02-23 16:14:16
