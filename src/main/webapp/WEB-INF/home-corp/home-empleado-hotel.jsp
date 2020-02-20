<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>

<html>
	<!--Esto es para evitar que se subrayen los enlaces-->
	<body>
		<style>
			a { text-decoration: none; }
		</style
	</body>
	
	<h1>Introduzca los datos del hotel cuyos empleados le interesan</h1>

	<form method="POST">
        Nombre Hotel: <input type="text" name="nombre" autocomplete="off">
        <br><br>
        Continente: <input type="text" name="continente" autocomplete="off">
        <br><br>
        País: <input type="text" name="pais" autocomplete="off">
        <br><br>
        Dirección hotel: <input type="text" name="direccion" autocomplete="off">
        <br><br>

        <input type="submit" onclick="alert('Solicitud enviada')">
    </form>
    <button onclick="window.location.href='/home-corp'">Regresar</button>

</html>
