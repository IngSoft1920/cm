<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<!DOCTYPE html>
<html lang="en">

<head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">

    <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">
    <meta name="description" content="">
    
    <meta name="author" content="Mark Otto, Jacob Thornton, and Bootstrap contributors">
    <meta name="generator" content="Jekyll v3.8.6">
    <title>Añadir Empleado</title>

    <link rel="canonical" href="https://getbootstrap.com/docs/4.4/examples/checkout/">

    <!-- Bootstrap core CSS -->
    <link href="/css/bootstrap.min.css" rel="stylesheet" >

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
            <h2>Añadir Empleado</h2>
            <p class="lead">Añadir empleado a la cadena</p>
        </div>
        
       
                <form class="needs-validation" method="POST" novalidate>
                
                    <div class="form-group">
                        <label>Nombre</label>
                        <input type="text" class="form-control" name="firstName" placeholder="Juan" required>
                        <div class="invalid-feedback">Campo obligatorio</div>
                    </div>

                    <div class="form-group">
                        <label>Apellidos</label>
                        <input type="text" class="form-control" name="lastNames" placeholder="López" required>
                        <div class="invalid-feedback">Campo obligatorio</div>
                    </div>

                    <div class="form-group">
                        <label>Email</label>
                        <input type="email" class="form-control" name="email" placeholder="soyun@ejemplo.com" required>
                        <div class="invalid-feedback">Campo obligatorio</div>
                    </div>

                    <div class="form-group">
                        <label>Teléfono</label>
                        <input type="text" class="form-control" name="telefono" placeholder="600612634" required>
                        <div class="invalid-feedback">Campo obligatorio</div>
                    </div>

                    <div class="form-group">
                        <label>Sueldo</label>
                        <input type="number" class="form-control" name="sueldo" placeholder="1950" min="0" required>
                        <div class="invalid-feedback">No válido</div>
                    </div>
                    <div class="form-group">
                        <label>Superior</label>
                        <input type="email" class="form-control" name="superior" placeholder="juan@gmail.com">
                    </div>

                    <div class="row">
                        <div class="col-md-10">
                            <label class="col-form-label-lg" >Profesión</label>
                            <c:forEach var="profesion" items="${profesiones}">  
                                <div class="form-check">
                                    <input type="radio" class="form-check-input" name="profesionID" value="${profesion.id}">
                                   
                                     <label class="form-check-label" >${profesion.nombre}</label>
                                </div>
                                
                            </c:forEach> 
                        </div>
                    </div>
                    
                    <div class="row">
                        <div class="col-md-10">
                            <label  class="col-form-label-lg">Días Libres</label>
    
                            <div class="form-check">
                                <input type="checkbox" class="form-check-input" name="diasLibres" value="0" >
                                <label class="form-check-label">Lunes</label>
                            </div>
                          
                            <div class="form-check">
                                <input type="checkbox" class="form-check-input" name="diasLibres" value="1" >
                                <label class="form-check-label">Martes</label>
                            </div>

                            <div class="form-check">
                                <input type="checkbox" class="form-check-input" name="diasLibres" value="2" >
                                <label class="form-check-label">Miércoles</label>
                            </div>

                             <div class="form-check">
                                 <input type="checkbox" class="form-check-input" name="diasLibres" value="3" >
                                 <label class="form-check-label">Jueves</label>
                            </div>

                            <div class="form-check">
                                <input type="checkbox" class="form-check-input" name="diasLibres" value="4" >
                                <label class="form-check-label">Viernes</label>
                            </div>

                            <div class="form-check">
                                <input type="checkbox" class="form-check-input" name="diasLibres" value="5" >
                                <label class="form-check-label">Sábado</label>
                            </div>
                          
                            <div class="form-check">
                                <input type="checkbox" class="form-check-input" name="diasLibres" value="6" >
                                <label class="form-check-label">Domingo</label>
                            </div>
                        </div>
                    </div>


                    <hr class="mb-4">
                    <button class="btn btn-primary btn-lg btn-block" type="submit">Añadir</button>
                </form>
            </div>
     
        <footer class="my-5 pt-5 text-muted text-center text-small">
            <p class="mb-1">© 2020 Company Management - UPM</p>
            <div class="container">
                <p class="float-left">
                    <a href="/select/empleados/${id}">Volver a empleados</a>
                </p>
                <p class="float-right">
                    <a href="/inicio">Volver a inicio</a>
                </p>
            </div>
        </footer>
    
    <script src="/js/jquery-3.4.1.slim.min.js"></script>
    <script>
        window.jQuery || document.write('<script src="/docs/4.4/assets/js/vendor/jquery.slim.min.js"><\/script>')
    </script>
    <script src="/js/bootstrap.bundle.min.js" ></script>
    <script src="/js/form-validation.js"></script>

</body>

</html>