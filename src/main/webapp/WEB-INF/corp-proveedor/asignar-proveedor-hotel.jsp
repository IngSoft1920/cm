<%@ page language="java" contentType="text/html; charset=utf-8"
	pageEncoding="utf-8"%>
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
    <title>Asignar Proveedor Hotel</title>

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
            <h2>Asignar Proveedor a Hotel</h2>
           
        </div>

        <div class="row center">
            <div class="col-md-18 order-md-1">
                <form class="needs-validation" method="POST" novalidate>
                     <div class="mb-3">
                    <label for="nombre">Empresa</label>
                    <input type="text" class="form-control" value="${proveedor.empresa}" readonly>
                </div>

                <div class="mb-3">
                    <label>CIF</label>
                    <input type="text" class="form-control" value="${proveedor.CIF}" readonly>
                </div>

                     <div class="row">
                     
                      <c:forEach var="producto" items="${productos}"> 

                        <div class="col-md-3 mb-3">
                            <label>Producto</label>
                            
                            
                            <div class="form-check">
                                <input class="form-check-input" type="checkbox" value="${producto.id}" name="productosIDs">
                                <label>
                                    ${producto.nombre}
                                </label>
                            </div>

                        </div>

                        <div class="col-md-4 mb-4">
                            <label for="zip">Precio</label>
                            <input type="number" class="form-control" id="zip" placeholder="" name="precio" required="">

                        </div>

                        <div class="col-md-4 mb-4">
                            <label for="zip">Unidad de Medida</label>
                            <input type="text" class="form-control" id="zip" placeholder="" name="unidadMedida" required="">

                        </div>
                        
                        </c:forEach>



                    </div>
                    
                    
                    <hr class="mb-4">
                    <button class="btn btn-primary btn-lg btn-block" type="submit">Asignar</button>
                </form>
                
                
            </div>
        </div>


        <footer class="my-5 pt-5 text-muted text-center text-small">
            <p class="mb-1">© 2020 Company Management - UPM</p>
            <div class="container">
                <p class="float-left">
                    <a href="/proveedores">Volver a proveedores</a>
                </p>
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