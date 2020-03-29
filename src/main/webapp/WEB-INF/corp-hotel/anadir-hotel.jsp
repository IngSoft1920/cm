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
    <title>Añadir Hotel</title>

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
            <h2>Añadir Hotel</h2>
            <p class="lead">Añadir hotel a la cadena.</p>
        </div>

        <div class="row center">

            <div class="col-md-18 order-md-1">

                <form class="needs-validation" novalidate="">
                    <div class="mb-3">
                        <label for="nombre">Nombre</label>
                        <input type="text" class="form-control" id="nombre" placeholder="Nombre" value="${modeloHotel.hotel.nombre}" required="">
                        <div class="invalid-feedback">
                            El nombre del hotel es obligatorio
                        </div>
                    </div>

                    <div class="mb-3">
                        <label for="continente">Continente</label>
                        <div class="input-group">
                            <input type="text" class="form-control" id="continente" placeholder="Continente" value="${modeloHotel.hotel.continente}" required="">
                            <div class="invalid-feedback" style="width: 100%;">
                                Your continente is required.
                            </div>
                        </div>
                    </div>

                    <div class="mb-3">
                        <label for="pais">Pais </label>
                        <input type="text" class="form-control" id="pais" placeholder="Pais" value="${modeloHotel.hotel.pais}">
                        <div class="invalid-feedback">
                            Please enter a valid Pais.
                        </div>
                    </div>

                    <div class="mb-3">
                        <label for="ciudad">Ciudad</label>
                        <input type="text" class="form-control" id="ciudad" placeholder="" required="" value="${modeloHotel.hotel.ciudad}">
                        <div class="invalid-feedback">
                            Please enter your city.
                        </div>
                    </div>

                    <div class="mb-3">
                        <label for="direccion">Direccion<span class="text-muted">(Optional)</span></label>
                        <input type="text" class="form-control" id="direccion" placeholder="Calle/Avenida" value="${modeloHotel.hotel.direccion}">
                        <div class="invalid-feedback">
                            Please enter your Direccion.
                        </div>
                    </div>

                    <div class="row">
                    
                    <!-- Rellenar los servicios desde bd con un for -->

                        <div class="col-md-5 mb-3">
                            <label for="country">Servicios</label>
                            
                            <c:forEach var="servicios" items="${modeloHotel.hotel_servicio.servicio_id}">
                            <div class="form-check">
                                <input class="form-check-input" type="checkbox" value="" id="defaultCheck1">
                                <label class="form-check-label" for="defaultCheck1">
                                    Restuaracion
                                </label>
                            </div>
                            </c:forEach>
                            

                            <div class="form-check">
                                <input class="form-check-input" type="checkbox" value="" id="defaultCheck1">
                                <label class="form-check-label" for="defaultCheck1" data-toggle="modal" data-target="#exampleModal">
                                    Otros
                                </label>

                                <!-- Modal para servicios -->
                                <div class="modal fade" id="exampleModal" tabindex="-1" role="dialog" aria-labelledby="exampleModalLabel" aria-hidden="true">
                                    <div class="modal-dialog" role="document">
                                        <div class="modal-content">
                                            <div class="modal-header">
                                                <h5 class="modal-title" id="exampleModalLabel">Añadir Servicio
                                                </h5>
                                                <button type="button" class="close" data-dismiss="modal" aria-label="Close">
                                                    <span aria-hidden="true">&times;</span>
                                                </button>
                                            </div>
                                            <div class="modal-body">
                                                Necesitas añadir otro servicio que no se encuentra en la lista?
                                            </div>
                                            <div class="modal-footer">
                                                <button type="button" class="btn btn-secondary" data-dismiss="modal">Cancelar</button>
                                                <a class="btn btn-primary" href="/anadir-hotel/anadir-servicios" role="button">Añadir</a>
                                            </div>
                                        </div>
                                    </div>
                                </div>

                            </div>

                            <div class="invalid-feedback">
                                Please select a valid servicio.
                            </div>
                        </div>
                    </div>

                    <div class="row">
                        <div class="col-md-6 mb-6">
                            <label for="zip">Habitaciones "tipo"</label>
                            <input type="number" class="form-control" id="zip" placeholder="" required="">
                            <div class="invalid-feedback">
                                Numero de habitaciones "tipo"
                            </div>
                        </div>
                       
                  

                        <div class="col-md-5 mb-5">
                            <label for="zip">Estrellas</label>
                            <input type="number" min="0" max="6" class="form-control" id="zip" placeholder="" required="">
                            <div class="invalid-feedback">
                                Numero de estrellas requeridas.
                            </div>
                        </div>
                    </div>

                    <div class="row">
                        <div class="col-md-5 mb-3">
                            <label for="country">Categorias</label>
                            <div class="form-check">
                                <input class="form-check-input" type="checkbox" value="" id="defaultCheck1">
                                <label class="form-check-label" for="defaultCheck1">
                                    Family
                                </label>
                            </div>
                           
                            <div class="form-check">
                                <input class="form-check-input" type="checkbox" value="" id="defaultCheck1">
                                <label class="form-check-label" for="defaultCheck1" data-toggle="modal" data-target="#exampleModal2">
                                    Otros
                                </label>

                                <!-- Modal para categoria -->
                                <div class="modal fade" id="exampleModal2" tabindex="-1" role="dialog" aria-labelledby="exampleModalLabel" aria-hidden="true">
                                    <div class="modal-dialog" role="document">
                                        <div class="modal-content">
                                            <div class="modal-header">
                                                <h5 class="modal-title" id="exampleModalLabel">Añadir Categoria
                                                </h5>
                                                <button type="button" class="close" data-dismiss="modal" aria-label="Close">
                                                    <span aria-hidden="true">&times;</span>
                                                </button>
                                            </div>
                                            <div class="modal-body">
                                                Necesitas añadir otra categoria que no se encuentra en la lista?
                                            </div>
                                            <div class="modal-footer">
                                                <button type="button" class="btn btn-secondary" data-dismiss="modal">Cancelar</button>
                                                <a class="btn btn-primary" href="/anadir-hotel/anadir-categoria" role="button">Añadir</a>
                                            </div>
                                        </div>
                                    </div>
                                </div>

                            </div>

                            <div class="invalid-feedback">
                                Please select a valid categoria.
                            </div>
                        </div>
                    </div>

                    <hr class="mb-4">

                    <button class="btn btn-primary btn-lg btn-block" type="submit">Añadir</button>
                </form>
            </div>
        </div>


        <footer class="my-5 pt-5 text-muted text-center text-small">
            <p class="mb-1">© 2020 Company Name</p>
            <ul class="list-inline">
                <li class="list-inline-item"><a href="https://getbootstrap.com/docs/4.4/examples/checkout/#">Privacy</a>
                </li>
                <li class="list-inline-item"><a href="https://getbootstrap.com/docs/4.4/examples/checkout/#">Terms</a>
                </li>
                <li class="list-inline-item"><a href="https://getbootstrap.com/docs/4.4/examples/checkout/#">Support</a>
                </li>
            </ul>
            <div class="container">
                <p class="float-right">
                    <a href="/inicio">Back to Home</a>
                </p>
                <p class="float-left">
                    <a href="/hoteles">Back to Hoteles</a>
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