package com.ite.libreria.model.dao;

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

}
