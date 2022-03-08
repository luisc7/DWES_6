package com.ite.libreria.model.dao;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.ite.libreria.model.beans.Tema;
import com.ite.libreria.model.repository.TemaRepository;

@Repository
public class TemaDaoImpl implements TemaDao{
	
	@Autowired
	private TemaRepository tdao;

	@Override
	public List<Tema> findTemas() {
		return tdao.findAll();
	}

	@Override
	public String nombreTema(String tema) {
		return tdao.nameTopic(tema);
	}

	@Override
	public boolean nuevoTema(String nombreTema, String abreviatura) {
		Tema nuevoTema = new Tema(abreviatura, nombreTema);
		if (tdao.topicExist(nombreTema)==null) {
			tdao.save(nuevoTema);
			return true;
		} else 
			return false;
	}

	@Override
	public Tema temaPorId(int idTema) {
		return tdao.findById(idTema).orElse(null);
	}

}
