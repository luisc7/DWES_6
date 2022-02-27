package com.ite.libreria.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import com.ite.libreria.model.dao.LibroDao;

@Controller
@RequestMapping("/cliente")
public class ClienteController {
	
	@Autowired
	private LibroDao ldao;
	
	@GetMapping("/tema/{temaAbr}")
	public String tema(
			@PathVariable ("temaAbr") String temaAbreviado,
			Model e) {
		e.addAttribute("listadoLibros", ldao.findByTopic(temaAbreviado));
		return "listaLibros";
	}

}
