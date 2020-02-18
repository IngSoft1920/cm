<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>



<html>
    
     <body>
		<style>
			a { text-decoration: none; }
		</style>
	</body>

    <h1>Añadir Proveedor:</h1>

    <form method="POST">
        Empresa: <input type="text" name="empresa" autocomplete="off">
        <br><br>
        Producto: <input type="text" name="producto" autocomplete="off">
        <br><br>
        <input type="submit" onclick="alert('Solicitud enviada')">
    </form>
    <button onclick="window.location.href='/home-corp'">Regresar</button>

</html>