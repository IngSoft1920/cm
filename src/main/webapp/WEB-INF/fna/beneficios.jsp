<%@taglib uri = "http://www.springframework.org/tags/form" prefix = "form"%>
<%@ taglib uri = "http://java.sun.com/jsp/jstl/core" prefix = "c" %>
<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>

<html>
<html lang="en">

<head>
<meta charset="UTF-8" />
<meta name="viewport" content="width=device-width, initial-scale=1.0" />
<meta http-equiv="X-UA-Compatible" content="ie=edge" />
<link
	href="https://fonts.googleapis.com/css?family=Poppins:400,500&display=swap"
	rel="stylesheet" />


<title>Hoteles</title>



<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">

<meta name="viewport"
	content="width=device-width, initial-scale=1, shrink-to-fit=no">
<meta name="description" content="">
<meta name="author"
	content="Mark Otto, Jacob Thornton, and Bootstrap contributors">
<meta name="generator" content="Jekyll v3.8.6">


<link rel="canonical"
	href="https://getbootstrap.com/docs/4.4/examples/album/">

<!-- Bootstrap core CSS -->
<link href="/css/bootstrap.min.css" rel="stylesheet"
	integrity="sha384-Vkoo8x4CGsO3+Hhxv8T/Q5PaXtkKtu6ug5TOeNV6gBiFeWPGFN9MuhOf23Q9Ifjh"
	crossorigin="anonymous">

<!-- Favicons -->
<link rel="apple-touch-icon"
	href="https://getbootstrap.com/docs/4.4/assets/img/favicons/apple-touch-icon.png"
	sizes="180x180">
<link rel="icon"
	href="https://getbootstrap.com/docs/4.4/assets/img/favicons/favicon-32x32.png"
	sizes="32x32" type="image/png">
<link rel="icon"
	href="https://getbootstrap.com/docs/4.4/assets/img/favicons/favicon-16x16.png"
	sizes="16x16" type="image/png">
<link rel="manifest"
	href="https://getbootstrap.com/docs/4.4/assets/img/favicons/manifest.json">
<link rel="mask-icon"
	href="https://getbootstrap.com/docs/4.4/assets/img/favicons/safari-pinned-tab.svg"
	color="#563d7c">
<link rel="icon"
	href="https://getbootstrap.com/docs/4.4/assets/img/favicons/favicon.ico">
<meta name="msapplication-config"
	content="/docs/4.4/assets/img/favicons/browserconfig.xml">
<meta name="theme-color" content="#563d7c">




<style>
.bd-placeholder-img {
	font-size: 1.125rem;
	text-anchor: middle;
	-webkit-user-select: none;
	-moz-user-select: none;
	-ms-user-select: none;
	user-select: none;
}

@media ( min-width : 768px) {
	.bd-placeholder-img-lg {
		font-size: 3.5rem;
	}
}
</style>
<!-- Custom styles for this template -->

<link href="/css/album.css" rel="stylesheet">
</head>

<body>
	<div class="conteiner">


		<header>

			<div class="navbar navbar-light bg-light shadow-sm">
				<div class="container d-flex align-content-start">
					<a href="" class="navbar-brand "> <strong>Beneficio</strong>
					</a> <a class="navbar-nav" href="/inicio">Inicio</a> <a
					

				</div>
			</div>
		</header>


		<main role="main">

   <head>
      <title>Beneficios</title>
   </head>
   
   <body>
   
     
      <div align="center">
     <h1> Información Seleccionada</h1>
     </div>
     <br><br><br>
	<c:forEach items="${treasureMap}" var="entry">
		    <table class="table table-striped"">
		    	   	<h2><pre>       Información solicitada para ${entry.value.nombreHotel} <br>       ID: ${entry.key}</td></pre>   </h2>
		   			 
		   			  
					 < c:if test = "${BeneficioBean.reserva}">
					 <tr>
							 <td> <b>Ingresos por estancia en habitaciones:</b> </td>
        							<c:forEach items="${entry.value.sumaReservas}" var="serv">
				   	     				    <td>${serv.key}: <vd> ${serv.value}€</vd><td>
				 	      			   </c:forEach>
					</tr>
      							 </c:if>
				

						<c:if test = "${BeneficioBean.servicios}">
					<tr>
						 <td> <b>Ingresos por servicios ofrecidos:</b> </td>
        					<c:forEach items="${entry.value.sumaFacturas}" var="fact">
		       			    <td>${fact.key}: <vd> ${fact.value}€</vd><td>
				 	      	 </c:forEach>
					</tr>
      					     </c:if>

					     <c:if test = "${BeneficioBean.empleados}">
					     <tr>
						 <td> <b>Dinero Invertido en Empleados:</b> </td>
        					<c:forEach items="${entry.value.sueldoEmpleados}" var="sl">
		       			    <td>${sl.key}: <vd> ${sl.value} €</vd><td>
				 	      	 </c:forEach>
						 </tr>
      					     </c:if>

					     <c:if test = "${BeneficioBean.comida}">
					     <tr>
						 <td> <b>Gastos en productos de proveedores:</b> </td>
        					<c:forEach items="${entry.value.gastoComida}" var="co">
		       			    <td>${co.key}: <vd> ${co.value}</vd>€<td>
				 	      	 </c:forEach>
				 	      	</tr> 
      					     </c:if>
						<tr>
						<td>
						Total: ${entry.value.total} €
						</td>			     
						</tr>
              						

							
					 </table>
					 
					 	</c:forEach>					
						 <div align="center">
						 <table>
						 <tr><td>
						 <input type="button" value="Back" onClick="history.back()">
						</td> </tr>
						 </table>
						  <form action="/inicio" method="get">
     						   <table>
     						    <tr>
     						      <td colspan="2">
	      					       <input type="submit" value="Home" name="Submit" id="frm1_submit"/>
	     					         </td>
	      						  </tr>
     							   </table>
     							    </form>
							    </div>
						 </div>

						
						</main>
				 </body>
				</html>
			