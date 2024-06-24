<%@page import="frgp.utn.edu.ar.entidad.Paciente"%>
<%@page import="java.util.List"%>
<%@page import="frgp.utn.edu.ar.entidad.Usuario"%>
<%@ page import="org.hibernate.Session" %>
<%@ page import="java.net.HttpCookie" %>
<%@ page import="org.springframework.web.util.HttpSessionMutexListener" %>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
 <%@page session="true"%>


<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.3.1/css/bootstrap.min.css" integrity="sha384-ggOyR0iXCbMQv3Xipma34MD+dH/1fQ784/j6cY/iJTQUOhcWr7x9JvoRxT2MZw1T" crossorigin="anonymous">
<link rel="stylesheet" href="https://use.fontawesome.com/releases/v5.0.13/css/all.css" integrity="sha384-DNOHZ68U8hZfKXOrtjWvjxusGo9WQnrNx2sqG0tfsghAvtVlRW3tvkXWZh58N9jp" crossorigin="anonymous">
<link rel ="stylesheet" type="text/css" href="${pageContext.request.contextPath}/resources/css/styles.css"> 
<title>ABML Pacientes</title>
</head>
<body>
<nav>
    <div class="nav nav-tabs" id="nav-tab" role="tablist">
        <a class="nav-link active" id="nav-home-tab" data-bs-toggle="tab" href="abmlPacientes.jsp" role="tab" aria-controls="nav-home" aria-selected="true">Pacientes</a>3
        <a class="nav-link" id="nav-profile-tab" data-bs-toggle="tab" href="abmlMedicos.jsp" role="tab" aria-controls="nav-profile" aria-selected="false">Medicos</a>
        <a class="nav-link" id="nav-contact-tab" data-bs-toggle="tab" href="abmlTurnos.jsp" role="tab" aria-controls="nav-contact" aria-selected="false">Turnos</a>
    </div>
</nav>
<%

	Usuario usuario = (Usuario) request.getAttribute("usuario");
    if (usuario == null) {
     usuario.getAdmin().setNombre(session.getAttribute("usuario").toString());
    }
%>

<header  style=" padding: 25px;">
  <nav style="" class="navbar navbar-expand-lg navbar-light bg-white fixed-top" >
    <div class="container-fluid">
      <div class="collapse navbar-collapse" id="navbarExample01">
			<ul class="navbar-nav me-auto mb-2 mb-lg-0">
   			 <li class="nav-item active" style="background-color: highlight;">
        		<a class="nav-link" aria-current="page" href="index.jsp">
            		<i class="fas fa-arrow-left"></i> 
            		
           				Cerrar Sesion
       				 </a>
    		</li>
			</ul>

        	<div class="alert alert-info ml-auto">
               <i class="fas fa-user"></i>
               <span><%= ((Usuario) request.getAttribute("usuario")).getAdmin().getNombre() %></span>
            </div>
      </div>
    </div>
  </nav>
  
</header>
<div class="container mt-5">
<div class="mt-4 mb-4">
    <div class="container-fluid">
        <div class="text-center">
        <h2>Administrar Pacientes</h2>
        </div>
        <div class="float-right mb-4">
            <button type="button" class="btn btn-info" data-toggle="modal" data-target="#altaPacienteModal">+ Alta paciente</button>
        </div>
    </div>
        <table id="miTabla" class="table table-info table-striped table-hover mt-5 shadow-lg p-3 mb-5 bg-body-tertiary rounded">
            <thead>
                <tr>
                    <th>Nombre</th>
                    <th>Apellido</th>
                    <th>DNI</th>
                    <th>Fecha de Nacimiento</th>
                    <th>Dirección</th>
                    <th>Provincia</th>
                    <th>Localidad</th>
                    <th>Sexo</th>
                    <th>Teléfono</th>
                    <th></th>
                    <th></th>
                </tr>
            </thead>
            <tbody>
                <%
                    List<Paciente> pacientes = (List<Paciente>) request.getAttribute("pacientes");
                    if (pacientes != null) {
                        for(Paciente pac : pacientes) {
                %>
                            <tr>
                                <td><%= pac.getNombre() %></td>
                                <td><%= pac.getApellido() %></td>
                                <td><%= pac.getDNI() %></td>
                                <td><%= pac.getFechaNacimiento() %></td>
                                <td><%= pac.getDireccion() %></td>
                                <td><%= pac.getProvincia() %></td>
                                <td><%= pac.getLocalidad() %></td>
                                <td><%= pac.getSexo() %></td>
                                <td><%= pac.getTelefono() %></td>
                                <td>
                                	<button type="button" class="btn btn-danger">Eliminar</button>
                                </td>
                                <td>
                                	<button type="button" class="btn btn-info">Editar</button>
                                </td>
                            </tr>
                <%
                        }
                    }
                %>
            </tbody>
        </table>
    </div>
