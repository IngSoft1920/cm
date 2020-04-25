<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<!DOCTYPE html>
<!-- saved from url=(0052)https://getbootstrap.com/docs/4.4/examples/checkout/ -->
<html lang="en">

<head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">

    <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">
    <meta name="description" content="">
    <meta name="author" content="Mark Otto, Jacob Thornton, and Bootstrap contributors">
    <meta name="generator" content="Jekyll v3.8.6">
    <title>Editar Hotel</title>

    <link rel="canonical" href="https://getbootstrap.com/docs/4.4/examples/checkout/">

    <!-- Bootstrap core CSS -->
    <link href="../css/bootstrap.min.css" rel="stylesheet">

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
            <h2>Editar Hotel</h2>
            <p class="lead">Modificar datos</p>
        </div>

        <div class="row center">
            <div class="col-md-18 order-md-1">
                <form class="needs-validation" method="POST" novalidate>

                    <div class="mb-3">
                        <label>Nombre</label>
                        <input type="text" class="form-control" name="nombre" value="${hotel.nombre}" required>
                        <div class="invalid-feedback">Campo obligatorio</div>
                    </div>

                    <div class="mb-3">
                        <label>Continente</label>
                        <input type="text" class="form-control" name="continente" value="${hotel.continente}" required>
                        <div class="invalid-feedback">Campo obligatorio</div>
                    </div>

                    <div class="mb-3">
                        <label>País</label>
                        <input type="text" class="form-control" name="pais" value="${hotel.pais}" required>
                        <div class="invalid-feedback">Campo obligatorio</div>
                    </div>

                    <div class="mb-3">
                        <label>Ciudad</label>
                        <input type="text" class="form-control" name="ciudad" value="${hotel.ciudad}" required>
                        <div class="invalid-feedback">Campo obligatorio</div>
                    </div>

                    <div class="mb-3">
                        <label>Direccion</label>
                        <input type="text" class="form-control" name="direccion" value="${hotel.direccion}" required>
                        <div class="invalid-feedback">Campo obligatorio</div>
                    </div>

                    <div class="mb-3">
                        <label>Estrellas</label>
                        <input type="number" min="0" max="6" class="form-control" name="estrellas" value="${hotel.estrellas}" required>
                        <div class="invalid-feedback">No válido</div>
                    </div>

                    <div class="mb-3">
                        <label>Descripción</label>
                        <textarea type="text" class="form-control" name="descripcion">${hotel.descripcion}</textarea>
                    </div>

                    <div class="row">
                        <div class="col-md-18 mb-3">
                            <label>Servicios</label>
                            <c:forEach var="servicio" items="${servicios}">
                                <div class="form-check mb-3"">
                                    <label>${servicio.nombre}</label>
                                    <input type="number" class="form-control" name="numInstalaciones" min="0" placeholder="número de instalaciones">
                                    <input type="number" class="form-control" name="precios" min="0" placeholder="precio">
                                    <input type="text" class="form-control" name="unidadesMedida" placeholder="unidad de medida">
                                    <input type="hidden" name="serviciosIDs" value="${servicio.id}">
                                </div>
                            </c:forEach>
                        </div>        
                    </div>

                    <div class="row">
                        <div class="col-md-5 mb-3">
                            <label>Categorias</label>
                            <c:forEach var="categoria" items="${categorias}">
                                <div class="form-check">
                                    <input class="form-check-input" type="checkbox" value="${categoria.id}" name="categoriasIDs">
                                    <label class="form-check-label" for="defaultCheck1">${categoria.nombre}</label>
                                </div>
                            </c:forEach>
                        </div>
                    </div>
                   
                    <div class="row">
                        <div class="col-md-18">
                            <label>Habitaciones</label>
                            <c:forEach var="hab" items="${habs}">
                                <div class="form-check">
                                    <label>${hab.nombre_tipo}</label>
                                    <input type="number" class="form-control" name="numDisponibles" required>
                                    <input type="hidden" name="habsIDs" value="${hab.id}">
                                </div>
                            </c:forEach>
                        </div>        
                    </div>

                    
                    <hr class="mb-4">
                    <button class="btn btn-primary btn-lg " type="submit" >Guardar cambios</button>
                    <a class="btn btn-secondary btn-lg" href="/hoteles" role="button">Cancelar</a>
                </form>
            </div>
        </div>


        <footer class="my-5 pt-5 text-muted text-center text-small">
            <p class="mb-1">© 2020 Company Management - UPM</p>
            <div class="container">
                <p class="float-left">
                    <a href="/hoteles">Volver a hoteles</a>
                </p>
                <p class="float-right">
                    <a href="/inicio">Volver a inicio</a>
                </p>
            </div>
        </footer>
    </div>
    <script src="../js/jquery-3.4.1.slim.min.js"></script>
    <script>
        window.jQuery || document.write('<script src="/docs/4.4/assets/js/vendor/jquery.slim.min.js"><\/script>');
        document.getElementsByName('numDisponibles').forEach( elem => elem.setAttribute("value","0") );
    </script>
    <script src="../js/bootstrap.bundle.min.js"></script>
    <script src="../js/form-validation.js"></script>

</body>

</html>