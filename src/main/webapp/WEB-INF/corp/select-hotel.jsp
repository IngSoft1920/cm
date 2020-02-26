<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<%@ taglib uri = "http://java.sun.com/jsp/jstl/core" prefix = "c" %>


<html>
	<head>
		<link rel="stylesheet" type="text/css" href="/css/main.css">
	</head>

	<body>
		<h1>Selecciona Hotel</h1>
		
		<c:forEach var="hotel" items="${hoteles}">

			<form method="POST">
				<input type="hidden" name="hotel_id" value="${hotel.id}">

				<button type="submit">
					<b>Nombre:</b> ${hotel.nombre} <br>
					<b>Ubicación:</b> ${hotel.direccion}.${hotel.ciudad},${hotel.pais}
				</button>
			</form>

		</c:forEach>
		<input type="submit" value="Volver" onclick="window.location='/home-corp';" />
	</body>

</html>
