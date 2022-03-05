package com.ite.libreria.controller;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.ite.libreria.model.beans.Usuario;
import com.ite.libreria.model.dao.LibroDao;
import com.ite.libreria.model.dao.UsuarioDao;

@Controller
@RequestMapping("/")
public class HomeController {
	
	@Autowired
	private LibroDao ldao;
	
	@Autowired
	private UsuarioDao urepo;
	
	@GetMapping("/")
	/**
	 * Al acceder a /clientes, dirijo a login si no hay sesión de usuario, 
	 * Si el usuario ha iniciado sesión, redirijo a la lista de destacados 
	 */
	
	public String inicio(
			Model model,
			HttpSession sesionUsuario) {
		
			model.addAttribute("listaNovedades", ldao.findNewReleases());
			return "index";
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
		Usuario usuarioActivo = urepo.findByUsername(username);
		
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
	
	@GetMapping("/cerrarSesion")
	public String cierre(
			HttpSession sesionUsuario,
			Model model	) {
		/**
		 * Cierra la sesión borrando el atributo del usuarioActivo,
		 * y vuelve a acceso
		 */
		
		sesionUsuario.removeAttribute("usuarioActivo");
		return "redirect:/acceso";
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
