<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>

<html>
	<head>
		<link rel="stylesheet" type="text/css" href="/css/main.css">
	</head>

	<body>
		
		<h1> Hola ${cliente.nombre} </h1>

		¡quitar esto!... id: ${cliente.id}

   		<h2><a href="/home-client/main/reservar-buscar">Reservar</a></h2>
    	<h2><a href="/home-client/main/cancelar-reserva">Cancelar reserva</a></h2>
		<h2><a href="/home-client/main/visualizar-reservas">Ver mis reservas</a></h2>
		<h2><a href="/home-client/main/visualizar-facturas">Ver mis facturas</a></h2>
		<h2><a href="/home-client/main/feedback">Realizar valoración</a></h2>
	</body>
	
</html>