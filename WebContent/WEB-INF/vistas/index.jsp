<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<link rel="stylesheet" href="https://use.fontawesome.com/releases/v5.0.13/css/all.css" integrity="sha384-DNOHZ68U8hZfKXOrtjWvjxusGo9WQnrNx2sqG0tfsghAvtVlRW3tvkXWZh58N9jp" crossorigin="anonymous">
<!-- Bootstrap -->
<link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.2/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-T3c6CoIi6uLrA9TneNEoa7RxnatzjcDSCmG1MXxSR1GAsXEV/Dwwykc2MPK8M2HN" crossorigin="anonymous">
<!-- Bootstrap icons -->
<link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap-icons@1.11.1/font/bootstrap-icons.css">

<title>Consultorio Medico UTN</title>
<link rel="icon" href="./img/favicon.png" type="image/png" sizes="30x38">

</head>

<body>
<section class="vh-100" style="background-color: #508bfc;">
    <% 
        boolean isCliente = true;
        if(request.getAttribute("isCliente") != null) {
            isCliente = (Boolean) request.getAttribute("isCliente");
        }
    %>
    <div class="container py-5 h-100">
        <div class="row d-flex justify-content-center align-items-center h-100">
            <img alt="logo" class="text-center w-25 p-1" src="img/logo.png">
            <div class="col-12 col-md-8 col-lg-6 col-xl-5 ms-5">
                <div class="card shadow-2-strong" style="border-radius: 1rem;">
                    <div class="card-body text-center">
                        <h2 class="mt-3 mb-5 fs-1">Acceso</h2>
                        <form class="text-center" action="admin.html" method="post">
                            <fieldset>
                                <div class="form-floating">
                                    <input type="text" name="txtUsuario" placeholder="Usuario" required class="form-control form-control-lg mb-2">
                                    <label for="floatingInput">Usuario</label>
                                </div>
                                <div class="form-floating">
                                    <input type="password" name="txtClave" placeholder="Clave" required class="form-control form-control-lg mb-2">
                                    <label for="floatingInput">Clave</label>
                                </div>
                                <p>
                                    <input class="btn btn-outline-info fs-4" type="submit" value="Iniciar sesión" name="btnIniciarSesion" id="btnIniciarSesion">
                                </p>
                            </fieldset>
                        </form>
                        <div class="opcionesIndex">
                            <a href="/TPINT_GRUPO_2_LAB4/ServletUsuario?btnIndex">¿Te olvidaste la clave?</a>
                        </div>
                        <div style="display:flex; flex-direction: column; align-items: center;">
                            <% if (isCliente == false) { %>    
                                <p style="font-size: 1.2rem; color:red; margin-top:20px;">¡Error de inicio de sesión! Por favor, verifique sus credenciales o visite una sucursal para obtener asistencia adicional. </p>
                            <% } %>
                        </div>
                        <% if (request.getAttribute("SesionFallida") != null) { %>
                            <script>
                                alert("Usuario o clave no válida, intente nuevamente.");
                            </script>
                        <% } %>
                    </div>
                </div>
            </div>
        </div>
    </div>
    <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.2/dist/js/bootstrap.bundle.min.js" integrity="sha384-C6RzsynM9kWDrMNeT87bh95OGNyZPhcTNXj1NW7RuBCsyN/o0jlpcV8Qyq46cDfL" crossorigin="anonymous"></script>
</section>    
</body>
</html>
