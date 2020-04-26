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
					 < c:if test = "${BeneficioBean.reserva}">
							 <td> <b>Ingresos por estancia en habitaciones:</b> </td>
        							<c:forEach items="${entry.value.sumaReservas}" var="serv">
				   	     				    <td>${serv.key}: <vd> ${serv.value}</vd><td>
				 	      			   </c:forEach>
      							 </c:if>

						<c:if test = "${BeneficioBean.servicios}">
						 <td> <b>Ingresos por servicios ofrecidos:</b> </td>
        					<c:forEach items="${entry.value.sumaFacturas}" var="fact">
		       			    <td>${fact.key}: <vd> ${fact.value}</vd><td>
				 	      	 </c:forEach>
      					     </c:if>

					     <c:if test = "${BeneficioBean.empleados}">
						 <td> <b>Dinero Invertido en Empleados:</b> </td>
        					<c:forEach items="${entry.value.sueldoEmpleados}" var="sl">
		       			    <td>${sl.key}: <vd> ${sl.value}</vd><td>
				 	      	 </c:forEach>
      					     </c:if>

					     <c:if test = "${BeneficioBean.comida}">
						 <td> <b>Dinero Invertido en Productos Alimenticios:</b> </td>
        					<c:forEach items="${entry.value.gastoComida}" var="fact">
		       			    <td>${fact.key}: <vd> ${fact.value}</vd><td>
				 	      	 </c:forEach>
      					     </c:if>
									     
					</tr>
              						

							

							
         				 	 </table>

				 </body>
				</html>
			