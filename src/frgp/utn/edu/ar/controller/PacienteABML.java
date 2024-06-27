package frgp.utn.edu.ar.controller;

import java.sql.Date;
import java.util.List;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import frgp.utn.edu.ar.entidad.Localidad;
import frgp.utn.edu.ar.entidad.Paciente;
import frgp.utn.edu.ar.entidad.Provincia;
import frgp.utn.edu.ar.entidad.Usuario;
import frgp.utn.edu.ar.negocioImp.PacienteNegocioImp;
import frgp.utn.edu.ar.negocioImp.UsuarioNegocioImp;
import frgp.utn.edu.ar.negocioImp.LocalidadNegocio;
import frgp.utn.edu.ar.negocioImp.ProvinciaNegocio;
@Controller
public class PacienteABML {
    @Autowired
    UsuarioNegocioImp negocioUsuario;

    @Autowired
    PacienteNegocioImp pacienteNegocio;
    
    @Autowired
    LocalidadNegocio localidadNegocio;
    @Autowired
    ProvinciaNegocio provinciaNegocio;

    @RequestMapping(value = "PacienteAlta.html", method = RequestMethod.POST)
    //VER EL TEMA DE LA PROVINCIA Y LOCALIDAD
    public ModelAndView altaPaciente(String nombre, String apellido, String dni, String telefono, Integer provincia, Integer localidad, String direccion, String fechaNacimiento, String sexo,HttpServletRequest request,String email){
        HttpSession session = request.getSession();
        System.out.println("entro a alta paciente");
        //String username = request.getParameter("usuario");
        String username =session.getAttribute("usuario").toString();
        System.out.println("usuario: "+username);

        Usuario usuario = negocioUsuario.ReadOne(username);
        Provincia provinciaObj = provinciaNegocio.ReadOne(provincia);
        Localidad localidadObj = localidadNegocio.ReadOne(localidad);
        System.out.println("entro a alta paciente");
        ModelAndView mv = new ModelAndView();
        try {
            Paciente paciente = new Paciente();
            paciente.setNombre(nombre);
            paciente.setApellido(apellido);
            paciente.setDNI(Integer.valueOf(dni));
            paciente.setTelefono(telefono);
            paciente.setProvincia(provinciaObj);
            paciente.setLocalidad(localidadObj);
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
                List<Provincia> provincias = provinciaNegocio.ReadAll();
                List<Localidad> localidades = localidadNegocio.ReadAll();
                mv.addObject("usuario", usuario);
                mv.addObject("pacientes", pacientes);
                mv.addObject("provincias", provincias);
                mv.addObject("localidades", localidades);
                mv.setViewName("abmlPacientes");
            } else {
                mv.setViewName("error");
            }
        }
        return mv;
    }

}
//EDITAR PACIENTE EMI
//ALTA PACIENTE NICO