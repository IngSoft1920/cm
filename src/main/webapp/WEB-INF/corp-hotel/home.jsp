<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<%@ taglib uri = "http://java.sun.com/jsp/jstl/core" prefix = "c" %>

<html>
	<head>
		<link rel="stylesheet" type="text/css" href="/css/main.css">
	</head>

	<body>
		<h1>Administración de hoteles</h1>

        <h2><a href="/hotel/anadir"> Añadir hotel </a></h2>
        <h2><a href="/home-corp/select-hotel/editar"> Editar hotel </a></h2>
        <h2><a href="/home-corp/select-hotel/eliminar"> Eliminar hotel </a></h2>
        <h2><a href="/home-corp/select-hotel/facturacion"> Facturación </a></h2>		
		<ul>
			<c:forEach var="hotel" items="${hoteles}">
				<li>
					<b>Nombre:</b> ${hotel.nombre} <br>
					<b>Ubicación:</b> ${hotel.direccion}.${hotel.ciudad},${hotel.pais}
				 </li>
				 <br>
			</c:forEach>
		</ul>
		<input type="submit" value="Volver" onclick="window.location='/home-corp';" />
	</body>

</html>
