<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>

<!DOCTYPE html>
<html lang="en">

<head>
    <meta charset="UTF-8" />
    <meta name="viewport" content="width=device-width, initial-scale=1.0" />
    <meta http-equiv="X-UA-Compatible" content="ie=edge" />
    <link href="https://fonts.googleapis.com/css?family=Poppins:400,500&display=swap" rel="stylesheet" />
    <link rel="stylesheet" href="/css/style.css" />

    <title>Inicio</title>


<!-- Bootstrap core CSS -->
<link href="/css/bootstrap.min.css" rel="stylesheet">

<!-- Favicons -->
<link rel="apple-touch-icon"
	href="https://getbootstrap.com/docs/4.4/assets/img/favicons/apple-touch-icon.png"
	sizes="180x180">
<link rel="icon"
	href="https://getbootstrap.com/docs/4.4/assets/img/favicons/favicon-32x32.png"
	sizes="32x32" type="image/png">
<link rel="icon"
	href="https://getbootstrap.com/docs/4.4/assets/img/favicons/favicon-16x16.png"
	sizes="16x16" type="image/png">
<link rel="manifest"
	href="https://getbootstrap.com/docs/4.4/assets/img/favicons/manifest.json">
<link rel="mask-icon"
	href="https://getbootstrap.com/docs/4.4/assets/img/favicons/safari-pinned-tab.svg"
	color="#563d7c">
<link rel="icon"
	href="https://getbootstrap.com/docs/4.4/assets/img/favicons/favicon.ico">
<meta name="msapplication-config"
	content="/docs/4.4/assets/img/favicons/browserconfig.xml">
<meta name="theme-color" content="#563d7c">


    
<link href="/css/pricing.css" rel="stylesheet">  
    
</head>

<body>
    <header>
        <div class="logo-container">
            <h1 class="logo">Mi Cadena</h1>
        </div>
        <nav>
            <ul class="nav-links">
                <li><a class="nav-link" href="/hoteles">Hoteles</a></li>
                <li><a class="nav-link" href="/select-hoteles">Empleados</a></li>
                <li><a class="nav-link" href="/proveedores">Proveedores</a></li>
                <li><a class="nav-link" href="/beneficio0">Facturación</a></li>
                <li><a class="nav-link" href="/productos"> Productos </a></li>
                <li><a class="nav-link" href="/configuracion"> Configuración </a></li>
            </ul>
        </nav>  
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
            </div>
            
           <div class="card mb-4 box-shadow">
             <div class="card-header">
              <h4 class="my-0 font-weight-normal">Balance económico</h4>
             </div>
             <div class="card-body">
              <h1 class="card-title pricing-card-title">${balance}€<small class="text-muted"></small></h1>
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