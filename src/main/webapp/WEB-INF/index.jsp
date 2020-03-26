<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>

<!DOCTYPE html>
<html lang="en">

<head>
    <meta charset="UTF-8" />
    <meta name="viewport" content="width=device-width, initial-scale=1.0" />
    <meta http-equiv="X-UA-Compatible" content="ie=edge" />
    <link href="https://fonts.googleapis.com/css?family=Poppins:400,500&display=swap" rel="stylesheet" />
    <link rel="stylesheet" href="/css/style.css" />
    <title>Home Page</title>
</head>

<body>
    <header>
        <div class="logo-container">
            <h1 class="logo">Mi Cadena</h1>
        </div>
        <nav>
            <ul class="nav-links">
                <li><a class="nav-link" href="/hoteles">Hoteles</a>
                </li>
                <li><a class="nav-link" href="/empleados">Empleados</a>
                </li>
                <li><a class="nav-link" href="/proveedores">Proveedores</a>
                </li>
                <li><a class="nav-link" href="/facturacion">Facturación</a></li>
            </ul>
        </nav>
        <div class="login">
            <a href="/login-corp">
                <img src="https://img.icons8.com/metro/26/000000/user-male.png">
            </a>
        </div>
    </header>

    <main>
        <section class="presentation">
            <div class="introduction">
                <div class="intro-text">
                    <h1>La cadena del futuro</h1>
                    <p>
                        La nueva forma de vivir la experiencia hotelera.
                    </p>
                </div>
                <div class="cta">
                    <form action="">
                        <button class="cta-select" type="submit" formaction="login-corp">Iniciar Sesión</button>
                    </form>

                </div>
            </div>
            <div class="cover">
                
                    <img src="./img/white-boats-on-body-of-water-2265876.jpg" alt="world" />
             
            </div>
        </section>

        <img class="big-circle" src="./img/big-eclipse.svg" alt="" />
        <img class="medium-circle" src="./img/mid-eclipse.svg" alt="" />
        <img class="small-circle" src="./img/small-eclipse.svg" alt="" />
    </main>
</body>

</html>