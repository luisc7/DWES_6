package com.ite.libreria.model.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.ite.libreria.model.beans.Libro;

public interface LibroRepository extends JpaRepository<Libro, Long>{
	
	@Query(
			"select lib from Libro lib "
			+ "where lib.tema.abreviatura = ?1")
	public List<Libro> buscarTema(String tema);

	@Query(
			"select lib from Libro lib "
			+ "where lib.titulo like %?1% ")
	public List<Libro> buscarLibro(String cadenaBusqueda);

	@Query(
			"select lib from Libro lib "
			+ "where lib.novedad = 'N' ")
	public List<Libro> buscarNovedades();

}
