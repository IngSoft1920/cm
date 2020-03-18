<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<html lang="en">

<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <link rel="stylesheet" href="/css/style.css">
    <link rel="stylesheet" href="stylebox.css">
    <title>Proveedores</title>
</head>

<body>
    <div class="container">
        <header>
            <div class="logo-container">
                <h1 class="logo">Proveedores</h1>
            </div>
            <nav>
                <ul class="nav-links">
                    <li><a class="nav-link" href="añadir-proveedores.html">Añadir</a> </li>
                    <li><a class="nav-link" href="#">Editar</a> </li>
                    <li><a class="nav-link" href="#">Eliminar</a> </li>
                </ul>
            </nav>
            <div class="login">
                <a href="inicio">
                    <img src="https://img.icons8.com/ios-glyphs/30/000000/home.png" />
                </a>
            </div>
        </header>

        <main>
            <section class="presentation">
                <div class="introduction">
                    <div class="intro-text">
                        <h1>Lista de Proveedores</h1>
                        <p>
                            Nuestros proveedores que conforman la cadena.
                        </p>
                    </div>



                    <!--Probar con este estilo de cajas para listas-->
                    <div class="container-3">
                        <div class="container-3-box">
                            <h3>Proveedor 1 </h3>
                            <p>Descripcion del proveedor 1 </p>
                            <ul class="box-list">

                                <li><strong>Empresa:</strong> Danone</li>
                                <li><strong>CIF:</strong> A58818501</li>
                            </ul>
                        </div>
                        <div class="container-3-box">
                            <h3>Proveedor 2 </h3>
                            <p>Descripcion del proveedor 2 </p>
                            <ul class="box-list">

                                <li><strong>Empresa:</strong> Hacendado</li>
                                <li><strong>CIF:</strong> A58816701</li>
                            </ul>
                        </div>
                        <div class="container-3-box">
                            <h3>Proveedor 3 </h3>
                            <p>Descripcion del proveedor 3 </p>
                            <ul class="box-list">

                                <li><strong>Empresa:</strong> Nestle</li>
                                <li><strong>CIF:</strong> A58918503</li>
                            </ul>
                        </div>
                        <div class="container-3-box">
                            <h3>Proveedor 4 </h3>
                            <p>Descripcion del proveedor 4 </p>
                            <ul class="box-list">

                                <li><strong>Empresa:</strong> Palacios</li>
                                <li><strong>CIF:</strong> A88918503</li>
                            </ul>
                        </div>
                    </div>

                </div>

            </section>

            <img class="big-circle" src="../img/big-eclipse.svg" alt="" />
            <img class="medium-circle" src="../img/mid-eclipse.svg" alt="" />
            <img class="small-circle" src="../img/small-eclipse.svg" alt="" />
        </main>

    </div>

</body>

</html>