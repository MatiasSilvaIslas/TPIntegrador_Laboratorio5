<%@page import="frgp.utn.edu.ar.entidad.Localidad"%>
<%@page import="frgp.utn.edu.ar.entidad.Provincia"%>
<%@page import="frgp.utn.edu.ar.entidad.Paciente"%>
<%@page import="java.util.List"%>
<%@page import="frgp.utn.edu.ar.entidad.Usuario"%>
<%@ page import="org.hibernate.Session"%>
<%@ page import="java.net.HttpCookie"%>
<%@ page import="org.springframework.web.util.HttpSessionMutexListener"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@page session="true"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<!DOCTYPE html>
<html>
<head>
<!--<meta charset="ISO-8859-1">-->
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
<link rel="stylesheet"
	href="https://cdn.jsdelivr.net/npm/bootstrap-icons@1.11.3/font/bootstrap-icons.min.css">
<link
	href="https://stackpath.bootstrapcdn.com/bootstrap/4.5.2/css/bootstrap.min.css"
	rel="stylesheet">

<title>ABML Pacientes</title>
<link rel="icon" href="./img/favicon.png" type="image/png" sizes="30x38">

<%
	List<Localidad> listaLocalidadess = (List<Localidad>) request.getAttribute("localidades");
	List<Provincia> listaProvinciass = (List<Provincia>) request.getAttribute("provincias");
%>
<script>
    var localidadesPorProvincia = {
        <%for (Provincia p : listaProvinciass) {
				out.print(p.getId() + ": [");
				boolean first = true;
				for (Localidad l : listaLocalidadess) {
					if (l.getProvincia().getId() == p.getId()) {
						if (!first) {
							out.print(",");
						}
						out.print("{id: " + l.getId() + ", nombre: '" + l.getLocalidad() + "'}");
						first = false;
					}
				}
				out.print("],");
			}%>
    };
</script>

<script>
    function cargarLocalidades() {
        var provinciaSelect = document.getElementById("provincia");
        var localidadSelect = document.getElementById("localidad");
        var provinciaId = provinciaSelect.value;
        
        localidadSelect.innerHTML = "";

        var localidades = localidadesPorProvincia[provinciaId];

        for (var i = 0; i < localidades.length; i++) {
            var option = document.createElement("option");
            option.value = localidades[i].id;
            option.text = localidades[i].nombre;
            localidadSelect.appendChild(option);
        }
    }
</script>

