package com.ite.libreria.controller;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.ite.libreria.model.beans.Libro;
import com.ite.libreria.model.beans.LineasPedido;
import com.ite.libreria.model.beans.Pedido;
import com.ite.libreria.model.beans.Usuario;
import com.ite.libreria.model.dao.LibroDao;
import com.ite.libreria.model.dao.PedidoDao;
import com.ite.libreria.model.dao.TemaDao;
import com.ite.libreria.model.dao.UsuarioDao;

@Controller
@RequestMapping("/cliente")
public class ClienteController {
	
	@Autowired
	private LibroDao ldao;
	@Autowired
	private TemaDao tdao;
	@Autowired
	private PedidoDao pdao;
	@Autowired
	private UsuarioDao udao;
	
	@GetMapping("/tema")
	public String tema(
			Model e,
			HttpSession sesionUsuario) {
		e.addAttribute("listadoTemas", tdao.findTemas());
		e.addAttribute("tituloPagina", "Listado de los temas disponibles");
		e.addAttribute("h1pagina", "Listado de temas disponibles");
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
		
		if (!carrito.contains(libroAdd)) {
			carrito.add(libroAdd);
			attr.addFlashAttribute("mensajeCarrito", "Se ha añadido el  libro "+ libroAdd.getTitulo() + " a tu carrito.");
			attr.addFlashAttribute("tipoMensaje", "alert-success");
		} else
			attr.addFlashAttribute("mensajeCarrito", "El libro "+ libroAdd.getTitulo() + " ya estaba en el carrito.");
		attr.addFlashAttribute("tipoMensaje", "alert-warning");
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
		attr.addFlashAttribute("tipoMensaje", "alert-info");
		sesionUsuario.setAttribute("carrito", carrito);
		return "redirect:/cliente/verCarrito";
	}
	
	
	@GetMapping("/comprar")
	public String comprar(
			Model e,
			RedirectAttributes attr,
			HttpSession sesionUsuario,
			Authentication auth) {
		
		Usuario cliente = udao.findByUsername(auth.getName());
		
		Pedido pedidoWeb = new Pedido(cliente.getDireccion(), "En curso", new Date(), null, cliente);
		List<Libro> carrito  = (List<Libro>) sesionUsuario.getAttribute("carrito");
		List<LineasPedido> lineaPedido = new ArrayList<LineasPedido>();
		if (carrito == null) {
			attr.addFlashAttribute("mensajeCliente", "¡No tienes libros en el carrito! Para comprar, debes añadir antes los libros que quieras al carrito.");
			attr.addFlashAttribute("tipoMensaje", "alert-danger");
			return "redirect:/";
		} else {
			for (Libro libroCarrito : carrito) {
				LineasPedido lp = new LineasPedido();
				
				lp.setLibro(libroCarrito);
				lp.setCantidad(1);
				lp.setFechaAlta(new Date());
				lp.setPedido(pedidoWeb);
				lp.setPrecioVenta(libroCarrito.getPrecioUnitario());				
							
				lineaPedido.add(lp);
			}
			
			pedidoWeb.setLineasPedidos(lineaPedido);			
			pedidoWeb.setEstado("Completado");
			
			pdao.nuevoPedido(pedidoWeb);
			sesionUsuario.setAttribute("carrito", null);
			
			attr.addFlashAttribute("mensajeCliente", "¡Has hecho tu pedido! En unos días los recibirás");
			attr.addFlashAttribute("tipoMensaje", "alert-success");
			return "redirect:/";
		}
		
	}

}
