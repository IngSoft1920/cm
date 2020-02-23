<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<%@ taglib uri = "http://java.sun.com/jsp/jstl/core" prefix = "c" %>


<html>
	<head>
		<link rel="stylesheet" type="text/css" href="/css/main.css">
	</head>

	<body>
		<h1>Empleado</h1>
		<h2><a href="/empleado/anadir"> Añadir </a></h2>
		<h2><a href="/empleado/eliminar"> Eliminar </a></h2>

		<ul>
			<c:forEach var="empleado" items="${empleados}">
				<li>
					<b>Nombre:</b> ${empleado.nombre} <br>
					<b>Apellidos:</b> ${empleado.apellidos} <br>
					<b>Email:</b> ${empleado.email} <br>
					<b>Teléfono</b> ${empleado.telefono} <br>
					<b>Ocupación</b> ${empleado.ocupacion} <br>
				 </li>
				 <br>
			</c:forEach>
		</ul>
		
	</body>

</html>
