<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>

<html>
    <head>
		<link rel="stylesheet" type="text/css" href="/css/main.css">
	</head>
    
    <body>
        <h1> Introduce tus preferencias </h1>

        <form method="POST">
            <label>Continente</label>
            <input type="text" name="continente" autocomplete="off">
            <br><br>

            <label>País</label>
            <input type="text" name="pais" autocomplete="off">
            <br><br>

            <label>Ciudad</label>
            <input type="text" name="ciudad" autocomplete="off">
            <br><br>

            <label>Fecha entrada</label>
            <input type="date" name="fecha_entrada" autocomplete="off">
            <br><br>

            <label>Fecha salida</label>
            <input type="date" name="fecha_salida" autocomplete="off">
            <br><br>

            <input type="submit" value="Buscar">
        </form>
    </body> 
	
</html>