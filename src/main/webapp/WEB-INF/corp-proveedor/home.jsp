<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<%@ taglib uri = "http://java.sun.com/jsp/jstl/core" prefix = "c" %>


<html>
	<head>
		<link rel="stylesheet" type="text/css" href="/css/main.css">
	</head>

	<body>
		<h1>Proveedor</h1>
		<h2><a href="/proveedor/anadir"> Añadir </a></h2>
		<h2><a href="/proveedor/eliminar"> Eliminar </a></h2>

		<ul>
			<c:forEach var="proveedor" items="${proveedores}">
				<li>
					<b>Empresa:</b> ${proveedor.empresa} <br>
					<b>Producto:</b> ${proveedor.producto} <br>
				 </li>
				 <br>
			</c:forEach>
		</ul>
		
	</body>

</html>