package frgp.utn.edu.ar.controller;

import frgp.utn.edu.ar.negocioImp.UsuarioNegocioImp;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

public class PacienteController {


    @RequestMapping(value = "/clientes.html")
    public ModelAndView home() {

        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("clientes");
        return modelAndView;
    }
}
