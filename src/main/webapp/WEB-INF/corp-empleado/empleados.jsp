<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<html lang="en">

<head>
    <meta charset="UTF-8" />
    <meta name="viewport" content="width=device-width, initial-scale=1.0" />
    <meta http-equiv="X-UA-Compatible" content="ie=edge" />
    <link href="https://fonts.googleapis.com/css?family=Poppins:400,500&display=swap" rel="stylesheet" />


    <title>Empleados</title>



    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">

    <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">
    <meta name="description" content="">
    <meta name="author" content="Mark Otto, Jacob Thornton, and Bootstrap contributors">
    <meta name="generator" content="Jekyll v3.8.6">


    <link rel="canonical" href="https://getbootstrap.com/docs/4.4/examples/album/">

    <!-- Bootstrap core CSS -->
    <link href="/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-Vkoo8x4CGsO3+Hhxv8T/Q5PaXtkKtu6ug5TOeNV6gBiFeWPGFN9MuhOf23Q9Ifjh" crossorigin="anonymous">

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

    <link href="/css/album.css" rel="stylesheet">
</head>

<body>
    <div class="conteiner">


        <header>

            <div class="navbar navbar-light bg-light shadow-sm">
                <div class="container d-flex align-content-start">
                    <a href="" class="navbar-brand ">

                        <strong>Empleados</strong>
                    </a>
                    <a class="navbar-nav" href="/inicio">Inicio</a>
                    <a class="navbar-nav" href="anadir-empleado.html">Añadir Empleado</a>
                </div>
            </div>
        </header>


        <main role="main">
            <div class="album py- bg-light">
                <div class="container">  
                    <div class="row">
                        
                            <table class="table table-striped">
                                <thead>
                                    <tr>
                                        <th scope="col">#</th>
                                        <th scope="col">Nombre</th>
                                        <th scope="col">Apellido</th>
                                        <th scope="col">Email</th>
                                        <th scope="col">Sueldo</th>
                                        <th scope="col">Acciones</th>

                                    </tr>
                                </thead>
                        
                                <tbody>
                        <c:forEach var="empleado" items="${empleados}">            
                                    <tr>
                                        <th scope="row">${empleado.id}</th>
                                        <td>${empleado.nombre}</td>
                                        <td>${empleado.apellidos}</td>
                                        <td>${empleado.email}</td>
                                        <td>${empleado.sueldo}$</td>
                                        <td>
                                            <div class="btn-group">
                                                <a class="btn btn-sm btn-outline-secondary" href="/ver-empleado/${empleado.id}" role="button">Ver</a>
                                                <a class="btn btn-sm btn-outline-secondary" href="/editar-empleado/${empleado.id}" role="button">Editar</a>
                                                <!-- Button trigger modal -->
                                                <a class="btn btn-sm btn-outline-secondary" href="/eliminar-empleado/${empleado.id}" role="button">
                                                    Eliminar
                                                </a>
                                                <!-- Modal -->
                                                
                                        </td>
                                    </tr>
                        </c:forEach>     
                                </tbody>
                          

                            </table>
                        
                    </div>
                </div>
            </div>


        </main>

        <footer class="text-muted">
            <div class="container">
                <p class="float-right">
                    <a href="">Back to top</a>
                </p>
                <p>Lista de empleados que conforman nuestra cadena.</p>
            </div>
        </footer>
        <script src="/js/jquery-3.4.1.slim.min.js" integrity="sha384-J6qa4849blE2+poT4WnyKhv5vZF5SrPo0iEjwBvKU7imGFAV0wwj1yYfoRSJoZ+n" crossorigin="anonymous"></script>
        <script>
            window.jQuery || document.write('<script src="/docs/4.4/assets/js/vendor/jquery.slim.min.js"><\/script>')
        </script>
        <script src="/js/bootstrap.bundle.min.js" integrity="sha384-6khuMg9gaYr5AxOqhkVIODVIvm9ynTT5J4V1cfthmT+emCG6yVmEZsRHdxlotUnm" crossorigin="anonymous"></script>


        </div>

</body>

</html>