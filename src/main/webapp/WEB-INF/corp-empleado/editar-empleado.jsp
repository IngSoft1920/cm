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
    <title>Editar Empleado</title>

    <link rel="canonical" href="https://getbootstrap.com/docs/4.4/examples/checkout/">

    <!-- Bootstrap core CSS -->
    <link href="/css/bootstrap.min.css" rel="stylesheet">

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
    <link href="/css/form-validation.css" rel="stylesheet">
</head>

<body class="bg-light">
    <div class="container">
        <div class="py-5 text-center">
            <h2>Editar Empleado</h2>
            <p class="lead">Modificar los datos del empleado</p>
        </div>

        <div class="row center">
            <div class="col-md-18 order-md-1">
                <form class="needs-validation" method="POST" novalidate>

                    <div class="mb-3">
                        <label>Nombre</label>
                        <input type="text" class="form-control" name="firstName" value="${empleado.nombre}" required>
                        <div class="invalid-feedback">Campo obligatorio</div>
                    </div>

                    <div class="mb-3">
                        <label>Apellidos</label>
                        <input type="text" class="form-control" name="lastNames" value="${empleado.apellidos}" required>
                        <div class="invalid-feedback">Campo obligatorio</div>
                    </div>

                    <div class="mb-3">
                        <label>Email </label>
                        <input type="text" class="form-control" name="email" value="${empleado.email}" required>
                        <div class="invalid-feedback">Campo obligatorio</div>
                    </div>

                    <div class="mb-3">
                        <label>Teléfono</label>
                        <input type="text" class="form-control" name="telefono" value="${empleado.telefono}" required>
                        <div class="invalid-feedback">Campo obligatorio</div>
                    </div>

                    <div class="mb-3">
                        <label>Sueldo</label>
                        <input type="number" class="form-control" name="sueldo" min="0" value="${empleado.sueldo}" required>
                        <div class="invalid-feedback">Campo obligatorio</div>
                    </div>

                    <div class="row">
                        <div class="col-md-5">
                            <label>Profesión</label>
                            <c:forEach var="profesion" items="${profesiones}">  
                                <div class="form-check">
                                    <input class="form-check-input" type="radio" value="${profesion.id}" name="profesionID" required <c:if test="${profesion.id == empleado.profesion_id}">checked="true"</c:if> >
                                    <label>${profesion.nombre}</label>
                                </div>
                            </c:forEach>
                        </div>
                    </div>
                    
                    <hr class="mb-4">
                    <button class="btn btn-primary btn-lg " type="submit" >Guardar cambios</button>
                    <a class="btn btn-secondary btn-lg" href="/empleados" role="button">Cancelar</a>
                </form>
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
    <script src="/js/jquery-3.4.1.slim.min.js"></script>
    <script>
        window.jQuery || document.write('<script src="/docs/4.4/assets/js/vendor/jquery.slim.min.js"><\/script>')
    </script>
    <script src="/js/bootstrap.bundle.min.js"></script>
    <script src="/js/form-validation.js"></script>

</body>

</html>