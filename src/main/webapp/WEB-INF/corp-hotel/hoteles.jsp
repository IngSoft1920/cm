<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>


<html lang="en">

<head>
    <meta charset="UTF-8" />
    <meta name="viewport" content="width=device-width, initial-scale=1.0" />
    <meta http-equiv="X-UA-Compatible" content="ie=edge" />
    <link href="https://fonts.googleapis.com/css?family=Poppins:400,500&display=swap" rel="stylesheet" />


    <title>Hoteles</title>



    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">

    <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">
    <meta name="description" content="">
    <meta name="author" content="Mark Otto, Jacob Thornton, and Bootstrap contributors">
    <meta name="generator" content="Jekyll v3.8.6">


    <link rel="canonical" href="https://getbootstrap.com/docs/4.4/examples/album/">

    <!-- Bootstrap core CSS -->
    <link href="/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-Vkoo8x4CGsO3+Hhxv8T/Q5PaXtkKtu6ug5TOeNV6gBiFeWPGFN9MuhOf23Q9Ifjh" crossorigin="anonymous">

    <!-- Favicons -->
    <link rel="apple-touch-icon" href="https://getbootstrap.com/docs/4.4/assets/img/favicons/apple-touch-icon.png" sizes="180x180">
    <link rel="icon" href="https://getbootstrap.com/docs/4.4/assets/img/favicons/favicon-32x32.png" sizes="32x32" type="image/png">
    <link rel="icon" href="https://getbootstrap.com/docs/4.4/assets/img/favicons/favicon-16x16.png" sizes="16x16" type="image/png">
    <link rel="manifest" href="https://getbootstrap.com/docs/4.4/assets/img/favicons/manifest.json">
    <link rel="mask-icon" href="https://getbootstrap.com/docs/4.4/assets/img/favicons/safari-pinned-tab.svg" color="#563d7c">
    <link rel="icon" href="https://getbootstrap.com/docs/4.4/assets/img/favicons/favicon.ico">
    <meta name="msapplication-config" content="/docs/4.4/assets/img/favicons/browserconfig.xml">
    <meta name="theme-color" content="#563d7c">




    <style>
        .bd-placeholder-img {
            font-size: 1.125rem;
            text-anchor: middle;
            -webkit-user-select: none;
            -moz-user-select: none;
            -ms-user-select: none;
            user-select: none;
        }
        
        @media (min-width: 768px) {
            .bd-placeholder-img-lg {
                font-size: 3.5rem;
            }
        }
    </style>
    <!-- Custom styles for this template -->

    <link href="/css/album.css" rel="stylesheet">
</head>

