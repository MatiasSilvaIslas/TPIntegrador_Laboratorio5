package frgp.utn.edu.ar.controller;


import frgp.utn.edu.ar.entidad.Administrador;
import frgp.utn.edu.ar.entidad.Medico;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import frgp.utn.edu.ar.entidad.Usuario;
import frgp.utn.edu.ar.negocioImp.UsuarioNegocioImp;



@Controller
public class IndexController {

	@RequestMapping("/index.html")
    public ModelAndView showHomePage() {
        ModelAndView mv = new ModelAndView();
        mv.setViewName("index");
        return mv;
    }

	
	@Autowired
	UsuarioNegocioImp negocioUsuario;

	@RequestMapping(value = "redirect.html", method = RequestMethod.POST)
    public ModelAndView autenticarUsuario(String txtUsuario, String txtClave) {
		
        Usuario usuario = negocioUsuario.autenticarUsuario(txtUsuario, txtClave);
        ModelAndView mv = new ModelAndView();
              
        if (usuario != null) {
            if (usuario.getAdmin() instanceof Administrador) {
            	 mv.addObject("usuario", usuario);
                 mv.setViewName("admin");
            } else if (usuario.getMedico()instanceof Medico)
            	mv.setViewName("medico");
            }else {
            	mv.setViewName("error");
            }
		return mv;
	}
    
}