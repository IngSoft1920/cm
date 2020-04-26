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
        .cajas {
            height: 670px;
        }

        .title {

            position: relative;
            left: 20px;
            top: 20px;


        font-family: Roboto;
        font-style: normal;
        font-weight: 500;
        font-size: 20px;
        line-height: 23px;
        /* identical to box height */
        
        letter-spacing: -0.05em;
        font-feature-settings: 'ss05' on, 'ss04' on, 'ss03' on, 'ss01' on, 'liga' off;
        
        color: #313962;
        }

        .localizacion {
position: relative;
left: 550px;

            width: 380px;
            height: 200px;
    
    background: #FBFBFB;
    border: 1px solid #FFFFFF;
    box-sizing: border-box;
    box-shadow: 2px 2px 10px #F0F0F0;
    border-radius: 40px;
        }


        .line-1 {
            position: relative;
            left: 15px;

        }
        .big-line {
            position: relative;
            right: 470px;
        }
        .bloque {
            position: relative;
            left: 20px;
        }

        .info{

    font-family: Roboto;
    font-style: normal;
    font-weight: normal;
    font-size: 16px;
    line-height: 19px;
    letter-spacing: -0.05em;
    
    color: #616887;
        }
        .descripcion {
position: relative;
left: 50px;
bottom: 200px;

            width: 380px;
            height: 200px;
    
    background: #FBFBFB;
    border: 1px solid #FFFFFF;
    box-sizing: border-box;
    box-shadow: 2px 2px 10px #F0F0F0;
    border-radius: 40px;
        }

        .instalaciones{
            position: relative;
            left: 35px;
            top:10px
        }

        .informacion {
            position:relative;
left: 550px;
bottom: 500px;

            width: 380px;

    
    background: #FBFBFB;
    border: 1px solid #FFFFFF;
    box-sizing: border-box;
    box-shadow: 2px 2px 10px #F0F0F0;
    border-radius: 40px;
        }
        
        .servicios {
            position: relative;
left: 50px;
bottom: 150px;

width: 380px;
height: 347px;

    
    background: #FBFBFB;
    border: 1px solid #FFFFFF;
    box-sizing: border-box;
    box-shadow: 2px 2px 10px #F0F0F0;
    border-radius: 40px;

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
            <h2>${hotel.nombre}</h2>
        </div>
    <div class="cajas">
        <div class="localizacion">
            <p class="title">Localización</p>
            <svg class="line-1" width="323" height="2" viewBox="0 0 323 2" fill="none" xmlns="http://www.w3.org/2000/svg">
                <line x1="1" y1="1" x2="322" y2="1" stroke="#313962" stroke-opacity="0.6" stroke-width="2" stroke-linecap="round" stroke-linejoin="round"/>
            </svg>
            <div class = "bloque">
                <p class="info">País: ${hotel.pais}</p>
                <p class="info">Ciudad: ${hotel.ciudad}</p>
                <p class="info">Dirección: ${hotel.direccion}</p>
            </div>
        </div>
        <div class="descripcion">
            <p class="title">Calificación</p>
            <svg class="line-1" width="323" height="2" viewBox="0 0 323 2" fill="none" xmlns="http://www.w3.org/2000/svg">
                <line x1="1" y1="1" x2="323" y2="1" stroke="#313962" stroke-opacity="0.6" stroke-width="2" stroke-linecap="round" stroke-linejoin="round"/>
            </svg>
            <div class = "bloque">
                <p class="info">Estrellas: ${hotel.estrellas}</p>
                <p class="info">Nota Media: ${nota}</p>
            </div>
        </div>
        <div class="servicios">
            <p class="title">Servicios</p>
            <svg class="line-1" width="323" height="2" viewBox="0 0 323 2" fill="none" xmlns="http://www.w3.org/2000/svg">
                <line x1="1" y1="1" x2="323" y2="1" stroke="#313962" stroke-opacity="0.6" stroke-width="2" stroke-linecap="round" stroke-linejoin="round"/>
            </svg>

            <div class = "bloque">
                <c:forEach var="servicio" items="${servicios}">
                    <p class="title">${servicio.nombre}</p>
                    <div class="instalaciones">
                        <p class="info">Instalaciones: ${servicio.num_instalaciones}</p>
                        <c:if test="${servicio.precio!=null}">
                            <p class="info">Precio: ${servicio.precio}</p>
                        </c:if>
                        <c:if test="${servicio.unidad_medida!=null}">
                            <p class="info">Precio: ${servicio.unidad_medida}</p>
                        </c:if>
                    </div>
                </c:forEach>
            </div>
        </div>
        <div class="informacion">
            <p class="title">Información</p>
            <svg class="line-1" width="323" height="2" viewBox="0 0 323 2" fill="none" xmlns="http://www.w3.org/2000/svg">
                <line x1="1" y1="1" x2="323" y2="1" stroke="#313962" stroke-opacity="0.6" stroke-width="2" stroke-linecap="round" stroke-linejoin="round"/>
            </svg>

            <div class = "bloque">
                    <p class="title">Categorias</p>
                    <div class="instalaciones">
                        <c:forEach var="categoria" items="${categorias}">
                            <p class="info">${categoria.nombre}</p>
                        </c:forEach>
                    </div>
                    <p class="title">Tipo Habitaciones</p>
                    <div class="instalaciones">
                        <c:forEach var="hab" items="${habs}">
                            <p class="info">${hab.nombre_tipo} : ${hab.num_disponibles}</p>
                        </c:forEach>
                    </div>
            </div>
        </div>
    </div>
        <div class ="proveedores">
        <strong class="navbar-brand">Proveedores</strong>
        <table class="table table-striped">
            <thead>
                <tr>
                    <th scope="col">#</th>
                    <th scope="col">Empresa</th>
                    <th scope="col">CIF</th>
                    <th scope="col"></th>
                </tr>
            </thead>
            <tbody>
                <c:forEach var="proveedores" items="${proveedores}">
                    <tr>
                        <th scope="row">${proveedores.id}</th>
                        <td>${proveedores.empresa}</td>
                        <td>${proveedores.CIF}</td>
                        <td>
                            <div class="btn-group">
                                <a class="btn btn-sm btn-outline-secondary"
                                   href="/proveedores/ver-proveedor/${proveedores.id}"
                                   role="button">Ver</a>
                                
                                <a class="btn btn-sm btn-outline-secondary"
                                    href="/proveedores/editar-proveedor/${proveedores.id}"
                                    role="button">Editar</a>

                                <a class="btn btn-sm btn-outline-secondary"
                                   href="/eliminar-proveedor/${proveedores.id}"
                                   role="button">Eliminar</a>
	
                        </td>
                    </tr>
                </c:forEach>

            </tbody>
        </table>
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

    <script src="../js/jquery-3.4.1.slim.min.js"></script>
    <script>
        window.jQuery || document.write('<script src="/docs/4.4/assets/js/vendor/jquery.slim.min.js"><\/script>')
    </script>
    <script src="../js/bootstrap.bundle.min.js"></script>
    <script src="../js/form-validation.js"></script>


</body>

</html>