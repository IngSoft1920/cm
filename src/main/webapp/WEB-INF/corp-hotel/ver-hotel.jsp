<%@ page language="java" contentType="text/html; charset=utf-8"
	pageEncoding="utf-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>

<html lang="en">

<head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">

    <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">
    <meta name="description" content="">
    <meta name="author" content="Mark Otto, Jacob Thornton, and Bootstrap contributors">
    <meta name="generator" content="Jekyll v3.8.6">
    <title>Ver Hotel</title>

    <link rel="canonical" href="https://getbootstrap.com/docs/4.4/examples/checkout/">

    <!-- Bootstrap core CSS -->
    <link href="../css/bootstrap.min.css" rel="stylesheet" integrity="sha384-Vkoo8x4CGsO3+Hhxv8T/Q5PaXtkKtu6ug5TOeNV6gBiFeWPGFN9MuhOf23Q9Ifjh" crossorigin="anonymous">

    <!-- Favicons -->
    <link rel="apple-touch-icon" href="https://getbootstrap.com/docs/4.4/assets/img/favicons/apple-touch-icon.png" sizes="180x180">
    <link rel="icon" href="https://getbootstrap.com/docs/4.4/assets/img/favicons/favicon-32x32.png" sizes="32x32" type="image/png">
    <link rel="icon" href="https://getbootstrap.com/docs/4.4/assets/img/favicons/favicon-16x16.png" sizes="16x16" type="image/png">
    <link rel="manifest" href="https://getbootstrap.com/docs/4.4/assets/img/favicons/manifest.json">
    <link rel="mask-icon" href="https://getbootstrap.com/docs/4.4/assets/img/favicons/safari-pinned-tab.svg" color="#563d7c">
    <link rel="icon" href="https://getbootstrap.com/docs/4.4/assets/img/favicons/favicon.ico">
    <meta name="msapplication-config" content="/docs/4.4/assets/img/favicons/browserconfig.xml">
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
        
        @media (min-width: 768px) {
            .bd-placeholder-img-lg {
                font-size: 3.5rem;
            }
        }
    </style>
    <!-- Custom styles for this template -->
    <link href="../css/form-validation.css" rel="stylesheet">
</head>

<body class="bg-light">
    <div class="container">
        <div class="py-5 text-center">
            <h2>Visualizar Hotel</h2>
            <p class="lead">Ver los datos del hotel</p>
        </div>

        <div class="row center">
            <div class="col-md-18 order-md-1">
                <div class="mb-3">
                    <label>Nombre</label>
                    <input type="text" class="form-control" readonly value="${hotel.nombre}">
                </div>

                <div class="mb-3">
                    <label>Continente</label>
                    <input type="text" class="form-control" readonly value="${hotel.continente}">
                </div>

                <div class="mb-3">
                    <label>Pais</label>
                    <input type="text" class="form-control" readonly value="${hotel.pais}">
                </div>

                <div class="mb-3">
                    <label>Ciudad</label>
                    <input type="text" class="form-control" readonly value="${hotel.ciudad}">
                </div>

                <div class="mb-3">
                    <label>Dirección</label>
                    <input type="text" class="form-control" readonly value="${hotel.direccion}">
                </div>

                <div class="mb-3">
                    <label>Estrellas</label>
                    <input type="number" class="form-control" readonly value="${hotel.estrellas}">
                </div>

                <div class="row">
                    <div class="col-md-5 mb-3">
                        <label>Servicios</label>
                        <c:forEach var="servicio" items="${servicios}">
                            <div class="form-check">${servicio.nombre}</div>
                        </c:forEach>
                    </div>
                </div>                

                <div class="row">
                    <div class="col-md-5 mb-3">
                        <label>Categorias</label>
                        <c:forEach var="categoria" items="${categorias}">
                            <div class="form-check">${categoria.nombre}</div>
                        </c:forEach>
                    </div>
                </div>

                <div class="row">
                    <div class="col-md-5 mb-3">
                        <label>Habitaciones</label>
                        <c:forEach var="hab" items="${habs}">
                            <div class="form-check">
                                ${hab.nombre_tipo}
                                <input type="number" class="form-control" disabled placeholder="${hab.num_disponibles}">
                            </div>
                        </c:forEach>
                    </div>
                </div>

                <!-- Esto es una línea separatoria -->
                <hr class="mb-4">
                <a class="btn btn-primary btn-lg btn-block" href="/hoteles" role="button">Volver a Hoteles</a>
            </div>
        </div>



        <footer class="my-5 pt-5 text-muted text-center text-small">
            <p class="mb-1">© 2020 Company Management - UPM</p>
            
            <div class="container">
                <p class="float-right">
                    <a href="/inicio">Volver a inicio</a>
                </p>
            </div>
        </footer>
    </div>

    <script src="../js/jquery-3.4.1.slim.min.js" integrity="sha384-J6qa4849blE2+poT4WnyKhv5vZF5SrPo0iEjwBvKU7imGFAV0wwj1yYfoRSJoZ+n" crossorigin="anonymous"></script>
    <script>
        window.jQuery || document.write('<script src="/docs/4.4/assets/js/vendor/jquery.slim.min.js"><\/script>')
    </script>
    <script src="../js/bootstrap.bundle.min.js" integrity="sha384-6khuMg9gaYr5AxOqhkVIODVIvm9ynTT5J4V1cfthmT+emCG6yVmEZsRHdxlotUnm" crossorigin="anonymous"></script>
    <script src="../js/form-validation.js"></script>


</body>

</html>