<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>



<html>
    
     <body>
		<style>
			a { text-decoration: none; }
		</style>
	</body>

    <h1>Añadir Empleado:</h1>

    <form method="POST">
        Nombre: <input type="text" name="nombre" autocomplete="off">
        <br><br>
        Apellidos: <input type="text" name="apellidos" autocomplete="off">
        <br><br>
        E-mail: <input type="email" name="email" autocomplete="off">
        <br><br>
        Teléfono: <input type="tel" name="telefono" autocomplete="off">
        <br><br>
        <input type="submit" onclick="alert('Solicitud enviada')">
    </form>
    <button onclick="window.location.href='/home-corp'">Regresar</button>

</html>