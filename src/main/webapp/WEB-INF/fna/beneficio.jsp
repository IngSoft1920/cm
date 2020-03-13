
<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<%@ taglib uri = "http://java.sun.com/jsp/jstl/core" prefix = "c" %>


<html>
	<head>
		<link rel="stylesheet" type="text/css" href="/css/main.css">
	</head>

	<body>
		<h1>Beneficios</h1>

			<c:forEach items="${treasureMap}" var="entry">
				   
				   <b>Hotel:</b> ${entry.value.nombreHotel}$  <b>ID:</b> ${entry.key}$<br>
				   <b> Dinero de las reservas:</b> ${entry.value.sumaReservas}$<br>
				   <b> Suma de las facturas:</b> ${entry.value.sumaFacturas}$<br>
				   <b> Dinero Invertido en Empleados:</b> ${entry.value.sueldoEmpleados}$<br>
				   <b> Dinero Invertido en Comida:</b> ${entry.value.gastoComida}$<b

			</c:forEach>
	</body>

</html>