package com.ite.libreria.controller;

import java.math.BigDecimal;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

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
import com.ite.libreria.model.beans.Perfile;
import com.ite.libreria.model.beans.Tema;
import com.ite.libreria.model.beans.Usuario;
import com.ite.libreria.model.dao.LibroDao;
import com.ite.libreria.model.dao.LineasPedidoDao;
import com.ite.libreria.model.dao.PedidoDao;
import com.ite.libreria.model.dao.PerfileDao;
import com.ite.libreria.model.dao.TemaDao;
import com.ite.libreria.model.dao.UsuarioDao;

@Controller
@RequestMapping("/admon")
public class AdmonController {

	@Autowired
	private TemaDao tdao;
	@Autowired
	private LibroDao ldao;
	@Autowired
	private UsuarioDao udao;
	@Autowired
	private PedidoDao pdao;
	@Autowired
	private PerfileDao pfdao;
	@Autowired
	private LineasPedidoDao lpdao;
	
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
	
	@GetMapping("/clientes")
	public String listaClientes(
			Model e) {
		e.addAttribute("listadoClientes", udao.findClientes());
		return "listaClientes";
	}
	
	@GetMapping("/clientes/{username}")
	public String detallesCliente(
			@PathVariable ("username") String username,
			@RequestParam (name="borrar", required=false) String borrar,
			Model e,
			RedirectAttributes attr) {
		
		if (borrar == null) {
			e.addAttribute("cliente", udao.findByUsername(username));	
			e.addAttribute("librosTotalCompradosCliente", udao.librosCliente(username));
			e.addAttribute("importeTotalPedidosCliente", udao.gastoCliente(username));
			e.addAttribute("librosTematicasDiferentesCliente", udao.temasCliente(username));
			return "detalleCliente";
		} else {
			boolean borrado = udao.borrarUsuario(username);
			if (borrado) {
				attr.addFlashAttribute("mensaje", "Usuario \""+ username+ "\" borrado.");
				attr.addFlashAttribute("tipoMensaje", "alert-info");
			} else {
				attr.addFlashAttribute("mensaje", "NO se ha podido al usuario "+ username + ".");
				attr.addFlashAttribute("tipoMensaje", "alert-danger");
			} 
			return "redirect:../clientes";
		}
	}
	
	@GetMapping("/clientes/{username}/activar")
	public String activarCliente(
			@PathVariable ("username") String username) {
		
		udao.activarUsuario(username);
		return "redirect:../{username}";
	}
	
	@GetMapping("/clientes/{username}/desactivar")
	public String desactivarCliente(
			@PathVariable ("username") String username) {
		
		udao.desactivarUsuario(username);
		return "redirect:../{username}";
	}
	
	/*@GetMapping("/pedidos") 
	public String formPedidosDiarios(
			Model e){
		e.addAttribute("listaPedidos", tdao.findTemas());
		return "formPedidosDiarios";
	}
	
	@PostMapping("/pedidos") 
	public String envioFormPedidosDiarios(
			@RequestParam ("fecha") String fecha,
			Model model,
			RedirectAttributes attr){
		
		SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
		try {
			Date diaPedidos = format.parse(fecha);
			model.addAttribute("listaPedidosFecha", pdao.listaPedidosDia(diaPedidos));
			return "listaPedidos";
			
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return "redirect:/";
		}
		
		
	}*/
	
	@GetMapping("/pedidos") 
	public String formPedidosDiarios(
			@RequestParam (required=false) String fecha,
			Model model,
			RedirectAttributes attr){
		if (fecha==null) {
		model.addAttribute("listaPedidos", tdao.findTemas());
		return "formPedidosDiarios";
		} else {
			SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
			try {
				Date diaPedidos = format.parse(fecha);
				model.addAttribute("listaPedidosFecha", pdao.listaPedidosDia(diaPedidos));
				return "listaPedidos";
				
			} catch (ParseException e) {
				e.printStackTrace();
				return "redirect:/";
			}
		}
	}
	
