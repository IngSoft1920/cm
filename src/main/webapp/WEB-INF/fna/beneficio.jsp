<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<%@ taglib uri = "http://java.sun.com/jsp/jstl/core" prefix = "c" %>

<html >

	<head>
		<link rel="stylesheet" type="text/css" href="/css/main.css">
		
	</head>

	<body>
		<h1>Beneficios</h1>

			<c:forEach items="${treasureMap}" var="entry">

				   <b>Hotel:</b> ${entry.value.nombreHotel} <emsp><b>ID:</b> ${entry.key}<br>
				   <b> Dinero de las reservas:</b> <vd> ${entry.value.sumaReservas}€</vd><br>
				   <b> Suma de las facturas:</b> <vd> ${entry.value.sumaFacturas}€</vd><br>
				   <b> Dinero Invertido en Empleados:</b><rj> ${entry.value.sueldoEmpleados}€</rj><br>
				   <b> Dinero Invertido en Comida:</b><rj> ${entry.value.gastoComida}€</rj><br><br><br>

			</c:forEach>
	</body>

</html>