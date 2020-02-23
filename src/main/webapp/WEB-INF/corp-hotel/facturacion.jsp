<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<%@ taglib uri = "http://java.sun.com/jsp/jstl/core" prefix = "c" %>
<%@ page import="java.util.List" %>
<%@ page import="ingsoft1920.cm.bean.Factura" %>


<% 
    double total = ( (List<Factura>) request.getAttribute("facturas") )
                        .stream()
                        .mapToDouble(f->f.getImporte())
                        .sum();
%>

<html>
	<head>
		<link rel="stylesheet" type="text/css" href="/css/main.css">
	</head>

	<body>
        <h1>Facturación Hotel</h1>
        
		<ul>
			<c:forEach var="factura" items="${facturas}">
				<li>
					<b>Importe:</b> ${factura.importe}€ <br>
					<b>Descripción:</b> ${factura.descripcion} <br>
					<b>Fecha:</b> ${factura.fecha} <br>
				 </li>
				 <br>
			</c:forEach>
        </ul>
        <h2>Total: <%=total%>€</h2>
        
        <input type="submit" value="Volver" onclick="window.location='/hotel';" />
	</body>

</html>
