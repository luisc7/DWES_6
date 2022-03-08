package com.ite.libreria.model.beans;

import java.io.Serializable;
import javax.persistence.*;


/**
 * The persistent class for the temas database table.
 * 
 */
@Entity
@Table(name="temas")
@NamedQuery(name="Tema.findAll", query="SELECT t FROM Tema t")
public class Tema implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name="ID_TEMA")
	private int idTema;

	private String abreviatura;

	@Column(name="DESC_TEMA")
	private String descTema;

	public Tema() {
	}

	public Tema(String abreviatura, String descTema) {
		super();
		this.abreviatura = abreviatura;
		this.descTema = descTema;
	}

	public int getIdTema() {
		return this.idTema;
	}

	public void setIdTema(int idTema) {
		this.idTema = idTema;
	}

	public String getAbreviatura() {
		return this.abreviatura;
	}

	public void setAbreviatura(String abreviatura) {
		this.abreviatura = abreviatura;
	}

	public String getDescTema() {
		return this.descTema;
	}

	public void setDescTema(String descTema) {
		this.descTema = descTema;
	}

	@Override
	public String toString() {
		return "Tema [idTema=" + idTema + ", abreviatura=" + abreviatura + ", descTema=" + descTema + "]";
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((descTema == null) ? 0 : descTema.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (!(obj instanceof Tema))
			return false;
		Tema other = (Tema) obj;
		if (descTema == null) {
			if (other.descTema != null)
				return false;
		} else if (!descTema.equals(other.descTema))
			return false;
		return true;
	}

}