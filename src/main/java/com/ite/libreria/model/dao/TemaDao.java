package com.ite.libreria.model.dao;

import java.util.List;

import com.ite.libreria.model.beans.Tema;

public interface TemaDao {
	
	List<Tema> findTemas();
	Tema temaPorId(int idTema);
	String nombreTema(String tema);
	boolean nuevoTema(String nombreTema, String abreviatura);
}
