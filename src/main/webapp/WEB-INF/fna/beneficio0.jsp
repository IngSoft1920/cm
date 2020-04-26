<%@taglib uri = "http://www.springframework.org/tags/form" prefix = "form"%>
<html>
   <head>
      <title>Spring MVC Form Handling</title>
   </head>
   <body>
	<h2>Seleccione la informacion a mostrar</h2>

	 <form:form method = "POST" action = "beneficio0" modelAttribute="BeneficioBean">
         <table>


	<tr>
              <td> <form:checkbox path="reserva"/>Ingresos de Reservas </td>       
            </tr>

	
	    <tr>
               <td><form:checkbox path="servicios" />Ingresos por Servicios</td>       
            </tr>

		<tr>
               <td><form:checkbox path="empleados"/>Dinero invertido en empleados</td>       
            </tr>
	    		 

		<tr>
               <
               <td><form:checkbox path = "comida" /> Dinero Invertido en Productos Alimenticios</td>
            </tr> 

	    <tr>
               <td colspan = "2">
                  <input type = "submit" value = "Submit"/>
               </td>
            </tr>

	 </table>  
      </form:form>
   </body>
</html>
