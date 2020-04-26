<%@taglib uri = "http://www.springframework.org/tags/form" prefix = "form"%>
<html>
   <head>
      <title>Spring MVC Form Handling</title>
   </head>
   <body>
	<h2>Seleccione la informacion a mostrar</h2>

	 <form:form method = "POST" action = "beneficio0">
         <table>

		<tr>
               <td><form:label path = "nombresHotel">Hoteles</form:label></td>
               <td>
                  <form:select path = "nombresHotel" items = "${hotelesList}"
                     multiple = "true" />
               </td>
            </tr>


	<tr>
               <td><form:label path = "tiposHabitacion"> Tipos de Habitacion </form:label></td>
               <td><form:checkboxes items = "${habitacionesList}" path = "tiposHabitacion" /></td>       
            </tr>


	    <tr>
               <td><form:label path = "tiposServicio"> Tipos de Servicios </form:label></td>
               <td><form:checkboxes items = "${serviciosList}" path = "tiposServicio" /></td>       
            </tr>

		<tr>
               <td><form:label path = "tiposEmpleados"> Roles de Empleados </form:label></td>
               <td><form:checkboxes items = "${empleadosList}" path = "tiposEmpleados" /></td>       
            </tr>
	    		 

		<tr>
               <td><form:label path = "comida"> Comida </form:label></td>
               <td><form:checkbox path = "comida" /></td>
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
