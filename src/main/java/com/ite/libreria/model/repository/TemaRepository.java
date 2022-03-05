package com.ite.libreria.model.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.ite.libreria.model.beans.Tema;

public interface TemaRepository extends JpaRepository<Tema, Integer>{
	
	@Query("select t.descTema from Tema t where t.abreviatura = ?1")
	public String nameTopic(String temaAbreviado);
	
}
