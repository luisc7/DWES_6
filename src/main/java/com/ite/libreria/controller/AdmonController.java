package com.ite.libreria.controller;

import java.math.BigDecimal;

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
import com.ite.libreria.model.beans.Tema;
import com.ite.libreria.model.dao.LibroDao;
import com.ite.libreria.model.dao.TemaDao;

@Controller
@RequestMapping("/admon")
public class AdmonController {

	@Autowired
	private TemaDao tdao;
	@Autowired
	private LibroDao ldao;
	
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
	
	@GetMapping("/altaLibro") 
	public String formAltaLibro(
			Model e){
		e.addAttribute("listaTemas", tdao.findTemas());
		return "formAltaLibro";
	}
	
	@PostMapping("/altaLibro") 
	public String envioFormAltaLibro(
			@RequestParam ("ISBN") Long isbn,
			@RequestParam ("nombreLibro") String nombreLibro,
			@RequestParam ("autor") String autor,
			@RequestParam ("paginas") int paginas,
			@RequestParam ("precio") BigDecimal precio,
			@RequestParam ("novedad") String novedad,
			@RequestParam ("tema") int tema,
			RedirectAttributes attr){
		
		Tema temaNuevoLibro = tdao.temaPorId(tema);		
		Libro nuevoLibro = new Libro(isbn, autor, novedad, paginas, precio, nombreLibro, temaNuevoLibro);
				
		if (ldao.addNuevoLibro(nuevoLibro)==true) {
			
			attr.addFlashAttribute("mensajeTema", "Se ha añadido el libro "+ nombreLibro + " a la BBDD.");
			attr.addFlashAttribute("tipoMensaje", "alert-success");
			return ("redirect:/cliente/tema/" + temaNuevoLibro.getAbreviatura());
			
		} else {
			attr.addFlashAttribute("mensajeTema", "NO se ha podido añadir "+ nombreLibro + " a la BBDD.");
			attr.addFlashAttribute("tipoMensaje", "alert-danger");
			return "redirect:/cliente/tema";
		}
	}
	
	@GetMapping("/borrarLibro/{isbn}") 
	public String borrarLibro(
			@PathVariable ("isbn") Long isbn,
			Model e,
			RedirectAttributes attr){
		
		Libro libroBorrar = ldao.findByIsbn(isbn);
		
		if (libroBorrar != null) {
			Tema temaLibro = tdao.temaPorId(libroBorrar.getTema().getIdTema());
			
			ldao.suprLibro(isbn);
			
			attr.addFlashAttribute("mensajeTema", "Se ha borrado el libro " + libroBorrar.getTitulo() + " cuyo ISBN es "+ libroBorrar.getIsbn() + " de la BBDD.");
			attr.addFlashAttribute("tipoMensaje", "alert-success");
			return ("redirect:/cliente/tema/" + temaLibro.getAbreviatura());
		} else {
			attr.addFlashAttribute("mensajeTema", "No se he podido borrar el libro de la BBDD.");
			attr.addFlashAttribute("tipoMensaje", "alert-danger");
			return ("redirect:/cliente/tema");			
		}
	}
	
	@GetMapping("/modificarLibro/{isbn}") 
	public String modificarLibro(
			@PathVariable ("isbn") Long isbn,
			Model e,
			RedirectAttributes attr){
		
		Libro libroModificar = ldao.findByIsbn(isbn);
		
		if (libroModificar != null) {
			e.addAttribute("libroModificar", libroModificar);
			e.addAttribute("listaTemas", tdao.findTemas());
			return "formModifLibro";
			/*Tema temaLibro = tdao.temaPorId(libroModificar.getTema().getIdTema());
			
			ldao.suprLibro(isbn);
			
			attr.addFlashAttribute("mensajeTema", "Se ha modificado el libro " + libroModificar.getTitulo() + " cuyo ISBN es "+ libroBorrar.getIsbn() + " de la BBDD.");
			attr.addFlashAttribute("tipoMensaje", "alert-success");
			return ("redirect:/cliente/tema/" + temaLibro.getAbreviatura());*/
		} else {
			attr.addFlashAttribute("mensajeTema", "No se he podido borrar el libro de la BBDD.");
			attr.addFlashAttribute("tipoMensaje", "alert-danger");
			return ("redirect:/cliente/tema");			
		}
	}
	
	@PostMapping("/modificarLibro/{isbn}") 
	public String envioFormModifLibro(
			@RequestParam ("ISBN") Long isbn,
			@RequestParam ("nombreLibro") String nombreLibro,
			@RequestParam ("autor") String autor,
			@RequestParam ("paginas") int paginas,
			@RequestParam ("precio") BigDecimal precio,
			@RequestParam ("novedad") String novedad,
			@RequestParam ("tema") int tema,
			RedirectAttributes attr){
		
		Tema temaNuevoLibro = tdao.temaPorId(tema);		
		Libro nuevoLibro = new Libro(isbn, autor, novedad, paginas, precio, nombreLibro, temaNuevoLibro);
				
		if (ldao.modifLibro(nuevoLibro)==true) {
			
			attr.addFlashAttribute("mensajeTema", "Se ha modificado el libro con ISBN "+ isbn + " en la BBDD.");
			attr.addFlashAttribute("tipoMensaje", "alert-success");
			return ("redirect:/cliente/tema/" + temaNuevoLibro.getAbreviatura());
			
		} else {
			attr.addFlashAttribute("mensajeTema", "NO se ha podido modificar el libro con ISBN "+ isbn + ".");
			attr.addFlashAttribute("tipoMensaje", "alert-danger");
			return "redirect:/cliente/tema";
		}
	}
	
}
