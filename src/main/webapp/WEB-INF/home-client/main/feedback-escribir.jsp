<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>

<html>
    <head>
		<link rel="stylesheet" type="text/css" href="/css/main.css">
	</head>
    
    <body>
        <h1> Valoración </h1>

        <form method="POST">
            <label>Cabecera</label><br>
            <input type="text" name="cabecera" autocomplete="off">
            <br><br>

            <label>Cuerpo</label><br>   
            <textarea rows="4" cols="50" name="cuerpo"></textarea>
            <br><br>

            <label>Nota</label>
            <input type="number" min="0" max="5" step="0.1" placeholder="4.5" name="nota" autocomplete="off">
            <br><br>

            <input type="submit" value="Enviar">
        </form>
    </body> 
	
</html>