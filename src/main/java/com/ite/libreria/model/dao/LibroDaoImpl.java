package com.ite.libreria.model.dao;

import java.util.List;
import java.util.Optional;

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

	@Override
	public Boolean addNuevoLibro(Libro libroNuevo) {
		
		System.out.println(libroNuevo.getIsbn());
		
		System.out.println(lrepo.findById(libroNuevo.getIsbn()));
		
		if (lrepo.existsById(libroNuevo.getIsbn())) {
			return false;
		} else {
			lrepo.save(libroNuevo);
			return true;
		}
	}

	@Override
	public Boolean suprLibro(long isbn) {
		lrepo.deleteById(isbn);
		return true;
	}

	@Override
	public Boolean modifLibro(Libro libroModif) {
		lrepo.save(libroModif);
		return true;
	}
}