	@GetMapping("/pedidos/{idPedido}")
	public String detallesPedido(
			@PathVariable ("idPedido") int idPedido,
			Model e) {
		
		e.addAttribute("pedido", pdao.pedidoPorId(idPedido));
		e.addAttribute("importeTotalPedidosCliente", pdao.sumaPedido(idPedido));
		return "detallePedido";
	}
	
	@GetMapping("/usuarios")
	public String listaAdmons(
			Model e,
			Authentication auth) {
		e.addAttribute("listadoAdmons", udao.findAdmons());
		e.addAttribute("superAdmon", "Tutankadmon");
		e.addAttribute("activeAdmon", auth.getName());
		return "listaUsuarios";
	}
	

	
	@GetMapping("/usuarios/{username}")
	public String modAdmons(
			Model e,
			RedirectAttributes attr,
			Authentication auth,
			@PathVariable String username,
			@RequestParam (required=false) String activar,
			@RequestParam (required=false) String desactivar,
			@RequestParam (required=false) String borrar) {
		
		if (username.toLowerCase().equals("tutankadmon")) {
			attr.addFlashAttribute("mensaje", "No se puede borrar al súper administrador");
			attr.addFlashAttribute("tipoMensaje", "alert-danger");
		} else if (username.equals(auth.getName())) {
			attr.addFlashAttribute("mensaje", "No se puede borrar el admin activo");
			attr.addFlashAttribute("tipoMensaje", "alert-danger");
		} else {
			if (activar!=null && udao.activarUsuario(username)) {
				attr.addFlashAttribute("mensaje", "Se ha activado al administrador " + username +".");
				attr.addFlashAttribute("tipoMensaje", "alert-success");
			} else if (desactivar!=null && udao.desactivarUsuario(username)) {
				attr.addFlashAttribute("mensaje", "Se ha desactivado al administrador " + username +".");
				attr.addFlashAttribute("tipoMensaje", "alert-success");
			} else if (borrar!=null) {
				udao.borrarUsuario(username);
				attr.addFlashAttribute("mensaje", "Se ha borrado el administrador " + username +".");
				attr.addFlashAttribute("tipoMensaje", "alert-success");
			} else {
				attr.addFlashAttribute("mensaje", "Ha habido un error.");
				attr.addFlashAttribute("tipoMensaje", "alert-danger");
			}
		}
		
		return "redirect:/admon/usuarios";
	}
	
	@GetMapping("/registroAdmon")
	public String registroAdmon() {
		return "formRegistroAdmon";
	}
	
	@PostMapping("/registroAdmon")
	public String registroAdmonForm(
			Model e,
			RedirectAttributes attr,
			@RequestParam ("username") String username,
			@RequestParam ("email") String email,
			@RequestParam ("password") String password,
			@RequestParam ("nombre") String nombre,
			@RequestParam ("apellido") String apellido,
			@RequestParam ("direccion") String direccion) {
		
		Usuario usuarioRegistro = new Usuario();
		Perfile perfilAdmon = pfdao.perfilPorDescripcion("ROL_ADMON");
		List<Perfile> listaPerfilAdmon = new ArrayList<Perfile>();
		listaPerfilAdmon.add(perfilAdmon);
		usuarioRegistro.setUsername(username);
		usuarioRegistro.setEmail(email);
		usuarioRegistro.setPassword("{noop}" + password);
		usuarioRegistro.setNombre(nombre);
		usuarioRegistro.setApellido(apellido);
		usuarioRegistro.setDireccion(direccion);
		usuarioRegistro.setEnabled(1);
		usuarioRegistro.setFechaAlta(new Date());
		usuarioRegistro.setPerfiles(listaPerfilAdmon);
			
		if (udao.addNewUserCliente(usuarioRegistro)) {			
			attr.addFlashAttribute("mensaje", "¡Nuevo administrador registrado!");
			attr.addFlashAttribute("tipoMensaje", "alert-success");
			return "redirect:/admon/usuarios";
		} else {
			e.addAttribute("mensajeAdmon", "El alias <b>" + username + "</b> ya está registrado, utiliza otro distinto.");
			e.addAttribute("tipoMensaje", "alert-danger");
			return "formRegistroAdmon";
		}
	}
	
	
	
	
}
