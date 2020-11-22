package com.cenfotec.examen2.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.cenfotec.examen2.domain.Actividad;
import com.cenfotec.examen2.repo.ActividadRepository;

@Service
public class ActividadServiceImpl implements ActividadService {

	@Autowired
	ActividadRepository actiRepository;
	
	public void save(Actividad actividad) {
		actiRepository.save(actividad);
	}

	@Override
	public List<Actividad> getAll() {
		return actiRepository.findAll();
	}


}