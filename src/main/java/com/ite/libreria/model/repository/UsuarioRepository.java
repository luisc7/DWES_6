package com.ite.libreria.model.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.ite.libreria.model.beans.Usuario;

public interface UsuarioRepository extends JpaRepository<Usuario, String>{

}
