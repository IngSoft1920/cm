
<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<%@ taglib uri = "http://java.sun.com/jsp/jstl/core" prefix = "c" %>


<html>
	<head>
		<link rel="stylesheet" type="text/css" href="/css/main.css">
	</head>

	<body>
		<h1>Beneficios</h1>

			<c:forEach items="${treasureMap}" var="entry">
				   
				   <b>Hotel:</b> ${entry.value.nombreHotel} <emsp><b>ID:</b> ${entry.key}<br>
				   <b>Ingresos por estancia en habitaciones:</b><br>
				   <c:forEach items="${entry.value.sumaReservas}" var="serv">
				   	      <emsp>${serv.key}: <vd> ${serv.value}</vd><br>
				   </c:forEach>
				   <b>Ingresos por servicios ofrecidos:</b><br>
				   <c:forEach items="${entry.value.sumaFacturas}" var="fact">
				   	      <emsp>${fact.key}: <vd> ${fact.value}</vd><br>
				   </c:forEach>
				   <c:forEach items="${entry.value.sueldoEmpleados}" var="suel">
				   	      <b>Sueldos destinados a los empleados segun su rol:</b><br>
				   	      <emsp>${suel.key}: <rj> ${suel.value}</rj><br>
				   </c:forEach>
				   <b>Gasto en productos alimenticios: </b><rj> ${entry.value.gastoComida}</rj><br>
				   <b>TOTAL: </b> ${entry.value.total}
				   
				   


				   <br><br><br>

			</c:forEach>
	</body>

</html>