</head>
<body>
	<nav>
		<div class="nav nav-tabs" id="nav-tab" role="tablist">
			<a class="nav-link active" id="nav-home-tab" data-bs-toggle="tab"
				href="abmlPacientes.jsp" role="tab" aria-controls="nav-home"
				aria-selected="true">Pacientes</a>3 <a class="nav-link"
				id="nav-profile-tab" data-bs-toggle="tab" href="abmlMedicos.jsp"
				role="tab" aria-controls="nav-profile" aria-selected="false">Medicos</a>
			<a class="nav-link" id="nav-contact-tab" data-bs-toggle="tab"
				href="abmlTurnos.jsp" role="tab" aria-controls="nav-contact"
				aria-selected="false">Turnos</a>
		</div>
	</nav>
	<%
		Usuario usuario = (Usuario) request.getAttribute("usuario");
		if (usuario == null) {
			usuario.getAdmin().setNombre(session.getAttribute("usuario").toString());
		}
	%>

	<header style="padding: 25px;">
		<nav style=""
			class="navbar navbar-expand-lg navbar-light bg-white fixed-top">
			<div class="container-fluid">
				<div class="collapse navbar-collapse" id="navbarExample01">
					<ul class="navbar-nav me-auto mb-2 mb-lg-0">
						<li class="nav-item active" style="background-color: highlight;">
							<a class="nav-link" aria-current="page" href="./admin.html">
								<i class="bi bi-house"></i> Menu Principal
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

	<div class="container mt-5">
		<div class="mt-4 mb-4">
			<div class="container-fluid">
				<div class="text-center">
					<h2>Administrar Pacientes</h2>
				</div>
				<div class="float-right mb-4">
					<button type="button" class="btn btn-info" data-toggle="modal"
						data-target="#altaPacienteModal">+ Alta paciente</button>
				</div>
			</div>
			<table id="miTabla"
				class="table table-info table-striped table-hover mt-5 shadow-lg p-3 mb-5 bg-body-tertiary rounded">
				<thead>
					<tr>
						<th>Nombre</th>
						<th>Apellido</th>
						<th>DNI</th>
						<th>Fecha de Nacimiento</th>
						<th>Direcci�n</th>
						<th>Provincia</th>
						<th>Localidad</th>
						<th>Sexo</th>
						<th>Telefono</th>
						<th></th>
						<th></th>
					</tr>
				</thead>
				<tbody>
					<%
						List<Paciente> pacientes = (List<Paciente>) request.getAttribute("pacientes");
						if (pacientes != null) {
							for (Paciente pac : pacientes) {
					%>
					<tr>
						<td><%=pac.getNombre()%></td>
						<td><%=pac.getApellido()%></td>
						<td><%=pac.getDNI()%></td>
						<td><%=pac.getFechaNacimiento()%></td>
						<td><%=pac.getDireccion()%></td>
						<td><%=pac.getProvincia().getProvincia()%></td>
						<td><%=pac.getLocalidad().getLocalidad()%></td>
						<td><%=pac.getSexo()%></td>
						<td><%=pac.getTelefono()%></td>
						<td>
							<button type="button" class="btn btn-outline-primary editar-btn"
								data-id="<%=pac.getId()%>"
								data-nombre="<%=pac.getNombre()%>"
								data-apellido="<%=pac.getApellido()%>"
								data-dni="<%=pac.getDNI()%>"
								data-fecha-nacimiento="<%=pac.getFechaNacimiento()%>"
								data-direccion="<%=pac.getDireccion()%>"
								data-provincia-id="<%=pac.getProvincia().getId()%>"
								data-localidad-id="<%=pac.getLocalidad().getId()%>"
								data-email="<%=pac.getCorreoElectronico()%>"
								data-sexo="<%=pac.getSexo()%>"
								data-telefono="<%=pac.getTelefono()%>" data-toggle="modal"
								data-target="#altaPacienteModal">
								<svg xmlns="http://www.w3.org/2000/svg" width="16" height="16"
									fill="currentColor" class="bi bi-pencil" viewBox="0 0 16 16">
            <path
										d="M12.146.146a.5.5 0 0 1 .708 0l3 3a.5.5 0 0 1 0 .708l-10 10a.5.5 0 0 1-.168.11l-5 2a.5.5 0 0 1-.65-.65l2-5a.5.5 0 0 1 .11-.168zM11.207 2.5 13.5 4.793 14.793 3.5 12.5 1.207zm1.586 3L10.5 3.207 4 9.707V10h.5a.5.5 0 0 1 .5.5v.5h.5a.5.5 0 0 1 .5.5v.5h.293zm-9.761 5.175-.106.106-1.528 3.821 3.821-1.528.106-.106A.5.5 0 0 1 5 12.5V12h-.5a.5.5 0 0 1-.5-.5V11h-.5a.5.5 0 0 1-.468-.325"></path>
        </svg>
							</button>

						</td>
						<td>
							<button type="button" class="btn btn-outline-danger"
								onclick="eliminarPaciente(<%=pac.getDNI()%>)">
								<svg xmlns="http://www.w3.org/2000/svg" width="16" height="16"
									fill="currentColor" class="bi bi-trash3" viewBox="0 0 16 16">
        <path
										d="M6.5 1h3a.5.5 0 0 1 .5.5v1H6v-1a.5.5 0 0 1 .5-.5M11 2.5v-1A1.5 1.5 0 0 0 9.5 0h-3A1.5 1.5 0 0 0 5 1.5v1H1.5a.5.5 0 0 0 0 1h.538l.853 10.66A2 2 0 0 0 4.885 16h6.23a2 2 0 0 0 1.994-1.84l.853-10.66h.538a.5.5 0 0 0 0-1zm1.958 1-.846 10.58a1 1 0 0 1-.997.92h-6.23a1 1 0 0 1-.997-.92L3.042 3.5zm-7.487 1a.5.5 0 0 1 .528.47l.5 8.5a.5.5 0 0 1-.998.06L5 5.03a.5.5 0 0 1 .47-.53Zm5.058 0a.5.5 0 0 1 .47.53l-.5 8.5a.5.5 0 1 1-.998-.06l.5-8.5a.5.5 0 0 1 .528-.47M8 4.5a.5.5 0 0 1 .5.5v8.5a.5.5 0 0 1-1 0V5a.5.5 0 0 1 .5-.5"></path>
    </svg>
							</button>
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
	<div class="modal fade" id="altaPacienteModal" tabindex="-1"
		role="dialog" aria-labelledby="altaPacienteModalLabel"
		aria-hidden="true">
		<div class="modal-dialog" role="document">
			<div class="modal-content">
				<div class="modal-header">
					<h5 class="modal-title" id="altaPacienteModalLabel">Alta de
						Paciente</h5>
					<button type="button" class="close" data-dismiss="modal"
						aria-label="Close">
						<span aria-hidden="true">&times;</span>
					</button>
				</div>
				<div class="modal-body">
					<form action="PacienteAlta.html" method="post">
					<%
        String accion = "alta"; // Valor por defecto para alta
        String idPaciente = request.getParameter("id"); // Obtener el ID del paciente si existe

        if (idPaciente != null && !idPaciente.isEmpty()) {
            accion = "editar"; // Si hay un ID de paciente, cambiar a editar
        }
    %>
						<input type="hidden" id="accion" name="accion" value="<%= accion %>">
						<input type="hidden" id="id" name="id">
						<div class="form-group">
							<label for="nombre">Nombre</label> <input type="text"
								class="form-control" id="nombre" name="nombre">
						</div>
						<div class="form-group">
							<label for="apellido">Apellido</label> <input type="text"
								class="form-control" id="apellido" name="apellido" required>
						</div>
						<div class="form-group">
							<label for="dni">DNI</label> <input type="number"
								class="form-control" id="dni" name="dni" required>
						</div>
						<div class="form-group">
							<label for="fechaNacimiento">Fecha de Nacimiento</label> <input
								type="date" class="form-control" id="fechaNacimiento"
								name="fechaNacimiento" required>
						</div>
						<div class="form-group">
							<label for="direccion">Direcci�n</label> <input type="text"
								class="form-control" id="direccion" name="direccion" required>
						</div>
						<div class="form-group">
							<label for="provincia">Provincia</label> <select name="provincia"
								id="provincia" class="form-control" required
								onchange="cargarLocalidades()">
								<%
									List<Provincia> listaProvincias = (List<Provincia>) request.getAttribute("provincias");
									if (listaProvincias != null) {
										for (Provincia p : listaProvincias) {
								%>
								<option value="<%=p.getId()%>"><%=p.getProvincia()%></option>
								<%
									}
									}
								%>
							</select>
						</div>
						<div class="form-group">
							<label for="localidad">Localidad</label> <select name="localidad"
								id="localidad" class="form-control" required>
								<%
									List<Localidad> listaLocalidades = (List<Localidad>) request.getAttribute("localidades");
									if (listaLocalidades != null) {
										for (Localidad l : listaLocalidades) {
								%>
								<option value="<%=l.getId()%>"><%=l.getLocalidad()%></option>
								<%
									}
									}
								%>
							</select>
						</div>
						<div class="form-group">
							<label for="email">Correo</label> <input type="text"
								class="form-control" id="email" name="email" required>
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
							<label for="telefono">Tel�fono</label> <input type="number"
								class="form-control" id="telefono" name="telefono" required>
						</div>
						<div class="modal-footer">
							<button class="btn btn-secondary" data-dismiss="modal">Cerrar</button>
							<input type="submit" class="btn btn-primary" value="guardar"
								id="guardar" name="guardar" />
						</div>
					</form>
				</div>

			</div>
		</div>
	</div>

	<script src="https://code.jquery.com/jquery-3.3.1.slim.min.js"
		integrity="sha384-q8i/X+965DzO0rT7abK41JStQIAqVgRVzpbzo5smXKp4YfRvH+8abtTE1Pi6jizo"
		crossorigin="anonymous"></script>
	<script
		src="https://cdn.jsdelivr.net/npm/popper.js@1.14.3/dist/umd/popper.min.js"
		integrity="sha384-ZMP7rVo3mIykV+2+9J3UJ46jBk0WLaUAdn689aCwoqbBJiSnjAK/l8WvCWPIPm49"
		crossorigin="anonymous"></script>
	<script
		src="https://cdn.jsdelivr.net/npm/bootstrap@4.1.3/dist/js/bootstrap.min.js"
		integrity="sha384-ChfqqxuZUCnJSK3+MXmPNIyE6ZbWh2IMqE241rYiqJxyMiZ6OW/JmZQ5stwEULTy"
		crossorigin="anonymous"></script>
	<script src="https://cdn.jsdelivr.net/npm/sweetalert2@11"></script>

	<script>
		document.getElementById('guardar').addEventListener('click',
				function() {
					swal("ok!", "se agrego correctamente!", "success");
				}, 2000);
	</script>

	<script
		src="https://ajax.googleapis.com/ajax/libs/jquery/2.1.4/jquery.min.js">
</script>
	<script type="text/javascript" charset="utf8"
		src="https://cdn.datatables.net/1.10.19/js/jquery.dataTables.js">
</script>




	<script type="text/javascript">
    $(document).ready(function() {
        $('#miTabla').DataTable({
            "language": {
                "url": "//cdn.datatables.net/plug-ins/9dcbecd42ad/i18n/Spanish.json"
            },
            "columnDefs": [
                {
                    "targets": [0, 1, 2, 3, 4, 5, 6,7,8],
                    "searchable": true, 
                    "orderable": false 
                }
            ]
        });
    });
</script>

	<script>
    $(document).ready(function() {
        $('.editar-btn').on('click', function() {
            var id = $(this).data('id');
            var nombre = $(this).data('nombre');
            var apellido = $(this).data('apellido');
            var dni = $(this).data('dni');
            var fechaNacimiento = $(this).data('fecha-nacimiento');
            var direccion = $(this).data('direccion');
            var provinciaId = $(this).data('provincia-id');
            var localidadId = $(this).data('localidad-id');
            var email = $(this).data('email');
            var sexo = $(this).data('sexo');
            var telefono = $(this).data('telefono');

            $('#altaPacienteModal #id').val(id);
            $('#altaPacienteModal #nombre').val(nombre);
            $('#altaPacienteModal #apellido').val(apellido);
            $('#altaPacienteModal #dni').val(dni);
            $('#altaPacienteModal #fechaNacimiento').val(fechaNacimiento);
            $('#altaPacienteModal #direccion').val(direccion);
            $('#altaPacienteModal #provincia').val(provinciaId).change();
            setTimeout(function() {
                $('#altaPacienteModal #localidad').val(localidadId);
            }, 500); 
            $('#altaPacienteModal #email').val(email);
            $('#altaPacienteModal #sexo').val(sexo);
            $('#altaPacienteModal #telefono').val(telefono);
        });
    });
</script>

<script>
function eliminarPaciente(dni) {
    if (confirm("�Est�s seguro de que deseas eliminar este paciente?")) {
        const xhr = new XMLHttpRequest();
        xhr.open("POST", "PacienteEliminar.html", true);
        xhr.setRequestHeader("Content-Type", "application/x-www-form-urlencoded");

        xhr.onreadystatechange = function () {
            if (xhr.readyState === 4 && xhr.status === 200) {
                alert("Paciente eliminado correctamente");
                location.reload();
            } else if (xhr.readyState === 4) {
                alert("Ocurri� un error al eliminar el paciente");
            }
        };

        xhr.send("dni=" + dni);
    }
}
</script>

</body>
</html>