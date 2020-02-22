<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<%@ taglib uri = "http://java.sun.com/jsp/jstl/core" prefix = "c" %>

<html>
	<head>
		<link rel="stylesheet" type="text/css" href="/css/main.css">
	</head>
	
	<body>
		<h1> Realizar Valoración </h1>
	</body>

	<c:forEach var="hotel" items="${hoteles}">
        <form method="POST" action=/home-client/main/feedback/${hotel.id}>
            <button type="submit">
                <b>Nombre:</b> ${hotel.nombre} <br>
                <b>Ubicación:</b> ${hotel.direccion}. ${hotel.ciudad},${hotel.pais} <br>
            </button>
        </form>
    </c:forEach> 

</html>