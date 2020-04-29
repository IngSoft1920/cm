<%@taglib uri = "http://www.springframework.org/tags/form" prefix = "form"%>
<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<html>
   <head>
      <title>Parametros de Búsqueda</title>
   </head>
   <body>
   <div align="center">
	<h2>Seleccione la informacion a mostrar</h2>

	 <form:form method = "POST" action = "beneficio0" modelAttribute="BeneficioBean">
         <table line-height="200px" height="200px">


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
               
               <td><form:checkbox path = "comida" /> Gastos en productos de proveedores</td>
            </tr> 

	    <tr>
               <td colspan = "2">
                  <input type = "submit" value = "Submit"/>
               </td>
            </tr>

	 </table>  
      </form:form>
      </div>
   </body>
</html>
