package com.cenfotec.examen2.service;


import java.util.List;

import com.cenfotec.examen2.domain.Actividad;


public interface ActividadService {
	
	public void save(Actividad actividad);
	public List<Actividad> getAll();

	
}