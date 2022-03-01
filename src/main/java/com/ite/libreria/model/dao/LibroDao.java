package com.ite.libreria.model.dao;

import java.util.List;

import com.ite.libreria.model.beans.Libro;

public interface LibroDao {

	List<Libro> findByTopic(String temaAbreviado);
	List<Libro> findByName(String cadenaBusqueda);
	Libro findByIsbn(long isbnl);
	List<Libro> findNewReleases();

}
