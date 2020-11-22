package com.cenfotec.examen2.domain;

import java.time.LocalTime;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

@Entity
public class Actividad {

	@Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;
	
	private String nombre;
	
	private String descripcion;
	
	private String textoMostrado;
	
	private LocalTime tiempo;
	
	@ManyToOne
    @JoinColumn(name="cart_id", nullable=false)
	private Workshop workshop;
	
	
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

	public String getDescripcion() {
		return descripcion;
	}

	public void setDescripcion(String descripcion) {
		this.descripcion = descripcion;
	}

	public String getTextoMostrado() {
		return textoMostrado;
	}

	public void setTextoMostrado(String textoMostrado) {
		this.textoMostrado = textoMostrado;
	}

	public Workshop getWorkshop() {
		return workshop;
	}

	public void setWorkshop(Workshop workshop) {
		this.workshop = workshop;
	}

	public LocalTime getTiempo() {
		return tiempo;
	}

	public void setTiempo(LocalTime tiempo) {
		this.tiempo = tiempo;
	}
	
	
}
