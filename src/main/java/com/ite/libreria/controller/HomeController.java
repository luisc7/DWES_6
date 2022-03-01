package com.ite.libreria.controller;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.ite.libreria.model.dao.LibroDao;

@Controller
@RequestMapping("/")
public class HomeController {
	
	@Autowired
	private LibroDao ldao;
	
	@GetMapping("/")
	/**
	 * Al acceder a /clientes, dirijo a login si no hay sesión de usuario, 
	 * Si el usuario ha iniciado sesión, redirijo a la lista de destacados 
	 */
	
	public String inicio(
			Model e) {
		e.addAttribute("listaNovedades", ldao.findNewReleases());
		return "index";
	}

}
