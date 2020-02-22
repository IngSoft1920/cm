<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<%@ taglib uri = "http://java.sun.com/jsp/jstl/core" prefix = "c" %>


<html>
	<head>
		<link rel="stylesheet" type="text/css" href="/css/main.css">
	</head>
	
	<body>
		<h1> Resultados de búsqueda </h1>
	</body>

	<!-- seleccionHabitaciones es un Map< Hotel , Map<Tipo,Double> > -->
	<ul>
		<c:forEach var="entrada" items="${seleccionHabitaciones}">
			<li>
				<b>Hotel:</b> ${entrada.key.nombre} <br>
				<b>Dirección:</b> ${entrada.key.direccion}. ${entrada.key.ciudad}, ${entrada.key.pais}. <br>
				<!-- entrada.value es un Map<Tipo,Double> -->
				<c:forEach var="entradaP" items="${entrada.value}">
					<form method="POST">
						<input type="hidden" name="hotel_id" value="${entrada.key.id}">
						<input type="hidden" name="importe" value="${entradaP.value}">
						<input type="hidden" name="tipo" value="${entradaP.key}">

						<button type="submit">
							<b>Tipo:</b> ${entradaP.key} <br>
							<b>Precio:</b> ${entradaP.value}€/noche <br>
						</button>
					</form>
				</c:forEach>

				
			</li>
		</c:forEach>
	</ul>
		


	
</html>