<body>
    <div class="conteiner">


        <header>
            <div class="collapse bg-light" id="navbarHeader">
                <div class="container">
                    <div class="row">
                        <div class="col-sm-8 col-md-7 py-4">
                            <h4 class="text-white">About</h4>
                            <p class="text-muted">Se muestra la lista de hoteles de nuestra cadena.</p>
                            <p class="text-muted">En cada Hotel se pueden hacer tres acciones: ver la informacion del Hotel, editar la informacion y eliminar el Hotel. q</p>
                        </div>
                        <div class="col-sm-4 offset-md-1 py-4">

                            <ul class="list-unstyled">
                                <li><a href="/anadir-hotel" class="text-black">Añadir Hotel</a>
                                </li>
                                <li><a href="/inicio" class="text-black">Inicio</a>
                                </li>
                            </ul>
                        </div>
                    </div>

                </div>
            </div>
            <div class="navbar navbar-light bg-light shadow-sm">
                <div class="container d-flex align-content-start">
                    <a href="" class="navbar-brand ">

                        <strong>Hoteles</strong>
                    </a>
                    <a class="navbar-nav" href="/inicio">Inicio</a>
                    <a class="navbar-nav" href="/anadir-hotel">Añadir Hotel</a>

                    <button class="navbar-toggler" type="button" data-toggle="collapse" data-target="#navbarHeader" aria-controls="navbarHeader" aria-expanded="false" aria-label="Toggle navigation">
                        <span class="navbar-toggler-icon"></span>
                    </button>

                </div>
            </div>
        </header>


        <main role="main">

            <!--Probar con este estilo de cajas para listas-->
            <div class="album py-5 bg-light">
                <div class="container">

                    <div class="row">
                        <div class="col-md-4">
                            <div class="card mb-4 shadow-sm">
                                <img src="../img/white-boats-on-body-of-water-2265876.jpg" class="img-fluid" alt="Hotel Cadiz Playa">
                                <div class="card-body">
                                    <!--Este texto lo utilizaremos como descripcion del Hotel-->
                                    <p class="card-text">Hotel Cadiz Playa. Hotel 5 estrellas.Con 4 restaurantes.
                                    </p>
                                    <div class="d-flex justify-content-between align-items-center">
                                        <div class="btn-group">
                                            <a class="btn btn-sm btn-outline-secondary" href="/ver-hotel" role="button">Ver</a>
                                            <a class="btn btn-sm btn-outline-secondary" href="/editar-hotel" role="button">Editar</a>

                                            <!-- Button trigger modal -->
                                            <button type="button" class="btn btn-sm btn-outline-secondary" data-toggle="modal" data-target="#exampleModal">
                                                Eliminar
                                            </button>
                                            <!-- Modal -->
                                            <div class="modal fade" id="exampleModal" tabindex="-1" role="dialog" aria-labelledby="exampleModalLabel" aria-hidden="true">
                                                <div class="modal-dialog" role="document">
                                                    <div class="modal-content">
                                                        <div class="modal-header">
                                                            <h5 class="modal-title" id="exampleModalLabel">Eliminar Hotel
                                                            </h5>
                                                            <button type="button" class="close" data-dismiss="modal" aria-label="Close">
                                                                <span aria-hidden="true">&times;</span>
                                                            </button>
                                                        </div>
                                                        <div class="modal-body">
                                                            Esta seguro que desea eliminar el hotel?
                                                        </div>
                                                        <div class="modal-footer">
                                                            <button type="button" class="btn btn-secondary" data-dismiss="modal">Cancelar</button>
                                                            <button type="button" class="btn btn-primary">Eliminar</button>
                                                        </div>
                                                    </div>
                                                </div>
                                            </div>
                                        </div>
                                        <small class="text-muted">9 meses</small>
                                    </div>
                                </div>
                            </div>
                        </div>
                        <div class="col-md-4">
                            <div class="card mb-4 shadow-sm">
                                <img src="../img/white-boats-on-body-of-water-2265876.jpg" class="img-fluid" alt="Hotel Malaga">
                                <div class="card-body">
                                    <!--Este texto lo utilizaremos como descripcion del Hotel-->
                                    <p class="card-text">This is a wider card with supporting text below as a natural lead-in to additional content. This content is a little bit longer.
                                    </p>
                                    <div class="d-flex justify-content-between align-items-center">
                                        <div class="btn-group">
                                            <button type="button" class="btn btn-sm btn-outline-secondary">Ver</button>
                                            <button type="button" class="btn btn-sm btn-outline-secondary">Editar</button>
                                            <button type="button" class="btn btn-sm btn-outline-secondary">Eliminar</button>
                                        </div>
                                        <small class="text-muted">3 horas</small>
                                    </div>
                                </div>
                            </div>
                        </div>
                        <div class="col-md-4">
                            <div class="card mb-4 shadow-sm">
                                <svg class="bd-placeholder-img card-img-top" width="100%" height="225" xmlns="http://www.w3.org/2000/svg" preserveAspectRatio="xMidYMid slice" focusable="false" role="img" aria-label="Placeholder: Thumbnail">
                                    <title>Placeholder</title>
                                    <rect width="100%" height="100%" fill="#55595c"></rect><text x="50%" y="50%"
                                        fill="#eceeef" dy=".3em">Barcelona</text>
                                </svg>
                                <div class="card-body">
                                    <!--Este texto lo utilizaremos como descripcion del Hotel-->
                                    <p class="card-text">This is a wider card with supporting text below as a natural lead-in to additional content. This content is a little bit longer.
                                    </p>
                                    <div class="d-flex justify-content-between align-items-center">
                                        <div class="btn-group">
                                            <button type="button" class="btn btn-sm btn-outline-secondary">Ver</button>
                                            <button type="button" class="btn btn-sm btn-outline-secondary">Editar</button>
                                            <button type="button" class="btn btn-sm btn-outline-secondary">Eliminar</button>
                                        </div>
                                        <small class="text-muted">55 mins</small>
                                    </div>
                                </div>
                            </div>
                        </div>

                        <div class="col-md-4">
                            <div class="card mb-4 shadow-sm">
                                <svg class="bd-placeholder-img card-img-top" width="100%" height="225" xmlns="http://www.w3.org/2000/svg" preserveAspectRatio="xMidYMid slice" focusable="false" role="img" aria-label="Placeholder: Thumbnail">
                                    <title>Placeholder</title>
                                    <rect width="100%" height="100%" fill="#55595c"></rect><text x="50%" y="50%"
                                        fill="#eceeef" dy=".3em">Milan</text>
                                </svg>
                                <div class="card-body">
                                    <!--Este texto lo utilizaremos como descripcion del Hotel-->
                                    <p class="card-text">This is a wider card with supporting text below as a natural lead-in to additional content. This content is a little bit longer.
                                    </p>
                                    <div class="d-flex justify-content-between align-items-center">
                                        <div class="btn-group">
                                            <button type="button" class="btn btn-sm btn-outline-secondary">Ver</button>
                                            <button type="button" class="btn btn-sm btn-outline-secondary">Editar</button>
                                            <button type="button" class="btn btn-sm btn-outline-secondary">Eliminar</button>
                                        </div>
                                        <small class="text-muted">5 dias</small>
                                    </div>
                                </div>
                            </div>
                        </div>
                        <div class="col-md-4">
                            <div class="card mb-4 shadow-sm">
                                <svg class="bd-placeholder-img card-img-top" width="100%" height="225" xmlns="http://www.w3.org/2000/svg" preserveAspectRatio="xMidYMid slice" focusable="false" role="img" aria-label="Placeholder: Thumbnail">
                                    <title>Placeholder</title>
                                    <rect width="100%" height="100%" fill="#55595c"></rect><text x="50%" y="50%"
                                        fill="#eceeef" dy=".3em">Milan</text>
                                </svg>
                                <div class="card-body">
                                    <!--Este texto lo utilizaremos como descripcion del Hotel-->
                                    <p class="card-text">This is a wider card with supporting text below as a natural lead-in to additional content. This content is a little bit longer.
                                    </p>
                                    <div class="d-flex justify-content-between align-items-center">
                                        <div class="btn-group">
                                            <button type="button" class="btn btn-sm btn-outline-secondary">Ver</button>
                                            <button type="button" class="btn btn-sm btn-outline-secondary">Editar</button>
                                            <button type="button" class="btn btn-sm btn-outline-secondary">Eliminar</button>
                                        </div>
                                        <small class="text-muted">3 semanas</small>
                                    </div>
                                </div>
                            </div>
                        </div>
                        <div class="col-md-4">
                            <div class="card mb-4 shadow-sm">
                                <svg class="bd-placeholder-img card-img-top" width="100%" height="225" xmlns="http://www.w3.org/2000/svg" preserveAspectRatio="xMidYMid slice" focusable="false" role="img" aria-label="Placeholder: Thumbnail">
                                    <title>Placeholder</title>
                                    <rect width="100%" height="100%" fill="#55595c"></rect><text x="50%" y="50%"
                                        fill="#eceeef" dy=".3em">Varadero</text>
                                </svg>
                                <div class="card-body">
                                    <!--Este texto lo utilizaremos como descripcion del Hotel-->
                                    <p class="card-text">This is a wider card with supporting text below as a natural lead-in to additional content. This content is a little bit longer.
                                    </p>
                                    <div class="d-flex justify-content-between align-items-center">
                                        <div class="btn-group">
                                            <button type="button" class="btn btn-sm btn-outline-secondary">Ver</button>
                                            <button type="button" class="btn btn-sm btn-outline-secondary">Editar</button>
                                            <button type="button" class="btn btn-sm btn-outline-secondary">Eliminar</button>
                                        </div>
                                        <small class="text-muted">2 anos</small>
                                    </div>
                                </div>
                            </div>
                        </div>

                        <div class="col-md-4">
                            <div class="card mb-4 shadow-sm">
                                <svg class="bd-placeholder-img card-img-top" width="100%" height="225" xmlns="http://www.w3.org/2000/svg" preserveAspectRatio="xMidYMid slice" focusable="false" role="img" aria-label="Placeholder: Thumbnail">
                                    <title>Placeholder</title>
                                    <rect width="100%" height="100%" fill="#55595c"></rect><text x="50%" y="50%"
                                        fill="#eceeef" dy=".3em">Miami</text>
                                </svg>
                                <div class="card-body">
                                    <!--Este texto lo utilizaremos como descripcion del Hotel-->
                                    <p class="card-text">This is a wider card with supporting text below as a natural lead-in to additional content. This content is a little bit longer.
                                    </p>
                                    <div class="d-flex justify-content-between align-items-center">
                                        <div class="btn-group">
                                            <button type="button" class="btn btn-sm btn-outline-secondary">Ver</button>
                                            <button type="button" class="btn btn-sm btn-outline-secondary">Editar</button>
                                            <button type="button" class="btn btn-sm btn-outline-secondary">Eliminar</button>
                                        </div>
                                        <small class="text-muted">1 mes</small>
                                    </div>
                                </div>
                            </div>
                        </div>
                        <div class="col-md-4">
                            <div class="card mb-4 shadow-sm">
                                <svg class="bd-placeholder-img card-img-top" width="100%" height="225" xmlns="http://www.w3.org/2000/svg" preserveAspectRatio="xMidYMid slice" focusable="false" role="img" aria-label="Placeholder: Thumbnail">
                                    <title>Placeholder</title>
                                    <rect width="100%" height="100%" fill="#55595c"></rect><text x="50%" y="50%"
                                        fill="#eceeef" dy=".3em">Paris</text>
                                </svg>
                                <div class="card-body">
                                    <!--Este texto lo utilizaremos como descripcion del Hotel-->
                                    <p class="card-text">This is a wider card with supporting text below as a natural lead-in to additional content. This content is a little bit longer.
                                    </p>
                                    <div class="d-flex justify-content-between align-items-center">
                                        <div class="btn-group">
                                            <button type="button" class="btn btn-sm btn-outline-secondary">Ver</button>
                                            <button type="button" class="btn btn-sm btn-outline-secondary">Editar</button>
                                            <button type="button" class="btn btn-sm btn-outline-secondary">Eliminar</button>
                                        </div>
                                        <small class="text-muted">2 meses</small>
                                    </div>
                                </div>
                            </div>
                        </div>
                        <div class="col-md-4">
                            <div class="card mb-4 shadow-sm">
                                <!--Este texto lo utilizaremos como descripcion del Hotel-->
                                <svg class="bd-placeholder-img card-img-top" width="100%" height="225" xmlns="http://www.w3.org/2000/svg" preserveAspectRatio="xMidYMid slice" focusable="false" role="img" aria-label="Placeholder: Thumbnail">
                                    <title>Placeholder</title>
                                    <rect width="100%" height="100%" fill="#55595c"></rect><text x="50%" y="50%"
                                        fill="#eceeef" dy=".3em">Rio de Janeiro</text>
                                </svg>
                                <div class="card-body">
                                    <p class="card-text">This is a wider card with supporting text below as a natural lead-in to additional content. This content is a little bit longer.
                                    </p>
                                    <div class="d-flex justify-content-between align-items-center">
                                        <div class="btn-group">
                                            <button type="button" class="btn btn-sm btn-outline-secondary">Ver</button>
                                            <button type="button" class="btn btn-sm btn-outline-secondary">Editar</button>
                                            <button type="button" class="btn btn-sm btn-outline-secondary">Eliminar</button>
                                        </div>
                                        <small class="text-muted">15 mins</small>
                                    </div>
                                </div>
                            </div>
                        </div>
                    </div>
                </div>
            </div>

        </main>

        <footer class="text-muted">
            <div class="container">
                <p class="float-right">
                    <a href="">Back to top</a>
                </p>
                <p>Ejemplo de Lista de Hoteles de nuestra Cadena.</p>
            </div>
        </footer>
        <script src="/js/jquery-3.4.1.slim.min.js" integrity="sha384-J6qa4849blE2+poT4WnyKhv5vZF5SrPo0iEjwBvKU7imGFAV0wwj1yYfoRSJoZ+n" crossorigin="anonymous"></script>
        <script>
            window.jQuery || document.write('<script src="/docs/4.4/assets/js/vendor/jquery.slim.min.js"><\/script>')
        </script>
        <script src="/js/bootstrap.bundle.min.js" integrity="sha384-6khuMg9gaYr5AxOqhkVIODVIvm9ynTT5J4V1cfthmT+emCG6yVmEZsRHdxlotUnm" crossorigin="anonymous"></script>


    </div>

</body>


</html>