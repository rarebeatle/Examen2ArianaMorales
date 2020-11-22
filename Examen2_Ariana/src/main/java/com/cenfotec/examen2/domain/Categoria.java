package com.cenfotec.examen2.domain;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
@Entity
public class Categoria {
	
	@Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;	
	
	private String nombre;
	
	@OneToMany(fetch=FetchType.LAZY, mappedBy="categoria")

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}
	


}
