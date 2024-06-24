<%@page import="frgp.utn.edu.ar.entidad.Paciente"%>
<%@page import="java.util.List"%>
<%@page import="frgp.utn.edu.ar.entidad.Usuario"%>
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

<%
	Usuario usuario = (Usuario) request.getAttribute("usuario");
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
<div class="text-center mt-4 mb-4">
        <h2>Administrar Pacientes</h2>

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
</body>
</html>