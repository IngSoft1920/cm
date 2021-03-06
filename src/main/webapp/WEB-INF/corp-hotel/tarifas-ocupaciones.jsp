 <%@ page language="java" contentType="text/html; charset=utf-8"
	pageEncoding="utf-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>


<!DOCTYPE html>
<html lang="en">

<head>
    <meta charset="UTF-8" />
    <meta name="viewport" content="width=device-width, initial-scale=1.0" />
    <meta http-equiv="X-UA-Compatible" content="ie=edge" />
    <link href="https://fonts.googleapis.com/css?family=Poppins:400,500&display=swap" rel="stylesheet" />


    <title>Estadísticas</title>



    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">

    <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">
    <meta name="description" content="">
    <meta name="author" content="Mark Otto, Jacob Thornton, and Bootstrap contributors">
    <meta name="generator" content="Jekyll v3.8.6">


    <link rel="canonical" href="https://getbootstrap.com/docs/4.4/examples/album/">

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

    <link href="/css/album.css" rel="stylesheet">
</head>

<body>
    <div class="conteiner">

        <header>
            <div class="navbar navbar-light bg-light shadow-sm">
                <div class="container d-flex align-content-start">

                    <svg onclick="back();" class="bi bi-arrow-left" width="1em" height="1em" viewBox="0 0 16 16" fill="currentColor" xmlns="http://www.w3.org/2000/svg">
                        <path fill-rule="evenodd" d="M5.854 4.646a.5.5 0 010 .708L3.207 8l2.647 2.646a.5.5 0 01-.708.708l-3-3a.5.5 0 010-.708l3-3a.5.5 0 01.708 0z" clip-rule="evenodd"/>
                        <path fill-rule="evenodd" d="M2.5 8a.5.5 0 01.5-.5h10.5a.5.5 0 010 1H3a.5.5 0 01-.5-.5z" clip-rule="evenodd"/>
                    </svg>

                    <strong class="navbar-nav">${fecha}</strong>

                    <svg onclick="forward();" class="bi bi-arrow-right" width="1em" height="1em" viewBox="0 0 16 16" fill="currentColor" xmlns="http://www.w3.org/2000/svg" hr>
                        <path fill-rule="evenodd" d="M10.146 4.646a.5.5 0 01.708 0l3 3a.5.5 0 010 .708l-3 3a.5.5 0 01-.708-.708L12.793 8l-2.647-2.646a.5.5 0 010-.708z" clip-rule="evenodd"/>
                        <path fill-rule="evenodd" d="M2 8a.5.5 0 01.5-.5H13a.5.5 0 010 1H2.5A.5.5 0 012 8z" clip-rule="evenodd"/>
                    </svg>



                </div>
            </div>
        </header>


        <main role="main">	
            <div class="album py- bg-light">
                <div class="container">
                    <c:choose>
                        <c:when test="${empty estadisticas}">
                            <h4 class="text-center">Este hotel no tiene precio establecido en esta fecha</h4>
                        </c:when>

                        <c:otherwise>
                            <div class="row">
                                <table class="table table-striped">
                                    <thead>
                                        <tr>
                                            <th scope="col">Nombre Tipo</th>
                                            <th scope="col">Tarifa</th>
                                            <th scope="col">Ocupación</th>
                                        </tr>
                                    </thead>
                                    <tbody>
                                        <c:forEach var="estadistica" items="${estadisticas}">	
                                            <tr>
                                                <th>${estadistica.hab.nombre_tipo}</th>
                                                <td>${estadistica.tarifa}</td>
                                                <td>${estadistica.ocupacion}%</td>
                                            </tr>
                                        </c:forEach>
                                    </tbody>
                                </table>
                            </div>
                        </c:otherwise>
                    </c:choose>
                    <div class="form-check text-center">
                        <Label for="busc">Elegir fecha</Label>
                        <input id="busc" type="date">
                        <button onclick="buscador();" class ="btn-btn-primary" type="button" >Ir</button>
                    </div>
                    
                   
                </div>
            </div>

           

        </main>

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
        <script src="/js/jquery-3.4.1.slim.min.js"></script>
        <script>
            window.jQuery || document.write('<script src="/docs/4.4/assets/js/vendor/jquery.slim.min.js"><\/script>')
                
            const url = window.location.search;
            const urlParams = new URLSearchParams(url);
            const date = new Date(urlParams.get('fecha'));

            function back() {
                date.setDate( date.getDate() - 1 );
                window.location.href='/tarifas-ocupaciones/${hotel_id}?fecha='+date.toISOString().slice(0,10);
            }

            function forward() {
                date.setDate( date.getDate() + 1 );
                window.location.href='/tarifas-ocupaciones/${hotel_id}?fecha='+date.toISOString().slice(0,10);
            }

            function buscador() {
                if( document.getElementById('busc').value ) {
                    window.location.href='/tarifas-ocupaciones/${hotel_id}?fecha='+new Date(document.getElementById('busc').value).toISOString().slice(0,10);
                }
            }
        
        </script>
        <script src="/js/bootstrap.bundle.min.js" ></script>


    </div>

</body>

</html>