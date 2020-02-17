<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>

<%@page import="java.util.List"%> 
<%@page import="ingsoft1920.bean.Hotel"%>

<%@ taglib uri = "http://java.sun.com/jsp/jstl/core" prefix = "c" %>



<html>
    
     <body>
		<style>
			a { text-decoration: none; }
		</style>
	</body>

    <h1>Realizar reserva:</h1>

    <%-- <%  List<Hotel> hoteles = (List<Hotel>) request.getAttribute("hoteles"); %>
    <% for(int i=0;i<hoteles.size();i++){ %>

            <b>Nombre:</b> <%= hoteles.get(i).getNombre() %> <br>
            <b>Ubicación:</b> <%= hoteles.get(i).getUbicacion() %> <br><br>
    <%}%> --%>

    <c:forEach var="hotel" items="${hoteles}">
        <form action="/home-client/realizar-reserva" method="POST">
            <!--Esto es un poco chapuza-->
            <input type="hidden" name="nombre" value="${hotel.nombre}" />
            <input type="hidden" name="ubicacion" value="${hotel.ubicacion}" />

            <button type="submit">
                <b>Nombre:</b> ${hotel.nombre} <br>
                <b>Ubicación:</b> ${hotel.ubicacion} <br>
            </button>
        </form>
    </c:forEach>

    <button onclick="window.location.href='/home-client'">Regresar</button>

</html>