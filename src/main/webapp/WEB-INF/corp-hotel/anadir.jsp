<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>

<html>
    <head>
        <link rel="stylesheet" type="text/css" href="/css/main.css">
    </head>

    <body>
        <h1>Añadir Hotel</h1>
        
        <form method="POST">
            <label>Nombre</label>
            <input type="text" name="nombre" autocomplete="off">
            <br><br>

            <label>Continente</label>
            <input type="text" name="continente" autocomplete="off">
            <br><br>

            <label>País</label>
            <input type="text" name="pais" autocomplete="off">
            <br><br>

            <label>Ciudad</label>
            <input type="text" name="ciudad" autocomplete="off">
            <br><br>

            <label>Direccion</label>
            <input type="text" name="direccion" autocomplete="off">
            <br><br>

            <input type="submit" value="Enviar">
        </form>
        <input type="submit" value="Volver" onclick="window.location='/hotel';" />
    </body>

</html>