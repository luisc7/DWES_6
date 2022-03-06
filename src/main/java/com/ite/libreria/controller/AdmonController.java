package com.ite.libreria.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.ite.libreria.model.dao.TemaDao;

@Controller
@RequestMapping("/admon")
public class AdmonController {

	@Autowired
	private TemaDao tdao;
	
	@GetMapping("/altaTema") 
	public String formAltaTema(){
		return "formAltaTema";
	}
	
	@PostMapping("/altaTema") 
	public String envioFormAltaTema(
			@RequestParam ("nombreTema") String nombreTema,
			@RequestParam ("abrevTema") String abrevTema,
			RedirectAttributes attr){
		
		if (tdao.nuevoTema(nombreTema, abrevTema)==true) {

			attr.addFlashAttribute("mensajeTema", "Se ha añadido el  tema "+ nombreTema + " a la BBDD.");
			attr.addFlashAttribute("tipoMensaje", "alert-success");
		} else {
			attr.addFlashAttribute("mensajeTema", "NO se ha añadido "+ nombreTema + " a la BBDD porque el tema ya existe.");
			attr.addFlashAttribute("tipoMensaje", "alert-danger");
		}
		
		
		return "redirect:/cliente/tema";
	}
}
