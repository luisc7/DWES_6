package com.ite.libreria.controller;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.ite.libreria.model.beans.Libro;
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
			Model e,
			HttpSession sesionUsuario) {
		e.addAttribute("listadoTemas", tdao.findTemas());
		e.addAttribute("tituloPagina", "Listado de los temas disponibles");
		sesionUsuario.setAttribute("ultimaVista", "/cliente/tema");
		return "listaTemas";
	}
	
	@GetMapping("/tema/{temaAbr}")
	public String temaLibros(
			@PathVariable ("temaAbr") String temaAbreviado,
			Model e,
			HttpSession sesionUsuario) {
		e.addAttribute("listadoLibros", ldao.findByTopic(temaAbreviado));
		e.addAttribute("tituloPagina", "Libros de "+ tdao.nombreTema(temaAbreviado));
		e.addAttribute("h1pagina", "Todos los libros de "+ tdao.nombreTema(temaAbreviado));
		sesionUsuario.setAttribute("ultimaVista", "/cliente/tema/" + temaAbreviado);
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
		e.addAttribute("tituloPagina", "Libros  \""+ cadenaBusqueda + "\"");
		e.addAttribute("h1pagina", "Búsqueda de libros cuyo título contiene \""+ cadenaBusqueda + "\"");
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
	
	@GetMapping("/addCarrito/{isbn}")
	public String libroAddCarrito(
			@PathVariable ("isbn") Long isbn,
			RedirectAttributes attr,
			HttpSession sesionUsuario) {
		List<Libro> carrito  = (List<Libro>) sesionUsuario.getAttribute("carrito");
		Libro libroAdd = ldao.findByIsbn(isbn);
		if (carrito==null) {
			carrito = new ArrayList<Libro>();
		}
		
		if (!carrito.contains(libroAdd))
			carrito.add(libroAdd);
		else
			attr.addFlashAttribute("mensajeCarrito", "El libro "+ libroAdd.getTitulo() + " ya estaba en el carrito");
		sesionUsuario.setAttribute("carrito", carrito);
		return "redirect:/cliente/verCarrito";
	}
	
	@GetMapping("/eliminar/{isbn}")
	public String libroBorrarCarrito(
			@PathVariable ("isbn") Long isbn,
			RedirectAttributes attr,
			HttpSession sesionUsuario) {
		List<Libro> carrito  = (List<Libro>) sesionUsuario.getAttribute("carrito");
		carrito.remove(ldao.findByIsbn(isbn));

		attr.addFlashAttribute("mensajeCarrito", "El libro "+ ldao.findByIsbn(isbn).getTitulo() + " ha sido borrado del carrito");
		sesionUsuario.setAttribute("carrito", carrito);
		return "redirect:/cliente/verCarrito";
	}

}
