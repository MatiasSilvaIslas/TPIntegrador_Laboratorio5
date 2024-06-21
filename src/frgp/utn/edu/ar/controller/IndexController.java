package frgp.utn.edu.ar.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class IndexController {
	 @RequestMapping("/index.html")
	    public ModelAndView showHomePage() {
	        return new ModelAndView("index");
	    }
}
