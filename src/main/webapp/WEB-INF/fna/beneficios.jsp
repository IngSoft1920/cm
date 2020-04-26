<%@taglib uri = "http://www.springframework.org/tags/form" prefix = "form"%>
<%@ taglib uri = "http://java.sun.com/jsp/jstl/core" prefix = "c" %>
<html>
   <head>
      <title>Beneficios</title>
   </head>
   
   <body>
   
      <h2>Submitted User Information</h2>
      <table>
	
	<c:forEach items="${treasureMap}" var="entry">
		   			  <tr>
						 <td>Ingresos por Hotel</td>
           					  <td> 
              					        <c: forEach items="${Hoteles}" var="h">
								    <c:choose>
									 <c:when test="${h == ${entry.value.nombreHotel}}">
       									 	 <b>Hotel:</b> ${entry.value.nombreHotel} <emsp><b>ID:</b> ${entry.key}<br></td>
										 <td>
										  <c:forEach items="${entry.value.sumaReservas}" var="serv">
										  <c:if test = "${${HabitacionTipo}.contains(${serv.key)}">
										  <tr> <b> Ingresos por Tipo de Habitacion:</b> </tr>
        									    <emsp>${serv.key}: <vd> ${serv.value}</vd><br>
      										   </c:if>
				   						   </c:forEach>
										</td>
										<td>
										  <c:forEach items="${entry.value.sumaFacturas}" var="fact">
										  <c:if test = "${${Servicios}.contains(${fact.key)}">
										 <tr> <b>Ingresos por servicios ofrecidos:</b></tr>
        									    <tr><emsp>${fact.key}: <vd> ${fact.value}</vd><br></tr>
      										   </c:if>
				   						   </c:forEach>
										</td>

										<td>
										  <c:forEach items="${entry.value.sueldosEmpleados}" var="suel">
										  <c:if test = "${${Empleados}.contains(${suel.key)}">
										 <tr> <b>Sueldo de los Empleados por Rol:</b></tr>
        									   <tr> <emsp>${suel.key}: <vd> ${suel.value}</vd>   </tr>
      										   </c:if>
				   						   </c:forEach>
										</td>

										<td>
										 <c:if test = "${${Comida}}">
										 <tr> <b>Gasto en productos alimenticios:</b></tr>
        									   <tr> <emsp>${entry.value.gastoComida}: <vd> ${suel.value}</vd>   </tr>
      										   </c:if>
										</td>
       									 

									   
   									    </c:when>    
   									     <c:otherwise>
       									     
        								      
   									       </c:otherwise>
									       </c:choose>
									      </c:forEach>
									  </tr>
              						</c:forEach>

							

							
         				 	 </table>

				 </body>
				</html>