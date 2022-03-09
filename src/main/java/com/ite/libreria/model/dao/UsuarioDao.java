package com.ite.libreria.model.dao;

import java.util.List;

import com.ite.libreria.model.beans.Usuario;

public interface UsuarioDao {
	Usuario findByUsername(String username);
	boolean userIsAdmin(Usuario usuario);
	boolean addNewUserCliente(Usuario usuario);
	List<Usuario> findClientes();
}
