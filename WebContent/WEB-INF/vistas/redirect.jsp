<%@page import="frgp.utn.edu.ar.entidad.Usuario"%>
<%
    Usuario usuario = (Usuario) session.getAttribute("usuario");

    if (usuario == null) {
    	 response.sendRedirect("/TPIntegrador_grupo7/error.jsp");
    } 
    else if (usuario.getAdmin() != null) {
        response.sendRedirect("/TPIntegrador_grupo7/admin.jsp");
    } 
    else if (usuario.getMedico() != null) {
        response.sendRedirect("/TPIntegrador_grupo7/medico.jsp");
    } 
%>
