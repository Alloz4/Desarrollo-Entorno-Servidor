package com.openwebinars.spring.controladores;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;


@Controller
public class MainController {
	@GetMapping("/")
	public String welcome(Model model) {
		model.addAttribute("mensaje", "¡Hola a todos los alumnos de OpenWebinars!");
		return "index";
	}
	@GetMapping("/saludo")
	public String saludo(Model model) {
		model.addAttribute("mensaje", "Seguron que has visto otras plataformas con miles de cursos, pero OpenWebinars es la mejor");
		return "saludo";
	}
}
