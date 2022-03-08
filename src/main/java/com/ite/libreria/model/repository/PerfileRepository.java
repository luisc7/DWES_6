package com.ite.libreria.model.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.ite.libreria.model.beans.Perfile;

public interface PerfileRepository extends JpaRepository<Perfile, Integer>{
	
	@Query("select p.idPerfil from Perfile p where p.descripcion = ?1")
	public Integer perfileId(String descripcion);
}
