package frgp.utn.edu.ar.controller;

import java.util.List;

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
//EDITAR PACIENTE EMI
//ALTA PACIENTE NICO