<!DOCTYPE html>
<!-- saved from url=(0052)https://getbootstrap.com/docs/4.4/examples/checkout/ -->
<html lang="en">

<head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">

    <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">
    <meta name="description" content="">
    <meta name="author" content="Mark Otto, Jacob Thornton, and Bootstrap contributors">
    <meta name="generator" content="Jekyll v3.8.6">
    <title>Añadir Empleado</title>

    <link rel="canonical" href="https://getbootstrap.com/docs/4.4/examples/checkout/">

    <!-- Bootstrap core CSS -->
    <link href="../css/bootstrap.min.css" rel="stylesheet" integrity="sha384-Vkoo8x4CGsO3+Hhxv8T/Q5PaXtkKtu6ug5TOeNV6gBiFeWPGFN9MuhOf23Q9Ifjh" crossorigin="anonymous">

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
    <link href="../css/form-validation.css" rel="stylesheet">
</head>

<body class="bg-light">
    <div class="container">
        <div class="py-5 text-center">
            <h2>Añadir Empleado</h2>
            <p class="lead">Añadir empleado a la cadena.</p>
        </div>

        <div class="row center">

            <div class="col-md-18 order-md-1">

                <form class="needs-validation" novalidate="">
                    <div class="mb-3">
                        <label for="firstName">Nombre</label>
                        <input type="text" class="form-control" id="firstName" placeholder="Nombre" value="" required="">
                        <div class="invalid-feedback">
                            El nombre del empleado es obligatorio
                        </div>
                    </div>

                    <div class="mb-3">
                        <label for="firstName">Apellidos</label>
                        <input type="text" class="form-control" id="firstName" placeholder="Nombre" value="" required="">
                        <div class="invalid-feedback">
                            El apellidos del empleado son obligatorio
                        </div>
                    </div>

                    <div class="mb-3">
                        <label for="email">Email </label>
                        <input type="email" class="form-control" id="email" placeholder="you@example.com">
                        <div class="invalid-feedback">
                            Please enter a valid email address.
                        </div>
                    </div>

                    <div class="mb-3">
                        <label for="address2">Telefono<span class="text-muted">(Optional)</span></label>
                        <input type="text" class="form-control" id="address2" placeholder="Apartment or suite">

                    </div>

                    <div class="row">

                        <div class="col-md-5 mb-3">
                            <label for="country">Profesion</label>
                            <div class="form-check">
                                <input class="form-check-input" type="checkbox" value="" id="defaultCheck1">
                                <label class="form-check-label" for="defaultCheck1">
                                    Camarero
                                </label>
                            </div>
                            <div class="form-check">
                                <input class="form-check-input" type="checkbox" value="" id="defaultCheck1">
                                <label class="form-check-label" for="defaultCheck1">
                                    Cocinero
                                </label>
                            </div>
                            <div class="form-check">
                                <input class="form-check-input" type="checkbox" value="" id="defaultCheck1">
                                <label class="form-check-label" for="defaultCheck1">
                                    Bartender
                                </label>
                            </div>
                            <div class="form-check">
                                <input class="form-check-input" type="checkbox" value="" id="defaultCheck1">
                                <label class="form-check-label" for="defaultCheck1">
                                    Seguridad
                                </label>
                            </div>

                            <div class="form-check">
                                <input class="form-check-input" type="checkbox" value="" id="defaultCheck1">
                                <label class="form-check-label" for="defaultCheck1" data-toggle="modal" data-target="#exampleModal">
                                    Otros
                                </label>

                                <!-- Modal -->
                                <div class="modal fade" id="exampleModal" tabindex="-1" role="dialog" aria-labelledby="exampleModalLabel" aria-hidden="true">
                                    <div class="modal-dialog" role="document">
                                        <div class="modal-content">
                                            <div class="modal-header">
                                                <h5 class="modal-title" id="exampleModalLabel">Añadir Profesion
                                                </h5>
                                                <button type="button" class="close" data-dismiss="modal" aria-label="Close">
                                                    <span aria-hidden="true">&times;</span>
                                                </button>
                                            </div>
                                            <div class="modal-body">
                                                Necesitas añadir otra profesion que no se encuentra en la lista?
                                            </div>
                                            <div class="modal-footer">
                                                <button type="button" class="btn btn-secondary" data-dismiss="modal">Cancelar</button>
                                                <a class="btn btn-primary" href="anadir-profesion.html" role="button">Añadir</a>
                                            </div>
                                        </div>
                                    </div>
                                </div>

                            </div>
                        </div>

                    </div>
                    <hr class="mb-4">

                    <button class="btn btn-primary btn-lg btn-block" type="submit">Añadir</button>
                </form>
            </div>
        </div>


        <footer class="my-5 pt-5 text-muted text-center text-small">
            <p class="mb-1">© 2020 Company Name</p>
            <ul class="list-inline">
                <li class="list-inline-item"><a href="https://getbootstrap.com/docs/4.4/examples/checkout/#">Privacy</a>
                </li>
                <li class="list-inline-item"><a href="https://getbootstrap.com/docs/4.4/examples/checkout/#">Terms</a>
                </li>
                <li class="list-inline-item"><a href="https://getbootstrap.com/docs/4.4/examples/checkout/#">Support</a>
                </li>
            </ul>
            <div class="container">
                <p class="float-right">
                    <a href="/index.html">Back to Home</a>
                </p>

            </div>
        </footer>
    </div>
    <script src="../js/jquery-3.4.1.slim.min.js" integrity="sha384-J6qa4849blE2+poT4WnyKhv5vZF5SrPo0iEjwBvKU7imGFAV0wwj1yYfoRSJoZ+n" crossorigin="anonymous"></script>
    <script>
        window.jQuery || document.write('<script src="/docs/4.4/assets/js/vendor/jquery.slim.min.js"><\/script>')
    </script>
    <script src="../js/bootstrap.bundle.min.js" integrity="sha384-6khuMg9gaYr5AxOqhkVIODVIvm9ynTT5J4V1cfthmT+emCG6yVmEZsRHdxlotUnm" crossorigin="anonymous"></script>
    <script src="../js/form-validation.js"></script>

</body>

</html>