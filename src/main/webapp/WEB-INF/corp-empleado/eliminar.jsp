<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<%@ taglib uri = "http://java.sun.com/jsp/jstl/core" prefix = "c" %>


<html>
	<head>
		<link rel="stylesheet" type="text/css" href="/css/main.css">
	</head>

	<body>
		<h1>Eliminar Empleado</h1>

		<c:forEach var="empleado" items="${empleados}">
			<form method="POST">
				<input type="hidden" name="empleado_id" value="${empleado.id}">

				<button type="submit">
					<b>Nombre:</b> ${empleado.nombre} <br>
					<b>Apellidos:</b> ${empleado.apellidos} <br>
					<b>Email:</b> ${empleado.email} <br>
					<b>Teléfono</b> ${empleado.telefono} <br>
					<b>Ocupación</b> ${empleado.ocupacion} <br>
				</button>
			</form>
		</c:forEach>
		
	</body>

</html>
