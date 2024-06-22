package frgp.utn.edu.ar.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

public class PacienteController {

    @RequestMapping("/pacientes/alta.html")
    public ModelAndView home() {
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("alta");
        return modelAndView;
    }
}
