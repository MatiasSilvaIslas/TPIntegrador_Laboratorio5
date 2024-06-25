<%@page import="frgp.utn.edu.ar.entidad.Usuario"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@page session="true"%>

<!DOCTYPE html>
<html lang="es">
<head>
  <meta charset="UTF-8">
<link rel="stylesheet"
	href="https://stackpath.bootstrapcdn.com/bootstrap/4.3.1/css/bootstrap.min.css"
	integrity="sha384-ggOyR0iXCbMQv3Xipma34MD+dH/1fQ784/j6cY/iJTQUOhcWr7x9JvoRxT2MZw1T"
	crossorigin="anonymous">
<link rel="stylesheet"
	href="https://use.fontawesome.com/releases/v5.0.13/css/all.css"
	integrity="sha384-DNOHZ68U8hZfKXOrtjWvjxusGo9WQnrNx2sqG0tfsghAvtVlRW3tvkXWZh58N9jp"
	crossorigin="anonymous">
<link rel="stylesheet" type="text/css"
	href="${pageContext.request.contextPath}/resources/css/styles.css">
<title>Inicio - Admin</title>
    <link rel="icon" href="./img/favicon.png" type="image/png" sizes="30x38">
</head>
<body>
<%
	session= request.getSession();
	String usuario = ((Usuario) request.getAttribute("usuario")).getUser().toString();
	session.setAttribute("usuario", usuario);
%>
	<header style="padding: 25px;">
		<nav style=""
			class="navbar navbar-expand-lg navbar-light bg-white fixed-top">
			<div class="container-fluid">
				<div class="collapse navbar-collapse" id="navbarExample01">
					<ul class="navbar-nav me-auto mb-2 mb-lg-0">
						<li class="nav-item active" style="background-color: highlight;">
							<a class="nav-link" aria-current="page" href="index.jsp"> 
								<i class="fas fa-arrow-left"></i> Cerrar Sesion
							</a>
						</li>
					</ul>

					<div class="alert alert-info ml-auto">
						<i class="fas fa-user"></i> <span><%=((Usuario) request.getAttribute("usuario")).getAdmin().getNombre()%></span>
					</div>
				</div>
			</div>
		</nav>
	</header>

	<div class="container-fluid menu">
		<div class="row">
			<div class="col-md-2"></div>
			<div class="col-md-8 text-center mt-5">
				<br>
				<h1>Panel Administrador</h1>
				<p>Seleccione la opción deseada:</p>
			</div>
			<div class="col-md-2"></div>
		</div>

		<div class="row mt-4">
			<div class="col-md-12">
				<div class="card-deck">
					<form action="PacienteABML.html" method="post" class="card-button">
                        <!-- Campos ocultos para enviar datos del usuario -->
                        <input type="hidden" name="username" value="<%=((Usuario) request.getAttribute("usuario")).getUser()%>">
                        <!-- Tarjeta clicable -->
                        <button type="submit" class="card text-center bg-light shadow h-100 py-2 card-button">
                            <div class="card-body">
                                <i class="fas fa-user card-icon fa-3x"></i>
                                <h5 class="card-title mt-2">Administrar Pacientes</h5>
                            </div>
                        </button>
                    </form>

					<div class="card text-center bg-light shadow h-100 py-2">
						<a href="#" class="card-body"> <i
							class="fas fa-user-md card-icon fa-3x"></i>
							<h5 class="card-title mt-2">Administrar Medicos</h5>
						</a>
					</div>
					<div class="card text-center bg-light shadow h-100 py-2">
						<a href="#" class="card-body"> <i
							class="fas fa-first-aid card-icon fa-3x"></i>
							<h5 class="card-title mt-2">Asignación de Turnos</h5>
						</a>
					</div>
					<div class="card text-center bg-light shadow py-2">
						<a href="#" class="card-body"> <i
							class="far fa-file-archive card-icon fa-3x"></i>
							<h5 class="card-title mt-2">Informes</h5>
						</a>
					</div>
				</div>
			</div>
		</div>
</body>
</html>