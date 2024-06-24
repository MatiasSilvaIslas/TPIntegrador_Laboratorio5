package frgp.utn.edu.ar.controller;

import java.sql.Date;
import java.util.List;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import frgp.utn.edu.ar.entidad.Turno;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;


import frgp.utn.edu.ar.entidad.Paciente;
import frgp.utn.edu.ar.entidad.Usuario;
import frgp.utn.edu.ar.negocioImp.PacienteNegocioImp;
import frgp.utn.edu.ar.negocioImp.UsuarioNegocioImp;

@Controller
public class PacienteABML {
    @Autowired
    UsuarioNegocioImp negocioUsuario;

    @Autowired
    PacienteNegocioImp pacienteNegocio;

    @RequestMapping(value = "PacienteAlta.html", method = RequestMethod.POST)

    public ModelAndView altaPaciente(String nombre, String apellido, String dni, String telefono, String provincias, String localidad, String direccion, String fechaNacimiento, String sexo,HttpServletRequest request,String email){
        HttpSession session = request.getSession();
        System.out.println("entro a alta paciente");
        //String username = request.getParameter("usuario");
        String username =session.getAttribute("usuario").toString();
        System.out.println("usuario: "+username);

        Usuario usuario = negocioUsuario.ReadOne(username);
        System.out.println("entro a alta paciente");
        ModelAndView mv = new ModelAndView();
        try {
            Paciente paciente = new Paciente();
            paciente.setNombre(nombre);
            paciente.setApellido(apellido);
            paciente.setDNI(Integer.valueOf(dni));
            paciente.setTelefono(telefono);
            paciente.setProvincia(provincias);
            paciente.setLocalidad(localidad);
            paciente.setDireccion(direccion);
            paciente.setFechaNacimiento(Date.valueOf(fechaNacimiento));
            paciente.setSexo(sexo.charAt(0));
            paciente.setCorreoElectronico(email);
            pacienteNegocio.Add(paciente);
            mv.addObject("usuario", usuario);
            mv.addObject("msg", "ok, paciente agregado correctamente");

			mv.setViewName("admin");
        } catch (Exception e) {
            mv.addObject("msgerror", "algo salio mal"+e.getMessage());
			mv.setViewName("abmlPacientes");
        }

        return mv;
    }

    @RequestMapping(value = "PacienteABML.html", method = RequestMethod.POST)
    public ModelAndView listaPacientes(String username) {

        Usuario usuario = negocioUsuario.ReadOne(username);
        ModelAndView mv = new ModelAndView();

        if (usuario != null) {
            if (usuario.getAdmin() != null) {
                List<Paciente> pacientes = pacienteNegocio.ReadAll();

                mv.addObject("usuario", usuario);
                mv.addObject("pacientes", pacientes);
                mv.setViewName("abmlPacientes");
            } else {
                mv.setViewName("error");
            }
        }
        return mv;
    }

}
