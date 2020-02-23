<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>

<html>
    <head>
        <link rel="stylesheet" type="text/css" href="/css/main.css">
    </head>

    <body>
        <h1>Añadir Proveedor</h1>
        
        <form method="POST">
            <label>Empresa</label>
            <input type="text" name="empresa" autocomplete="off">
            <br><br>

            <label>Producto</label>
            <input type="text" name="producto" autocomplete="off">
            <br><br>

            <input type="submit" value="Enviar">
        </form>
        <input type="submit" value="Volver" onclick="window.location='/home-corp';" />
    </body>

</html>