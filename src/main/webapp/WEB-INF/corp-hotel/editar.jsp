<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>

<html>
    <head>
        <link rel="stylesheet" type="text/css" href="/css/main.css">
    </head>

    <body>
        <h1>Editar Hotel</h1>
        
        <form method="POST">
            <label>Nombre</label>
            <input type="text" name="nombre" autocomplete="off">
            <br><br>

            <input type="submit" value="Enviar">
        </form>
        <input type="submit" value="Volver" onclick="window.location='/hotel';" />
    </body>

</html>