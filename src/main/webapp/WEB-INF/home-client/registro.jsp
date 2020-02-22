<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>

<html>
	<!--Esto es para evitar que se subrayen los enlaces-->
    
    <head>
		<link rel="stylesheet" type="text/css" href="/css/main.css">
	</head>
    
    
    <body>
        <h1> Registro </h1>

        <form method="POST">
            <label>Nombre</label>
            <input type="text" name="nombre" autocomplete="off">
            <br><br>

            <label>DNI</label>
            <input type="text" name="DNI" autocomplete="off">
            <br><br>

            <label>Email</label>
            <input type="text" name="email" autocomplete="off">
            <br><br>

            <label>Contraseña</label>
            <input type="password" name="password" autocomplete="off">
            <br><br>

            <input type="submit" value="Registrarme">
        </form>
    </body>

	
</html>