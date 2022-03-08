package com.ite.libreria.model.dao;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.ite.libreria.model.beans.Perfile;
import com.ite.libreria.model.repository.PerfileRepository;

@Repository
public class PerfileDaoImpl implements PerfileDao {
	
	@Autowired
	private PerfileRepository pfrepo;

	@Override
	public Perfile perfilPorDescripcion(String descripcion) {
		Integer idPerfil = pfrepo.perfileId(descripcion);
		return pfrepo.findById(idPerfil).orElse(null);
	}

}
