package com.ite.libreria.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.ite.libreria.model.dao.LibroDao;
import com.ite.libreria.model.dao.PedidoDao;
import com.ite.libreria.model.dao.TemaDao;

@Controller
@RequestMapping("/cliente")
public class ClienteController {
	
	@Autowired
	private LibroDao ldao;
	@Autowired
	private TemaDao tdao;
	@Autowired
	private PedidoDao pdao;
	
	@GetMapping("/tema")
	public String tema(
			Model e) {
		e.addAttribute("listadoTemas", tdao.findTemas());
		return "listaTemas";
	}
	
	@GetMapping("/tema/{temaAbr}")
	public String temaLibros(
			@PathVariable ("temaAbr") String temaAbreviado,
			Model e) {
		e.addAttribute("listadoLibros", ldao.findByTopic(temaAbreviado));
		return "listaLibros";
	}
	
	@GetMapping("/buscar")
	public String buscar() {
		return "formBusqueda";
	}
	
	@PostMapping("/buscar")
	public String buscar(
			@RequestParam ("busqueda") String cadenaBusqueda,
			Model e) {
		e.addAttribute("listadoLibros", ldao.findByName(cadenaBusqueda));
		return "listaLibros";
	}
	
	@GetMapping("/verDetalle/{isbn}")
	public String detalle(
			@PathVariable ("isbn") String isbn,
			Model e) {
		e.addAttribute("libroEntero", ldao.findByIsbn(Long.parseLong(isbn)));
		return "detalleLibro";
	}
	
	@GetMapping("/verCarrito")
	public String carrito(
			Model e) {
		
		return "carrito";
	}

}
