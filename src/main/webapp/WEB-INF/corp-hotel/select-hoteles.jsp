<%@ page language="java" contentType="text/html; charset=utf-8"
	pageEncoding="utf-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>


<html lang="en">

<head>
<meta charset="UTF-8" />
<meta name="viewport" content="width=device-width, initial-scale=1.0" />
<meta http-equiv="X-UA-Compatible" content="ie=edge" />
<link
	href="https://fonts.googleapis.com/css?family=Poppins:400,500&display=swap"
	rel="stylesheet" />


<title>Selecciona el hotel</title>



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
<link href="/css/bootstrap.min.css" rel="stylesheet">

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
					<strong class="navbar-brand">Selecciona el hotel para visualizar los empleados</strong>
					<a class="navbar-nav" href="/inicio">Inicio</a> 
					
				</div>
			</div>
		</header>

		<main role="main">
			<!--Estilo de cajas para listas-->
			<div class="album py-5 bg-light">
				<div class="container">
					<div class="row">
						<!-- For Each para devolver lista de hoteles -->
						<c:forEach var="hotel" items="${hoteles}">	
							<div class="col-md-4">
								<div class="card mb-4 shadow-sm">
									<svg class="bd-placeholder-img card-img-top" width="100%"
										height="225" xmlns="http://www.w3.org/2000/svg"
										preserveAspectRatio="xMidYMid slice" focusable="false"
										role="img" aria-label="Placeholder: Thumbnail">
										
										<title>${hotel.nombre}</title>
                                    	<rect width="100%" height="100%" fill="#55595c"></rect>
										<text x="50%" y="50%" fill="#eceeef" dy=".3em">${hotel.ciudad}</text>
									</svg>
									   
									<div class="card-body">
										<p class="card-text">${hotel.nombre}</p>
										<div class="d-flex justify-content-between align-items-center">
											<div class="btn-group">
												<a class="btn btn-sm btn-outline-primary"
													href="/select/empleados/${hotel.id}" role="button">Seleccionar</a> 
													
											</div>
										</div>
									</div>
								</div>
							</div>
						</c:forEach>
					</div>
				</div>
			</div>
		</main>

		<footer class="text-muted">
			<div class="container">
				<p class="float-right">
					<a href="">Volver arriba</a>
				</p>
				<p>Lista de Hoteles de nuestra Cadena.</p>
			</div>
		</footer>
		<script src="/js/jquery-3.4.1.slim.min.js"></script>
		<script>
			window.jQuery
					|| document
							.write('<script src="/docs/4.4/assets/js/vendor/jquery.slim.min.js"><\/script>')
		</script>
		<script src="/js/bootstrap.bundle.min.js"></script>
	</div>
</body>
</html>