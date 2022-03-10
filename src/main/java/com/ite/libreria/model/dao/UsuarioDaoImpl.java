package com.ite.libreria.model.dao;

import java.math.BigDecimal;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.ite.libreria.model.beans.Perfile;
import com.ite.libreria.model.beans.Usuario;
import com.ite.libreria.model.repository.UsuarioRepository;

@Repository
public class UsuarioDaoImpl implements UsuarioDao{
	
	@Autowired
	private UsuarioRepository urepo;
	/*@Autowired
	private PerfileRepository prepo;*/

	
	/**
	 * Busca el usuario por su username:
	 * 
	 * @param El username del usuario
	 * @return El Usuario con dicho username. Null si no existe.
	 */
	@Override
	public Usuario findByUsername(String username) {		
		return urepo.findById(username).orElse(null);
	}

	@Override
	public boolean userIsAdmin(Usuario usuario) {
		List<Perfile> perfiles = usuario.getPerfiles();
		boolean isAdmin = false;
		/*for (Perfile perfil : perfiles) {
			if (perfil.getDescripcion().equals("ROL_ADMON"))
				isAdmin = true;
		}*/
		return isAdmin;
	}

	@Override
	public boolean addNewUserCliente(Usuario usuario) {
		if (urepo.findById(usuario.getUsername()).orElse(null) != null) {			
			return false;
		} else {
			urepo.save(usuario);
			return true;
		}
		
	}

	@Override
	public List<Usuario> findClientes() {
		return urepo.listaPorPerfil("ROL_CLIENTE");
	}

	@Override
	public int librosCliente(String username) {
		Integer cantidad = urepo.librosCompradosPorCliente(username);
		if (cantidad!=null)
			return cantidad;
		else
			return 0;
	}

	@Override
	public BigDecimal gastoCliente(String username) {
		BigDecimal gastado = urepo.importeTotalGastadoCliente(username);
		if(gastado == null)
			return (new BigDecimal(0));
		else
			return gastado;
	}

	@Override
	public int temasCliente(String username) {
		Integer temas = urepo.tematicasDiferentesDeCliente(username);
		if (temas == null)
			return 0;
		else
			return temas;
	}

	@Override
	public boolean activarUsuario(String username) {
		Usuario usuario = urepo.findById(username).orElse(null);
		if (usuario == null)
			return false;
		else {
			usuario.setEnabled(1);
			if (urepo.save(usuario) == usuario)
				return true;
			else
				return false;
		}
	}

	@Override
	public boolean desactivarUsuario(String username) {
		Usuario usuario = urepo.findById(username).orElse(null);
		if (usuario == null)
			return false;
		else {
			usuario.setEnabled(0);
			if (urepo.save(usuario) == usuario)
				return true;
			else
				return false;
		}
	}

}
