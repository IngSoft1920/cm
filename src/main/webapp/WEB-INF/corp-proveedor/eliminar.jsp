<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<%@ taglib uri = "http://java.sun.com/jsp/jstl/core" prefix = "c" %>

<html>
    <head>
        <link rel="stylesheet" type="text/css" href="/css/main.css">
    </head>

    <body>
        <h1>Eliminar Proveedor</h1>

        <c:forEach var="proveedor" items="${proveedores}">
			<form method="POST">
				<input type="hidden" name="proveedor_id" value="${proveedor.id}">

				<button type="submit">
					<b>Empresa:</b> ${proveedor.empresa} <br>
					<b>Producto:</b> ${proveedor.producto} <br>
				</button>
			</form>
		</c:forEach>

    </body>

</html>