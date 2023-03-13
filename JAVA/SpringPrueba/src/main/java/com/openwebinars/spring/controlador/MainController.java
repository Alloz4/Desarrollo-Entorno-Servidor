package com.openwebinars.spring.controlador;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class MainController {

	@GetMapping("/")
	public String welcome(Model model) {
		model.addAttribute("mensaje", "Práctica Canvas");
		return "index";
	}
	@GetMapping("/que")
	public String que(Model model) {
		model.addAttribute("mensaje", "Bienvenido a QUÉ");
		return "que";
	}
	@GetMapping("/contacto")
	public String contacto(Model model) {
		model.addAttribute("mensaje", "Bienvenido a CONTACTO");
		return "contacto";
	}
}
