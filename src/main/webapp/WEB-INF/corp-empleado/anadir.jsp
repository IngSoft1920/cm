<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>

<html>
    <head>
        <link rel="stylesheet" type="text/css" href="/css/main.css">
    </head>

    <body>
        <h1>Añadir empleado</h1>
        
        <form method="POST">
            <label>Nombre</label>
            <input type="text" name="nombre" autocomplete="off">
            <br><br>

            <label>Apellidos</label>
            <input type="text" name="apellidos" autocomplete="off">
            <br><br>

            <label>Email</label>
            <input type="email" name="email" autocomplete="off">
            <br><br>

            <label>Teléfono</label>
            <input type="text" name="telefono" autocomplete="off">
            <br><br>

            <label>Ocupación</label>
            <input type="text" name="ocupacion" autocomplete="off">
            <br><br>

            <input type="submit" value="Enviar">
        </form>
        <input type="submit" value="Volver" onclick="window.location='/home-corp';" />
    </body>

</html>