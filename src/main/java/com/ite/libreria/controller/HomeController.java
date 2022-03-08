package com.ite.libreria.controller;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.ite.libreria.model.beans.Perfile;
import com.ite.libreria.model.beans.Usuario;
import com.ite.libreria.model.dao.LibroDao;
import com.ite.libreria.model.dao.PerfileDao;
import com.ite.libreria.model.dao.UsuarioDao;

@Controller
@RequestMapping("/")
public class HomeController {
	
	@Autowired
	private LibroDao ldao;
	
	@Autowired
	private UsuarioDao udao;
	
	@Autowired
	private PerfileDao pfdao; 
	
	@GetMapping("/")
	public String inicio(
			Model model,
			HttpSession sesionUsuario) {
		
			model.addAttribute("listaNovedades", ldao.findNewReleases());
			model.addAttribute("h1pagina", "Últimas novedades");
			sesionUsuario.setAttribute("ultimaVista", "/");
			return "index";
	}
	
	@GetMapping("/registro")
	public String registro() {
			return "formRegistro";
	}
	
	@PostMapping("/registro")
	public String registroForm(
			Model e,
			RedirectAttributes attr,
			@RequestParam ("username") String username,
			@RequestParam ("email") String email,
			@RequestParam ("password") String password,
			@RequestParam ("nombre") String nombre,
			@RequestParam ("apellido") String apellido,
			@RequestParam ("direccion") String direccion) {
		
		Usuario usuarioRegistro = new Usuario();
		/*Perfile perfilCliente = pdao.perfilPorDescripcion("ROL_CLIENTE");*/
		Perfile perfilCliente = new Perfile(2, "ROL_CLIENTE");
		List<Perfile> listaPerfilCliente = new ArrayList<Perfile>();
		listaPerfilCliente.add(perfilCliente);
		/*if (urepo.findByUsername(username)==null) {*/
		usuarioRegistro.setUsername(username);
		usuarioRegistro.setEmail(email);
		usuarioRegistro.setPassword("{noop}" + password);
		usuarioRegistro.setNombre(nombre);
		usuarioRegistro.setApellido(apellido);
		usuarioRegistro.setDireccion(direccion);
		usuarioRegistro.setEnabled(1);
		usuarioRegistro.setFechaAlta(new Date());
		usuarioRegistro.setPerfiles(listaPerfilCliente);
			
		if (udao.addNewUserCliente(usuarioRegistro)) {			
			attr.addFlashAttribute("mensajeCliente", "¡Ya estás registrado! <a href=\"/login\">Inicia sesión</a> para añadir libros a tu carrito.");
			attr.addFlashAttribute("tipoMensaje", "alert-success");
			return "redirect:/";
		} else {
			e.addAttribute("mensajeCliente", "El alias ya está registrado, utiliza otro distinto.");
			e.addAttribute("tipoMensaje", "alert-danger");
			return "formRegistro";
		}
	}
	
	
	@GetMapping("/acceso")
	public String acceso(Model model) {
		/**
		 *  Envía al jsp de login
		 */
		return "acceso";
	}
	
	@PostMapping("/acceso")
	public String procesarAcceso(
			HttpSession sesionUsuario,
			RedirectAttributes attr, 
			Model model, 
			@RequestParam("username") String username, 
			@RequestParam("password") String password ) {
		/**
		 * Al envío del formulario de login se contemplan tres escenarios:
		 * - Usuario y contraseña correctos
		 * - Usuario no existe
		 * - Contraseña incorrecta (usuario existe)
		 */
		Usuario usuarioActivo = udao.findByUsername(username);
		
		if (usuarioActivo == null) {
			model.addAttribute("mensajeLogin", "El usuario no existe");
			return "acceso";
		} else if (usuarioActivo.getPassword().equals(password)) {
			attr.addFlashAttribute("mensajeLogin", "Inicio de sesión correcto");	
			sesionUsuario.setAttribute("usuarioActivo", usuarioActivo);
			return "redirect:/";						
		} else {
			model.addAttribute("mensajeLogin", "La contraseña no es correcta");
			return "acceso";			
		}
	}
	
	
	
	
	@GetMapping("/cliente")
	/**
	 * Al acceder a /cliente, dirijo a login si no hay sesión de usuario, 
	 * Si el usuario ha iniciado sesión, redirijo a la lista de destacados 
	 */
	
	public String inicio(HttpSession sesionUsuario) {
		
		if (sesionUsuario.getAttribute("usuarioActivo") == null)
			return "redirect:/acceso";
		else
			return "redirect:/";
	}

}
