<%@ taglib uri = "http://java.sun.com/jsp/jstl/core" prefix = "c" %>
<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>

<!DOCTYPE html>
<html>


<head>
<meta charset="UTF-8" />
<meta name="viewport" content="width=device-width, initial-scale=1.0" />
<meta http-equiv="X-UA-Compatible" content="ie=edge" />
<link href="https://fonts.googleapis.com/css?family=Poppins:400,500&display=swap" rel="stylesheet" />


<title>Beneficio</title>


<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">

<meta name="viewport"
	content="width=device-width, initial-scale=1, shrink-to-fit=no">
<meta name="description" content="">
<meta name="author"
	content="Mark Otto, Jacob Thornton, and Bootstrap contributors">
<meta name="generator" content="Jekyll v3.8.6">


<link rel="canonical" href="https://getbootstrap.com/docs/4.4/examples/album/">

<!-- Bootstrap core CSS -->
<link href="/css/bootstrap.min.css" rel="stylesheet" crossorigin="anonymous">

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
					<a class="navbar-brand ">
					 <strong class="navbar-nav">Beneficio 2020</strong> 
					 </a> 
					<a class="navbar-nav" href="/inicio">Inicio</a> 
				</div>
			</div>
		</header>
				
			<main role="main">
		
		       <table class="table table-striped" line-height="200px" height="200px">
		       
		                     
		       
		   <tr>
		 <th>Reservas  </th>	 
		 <td>	${reservas}</td>
		 </tr>
		
   		 <td colspan="5"></td>
			
		  <tr>
		 <td>Servicios            </td>	<td>			${servicios}		</td>
		 </tr>
		 
		 <td colspan="5"></td>
		 <tr>
		<td>Pedidos a proveedores   </td> <td> ${proveedores}</td>
		</tr>
		
		
		<tr>
		<td><b>Beneficio en Bruto	  </b></td><td>	${beneficio_bruto}		</td>
		</tr>

		
		<tr>
		<td>Salarios</td>  <td>	${sueldos}</td>
		</tr>
		
   								
		 <tr class="blank_row">
   		 <td colspan="5"></td>
		 </tr>
		
		   <tr><td><b>	BENEFICIO NETO/(PÉRDIDA) </b></td>	<td>	  ${beneficio_neto}     </td> </tr>
		   </table>
		</main>
		
		</div>
	</body>
	

</html>