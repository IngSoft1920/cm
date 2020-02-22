<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<%@ taglib uri = "http://java.sun.com/jsp/jstl/core" prefix = "c" %>


<html>
	<head>
		<link rel="stylesheet" type="text/css" href="/css/main.css">
	</head>
		
	<body>
		<h1> Visualizar Facturas </h1>


		<ul>
			<c:forEach var="factura" items="${facturas}">
				<li>
					<p>
						<b>Importe:</b> ${factura.importe}€ <br>
						<b>Descripción:</b> ${factura.descripcion} <br>
						<b>Fecha:</b> ${factura.fecha} <br>
						<b>Pagado:</b> ${factura.pagado} <br>
					</p>
				</li>
			</c:forEach>	
		</ul>
	</body>

</html>
