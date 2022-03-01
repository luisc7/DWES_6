package com.ite.libreria.model.dao;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.ite.libreria.model.beans.Libro;
import com.ite.libreria.model.repository.LibroRepository;

@Repository
public class LibroDaoImpl implements LibroDao {
	
	@Autowired 
	private LibroRepository lrepo;
	
	@Override
	public List<Libro> findByTopic(String temaAbreviado){
		return lrepo.buscarTema(temaAbreviado);		
	}

	@Override
	public List<Libro> findByName(String cadenaBusqueda) {
		return lrepo.buscarLibro(cadenaBusqueda);
	}

	@Override
	public Libro findByIsbn(long isbn) {
		return lrepo.findById(isbn).orElse(null);
	}

	@Override
	public List<Libro> findNewReleases() {
		return lrepo.buscarNovedades();
	}
}
