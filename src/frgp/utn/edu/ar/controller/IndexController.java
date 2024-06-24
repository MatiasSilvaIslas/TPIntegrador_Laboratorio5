package frgp.utn.edu.ar.controller;


//import javax.servlet.http.HttpServletRequest;
//import javax.servlet.http.HttpServletResponse;

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

    @RequestMapping("/clientes.html")//, method = RequestMethod.GET) captura el link
    public ModelAndView abmlPaciente() {
        ModelAndView mv = new ModelAndView();
        mv.setViewName("clientes");//renderiza el jsp de la carpeta vistas
        return mv;
    }

    @Autowired
    UsuarioNegocioImp negocioUsuario;
    //value = "admin.html", method = RequestMethod.POST
    @RequestMapping(value={"/admin.html","/medico.html","/index.html"}, method = RequestMethod.POST)
    public ModelAndView autenticarUsuario(String txtUsuario, String txtClave) {

        Usuario usuario = negocioUsuario.autenticarUsuario(txtUsuario, txtClave);
        ModelAndView mv = new ModelAndView();

        if (usuario != null) {
            mv.addObject("usuario", usuario);
            if (usuario.getAdmin() instanceof Administrador)
                mv.setViewName("admin");//renderiza el vista admin
             else if (usuario.getMedico() instanceof Medico)
                mv.setViewName("medico"); //renderiza el vista medico
        } else {
            mv.setViewName("index");//devolver el error
        }
        return mv;
    }

}