</div>


<!-- Modal -->
<div class="modal fade" id="altaPacienteModal" tabindex="-1" role="dialog" aria-labelledby="altaPacienteModalLabel" aria-hidden="true">
    <div class="modal-dialog" role="document">
        <div class="modal-content">
            <div class="modal-header">
                <h5 class="modal-title" id="altaPacienteModalLabel">Alta de Paciente</h5>
                <button type="button" class="close" data-dismiss="modal" aria-label="Close">
                    <span aria-hidden="true">&times;</span>
                </button>
            </div>
            <div class="modal-body">
                <form action="PacienteAlta.html" method="post" >
                    <div class="form-group">
                        <label for="nombre">Nombre</label>
                        <input type="text" class="form-control" id="nombre" name="nombre">
                    </div>
                    <div class="form-group">
                        <label for="apellido">Apellido</label>
                        <input type="text" class="form-control" id="apellido" name="apellido" required>
                    </div>
                    <div class="form-group">
                        <label for="dni">DNI</label>
                        <input type="number" class="form-control" id="dni" name="dni" required>
                    </div>
                    <div class="form-group">
                        <label for="fechaNacimiento">Fecha de Nacimiento</label>
                        <input type="date" class="form-control" id="fechaNacimiento" name="fechaNacimiento" required>
                    </div>
                    <div class="form-group">
                        <label for="direccion">Dirección</label>
                        <input type="text" class="form-control" id="direccion" name="direccion" required>
                    </div>
                    <div class="form-group">
                        <label for="provincia">Provincia</label>
                        <%--<input type="text" class="form-control" id="provincia">--%>

                        <select name="provincias" id="provincia" required>
                            <option value="Buenos Aires">Buenos Aires</option>
                            <option value="Catamarca">Catamarca</option>
                            <option value="Chaco">Chaco</option>
                            <option value="Chubut">Chubut</option>
                            <option value="Córdoba">Córdoba</option>
                            <option value="Corrientes">Corrientes</option>
                            <option value="Entre Ríos">Entre Ríos</option>
                            <option value="Formosa">Formosa</option>
                            <option value="Jujuy">Jujuy</option>
                            <option value="La Pampa">La Pampa</option>
                            <option value="La Rioja">La Rioja</option>
                            <option value="Mendoza">Mendoza</option>
                            <option value="Misiones">Misiones</option>
                            <option value="Neuquén">Neuquén</option>
                            <option value="san Juan">San Juan</option>
                        </select>
                    </div>
                    <div class="form-group">
                        <label for="localidad">Localidad</label>
                        <input type="text" class="form-control" id="localidad" name="localidad" required>
                    </div>
                    <div class="form-group">
                        <label for="email">Correo</label>
                        <input type="text" class="form-control" id="email" name="email" required>
                    </div>
                    <div class="form-group">
                        <label for="sexo">Sexo</label>
                        <%--<input type="text" class="form-control" id="sexo" name="sexo">--%>
                        <select name="sexo" id="sexo" required>
                            <option value="M">Masculino</option>
                            <option value="F">Femenino</option>
                        </select>
                    </div>
                    <div class="form-group">
                        <label for="telefono">Teléfono</label>
                        <input type="number" class="form-control" id="telefono" name="telefono" required>
                    </div>
                    <div class="modal-footer">
                        <button  class="btn btn-secondary" data-dismiss="modal">Cerrar</button>
                        <input type="submit" class="btn btn-primary" value="guardar" id="guardar" name="guardar"/>
                    </div>
                </form>
            </div>

        </div>
    </div>
</div>
<script src="https://code.jquery.com/jquery-3.3.1.slim.min.js" integrity="sha384-q8i/X+965DzO0rT7abK41JStQIAqVgRVzpbzo5smXKp4YfRvH+8abtTE1Pi6jizo" crossorigin="anonymous"></script>
<script src="https://cdn.jsdelivr.net/npm/popper.js@1.14.3/dist/umd/popper.min.js" integrity="sha384-ZMP7rVo3mIykV+2+9J3UJ46jBk0WLaUAdn689aCwoqbBJiSnjAK/l8WvCWPIPm49" crossorigin="anonymous"></script>
<script src="https://cdn.jsdelivr.net/npm/bootstrap@4.1.3/dist/js/bootstrap.min.js" integrity="sha384-ChfqqxuZUCnJSK3+MXmPNIyE6ZbWh2IMqE241rYiqJxyMiZ6OW/JmZQ5stwEULTy" crossorigin="anonymous"></script>
<script src="https://cdn.jsdelivr.net/npm/sweetalert2@11"></script>

<script>
    document.getElementById('guardar').addEventListener('click', function() {
        swal("ok!", "se agrego correctamente!", "success");
    },2000);
</script>
</body>
</html>