<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<%@ taglib uri = "http://java.sun.com/jsp/jstl/core" prefix = "c" %>


<html>
	<head>
		<link rel="stylesheet" type="text/css" href="/css/main.css">
	</head>
		
	<body>
		<h1> Visualizar Reservas </h1>


		<ul>
			<!-- reservasMap es un Map<Reserva,Hotel> -->
			<c:forEach var="entrada" items="${reservasMap}">
				<li>
					<p>
						<b>Importe:</b> ${entrada.key.importe}€ <br>
						<b>Tipo:</b> ${entrada.key.tipo} <br>
						<b>Fecha entrada:</b> ${entrada.key.fecha_entrada} <br>
						<b>Fecha salida:</b> ${entrada.key.fecha_salida} <br>
						<b>Hotel:</b> ${entrada.value.nombre} <br>
						<b>Dirección:</b> ${entrada.value.direccion}. ${entrada.value.ciudad}, ${entrada.value.pais}. <br>
					</p>
				</li>
			</c:forEach>	
		</ul>
	</body>

</html>

