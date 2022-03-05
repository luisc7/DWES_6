package com.ite.libreria.model.dao;

import java.util.List;

import com.ite.libreria.model.beans.Tema;

public interface TemaDao {
	
	List<Tema> findTemas();
	String nombreTema(String tema);

}
