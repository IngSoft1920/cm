<%@ page language="java" contentType="text/html; charset=utf-8"
	pageEncoding="utf-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<!DOCTYPE html>
<!-- saved from url=(0051)https://getbootstrap.com/docs/4.4/examples/sign-in/ -->
<html lang="en">

<head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">

    <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">
    <meta name="description" content="">
    <meta name="author" content="Mark Otto, Jacob Thornton, and Bootstrap contributors">
    <meta name="generator" content="Jekyll v3.8.6">
    <title>Login proveedores</title>

    <link rel="canonical" href="https://getbootstrap.com/docs/4.4/examples/sign-in/">

    <!-- Bootstrap core CSS -->
    <link href="/css/bootstrap.min.css" rel="stylesheet"  crossorigin="anonymous">

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
    <link href="/css/signin.css" rel="stylesheet">
</head>

<body class="text-center">
    <form class="form-signin" method="POST">
        <img class="mb-4" src="/img/bootstrap-solid.svg" alt="" width="100" height="100">
        <h1 class="h3 mb-3 font-weight-normal">Iniciar sesión </h1>
        <h1 class="h5 mb-3 font-weight-normal">Proveedor oficial</h1>

        <label for="user" class="sr-only">Usuario</label>
        <input class="form-control" type="text" id="user" name="usuario" placeholder="Correo Electrónico" required autofocus>
        
        <label for="inputPassword" class="sr-only">Contraseña</label>
        <input class="form-control" type="password" id="inputPassword" name="password" placeholder="Contraseña" required>
        
        <button class="btn btn-lg btn-primary btn-block" type="submit" >Acceder</button>
        
       
        <p class="mt-5 mb-3 text-muted">© 2020 Company Management - UPM</p>
    </form>

</body>

</html>