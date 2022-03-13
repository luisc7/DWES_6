package com.ite.libreria.model.dao;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.ite.libreria.model.beans.Tema;
import com.ite.libreria.model.repository.TemaRepository;

@Repository
public class TemaDaoImpl implements TemaDao{
	
	@Autowired
	private TemaRepository trepo;

	@Override
	public List<Tema> findTemas() {
		return trepo.findAll();
	}

	@Override
	public String nombreTema(String tema) {
		return trepo.nameTopic(tema);
	}

	@Override
	public int nuevoTema(String nombreTema, String abreviatura) {
		Tema nuevoTema = new Tema(abreviatura.toUpperCase(), nombreTema);
		if (trepo.topicExist(nombreTema)!=null) {
			return -1;
		} else if (trepo.topicAbrevExist(abreviatura)!=null) {
			return 0;
		} else {
			trepo.save(nuevoTema);
			return 1;
		}
	}

	@Override
	public Tema temaPorId(int idTema) {
		return trepo.findById(idTema).orElse(null);
	}

}
