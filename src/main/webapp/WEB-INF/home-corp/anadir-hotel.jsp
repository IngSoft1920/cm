<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>

<html>
    
     <body>
		<style>
			a { text-decoration: none; }
		</style
	</body>

    <h1>Añadir Hotel:</h1>

    <form method="POST">
        Nombre Hotel: <input type="text" name autocomplete="off"="nombreHotel">
        <br><br>
        Ubicación Hotel: <input type="text" name="ubicacionHotel" autocomplete="off">
        <br><br>
        <input type="submit">
    </form>

    <button onclick="window.location.href='/home-corp'">Regresar</button>
</html>