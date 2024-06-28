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
    public ModelAndView altaPaciente(Integer id, String nombre, String apellido, String dni, String telefono, Integer provincia, Integer localidad, String direccion, String fechaNacimiento, String sexo,HttpServletRequest request,String email,String accion){
        HttpSession session = request.getSession();
        System.out.println("entro a alta paciente");

        String username =session.getAttribute("usuario").toString();
        Usuario usuario = negocioUsuario.ReadOne(username);
        Provincia provinciaObj = provinciaNegocio.ReadOne(provincia);
        Localidad localidadObj = localidadNegocio.ReadOne(localidad);
        ModelAndView mv = new ModelAndView();
        Paciente paciente = new Paciente();

        
        try {
            if (id == null) {
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
                paciente.setActivo(true);
                pacienteNegocio.Add(paciente);
                mv.addObject("msg", "Paciente agregado correctamente");
            } else {
                paciente = pacienteNegocio.ReadOne(Integer.valueOf(dni)); 
                if (paciente != null) {
                	paciente.setId(id);
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
                    paciente.setActivo(true);
                    pacienteNegocio.Update(paciente);
                    mv.addObject("msg", "Paciente actualizado correctamente");
                } else {
                    mv.addObject("msgerror", "Paciente no encontrado");
                }
            }

            mv.addObject("usuario", usuario);
            mv.setViewName("admin");
        } catch (Exception e) {
            mv.addObject("msgerror", "Algo salió mal: " + e.getMessage());
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
    
    @RequestMapping(value = "PacienteEliminar.html", method = RequestMethod.POST)
    public ModelAndView eliminarPaciente(String dni, HttpServletRequest request) {
        HttpSession session = request.getSession();
        String username =session.getAttribute("usuario").toString();
        Usuario usuario = negocioUsuario.ReadOne(username);
        ModelAndView mv = new ModelAndView();
        
        System.out.println("este es el numero de dni"+dni);
        Paciente pac = new Paciente();
        pac= pacienteNegocio.ReadOne(Integer.valueOf(dni));
        try {
            pacienteNegocio.Delete(pac);
            mv.addObject("msg", "Paciente eliminado correctamente");
        } catch (Exception e) {
            mv.addObject("msgerror", "Algo salió mal: " + e.getMessage());
        }
        
        mv.addObject("usuario", usuario);
        mv.setViewName("admin");
        return mv;
    }

}