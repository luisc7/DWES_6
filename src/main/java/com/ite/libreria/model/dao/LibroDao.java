package com.ite.libreria.model.dao;

import java.util.List;

import com.ite.libreria.model.beans.Libro;

public interface LibroDao {

	List<Libro> findByTopic(String temaAbreviado);
	List<Libro> findByName(String cadenaBusqueda);
	Libro findByIsbn(long isbn);
	List<Libro> findNewReleases();
	Boolean addNuevoLibro(Libro libroNuevo);
	Boolean modifLibro(Libro libroModif);
	Boolean suprLibro(long isbn);
}
