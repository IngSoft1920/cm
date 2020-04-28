<%@taglib uri = "http://www.springframework.org/tags/form" prefix = "form"%>
<%@ taglib uri = "http://java.sun.com/jsp/jstl/core" prefix = "c" %>
<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<html>
   <head>
      <title>Beneficios</title>
   </head>
   
   <body>
   
     
      <div align="center">
     <h1> Información Seleccionada</h1>
	<c:forEach items="${treasureMap}" var="entry">
		    <table border="1" cellpadding="5" style="background-color:lightgrey">
		    	   	<caption><h2>Submitted User Information for ${entry.value.nombreHotel} <br>ID: ${entry.key}</td>   </h2></caption>
		   			 
		   			  
					 < c:if test = "${BeneficioBean.reserva}">
					 <tr>
							 <td> <b>Ingresos por estancia en habitaciones:</b> </td>
        							<c:forEach items="${entry.value.sumaReservas}" var="serv">
				   	     				    <td>${serv.key}: <vd> ${serv.value}</vd><td>
				 	      			   </c:forEach>
					</tr>
      							 </c:if>
				

						<c:if test = "${BeneficioBean.servicios}">
					<tr>
						 <td> <b>Ingresos por servicios ofrecidos:</b> </td>
        					<c:forEach items="${entry.value.sumaFacturas}" var="fact">
		       			    <td>${fact.key}: <vd> ${fact.value}</vd><td>
				 	      	 </c:forEach>
					</tr>
      					     </c:if>

					     <c:if test = "${BeneficioBean.empleados}">
					     <tr>
						 <td> <b>Dinero Invertido en Empleados:</b> </td>
        					<c:forEach items="${entry.value.sueldoEmpleados}" var="sl">
		       			    <td>${sl.key}: <vd> ${sl.value}</vd><td>
				 	      	 </c:forEach>
						 </tr>
      					     </c:if>

					     <c:if test = "${BeneficioBean.comida}">
					     <tr>
						 <td> <b>Gastos en productos de proveedores:</b> </td>
        					<c:forEach items="${entry.value.gastoComida}" var="co">
		       			    <td>${co.key}: <vd> ${co.value}</vd><td>
				 	      	 </c:forEach>
				 	      	</tr> 
      					     </c:if>
									     
					
              						

							
					 </table>
		</c:forEach>					
         				 	
						 </div>

				 </body>
				</html>
			