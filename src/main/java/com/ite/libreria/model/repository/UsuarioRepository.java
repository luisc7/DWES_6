package com.ite.libreria.model.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.ite.libreria.model.beans.Usuario;

public interface UsuarioRepository extends JpaRepository<Usuario, String>{
	
	/*@Query("select usr from Usuario usr inner join usr.perfiles up inner join Perfile p where p.descripcion= ?1")
	public List<Usuario> listaPorPerfil(String descripcionPerfil);*/
	
	/*@Query(
			"select usr from Usuario usr "
			+ "inner join usr.perfiles up "
			+ "where up.idPerfil = 2")
	public List<Usuario> listaPorPerfil(String descripcionPerfil);*/
	
	@Query(
			"select usr from Usuario usr "
			+ "inner join usr.perfiles up "
			+ "inner join Perfile p "
			+ "where p.descripcion =?1")
	public List<Usuario> listaPorPerfil(String descripcionPerfil);

}
