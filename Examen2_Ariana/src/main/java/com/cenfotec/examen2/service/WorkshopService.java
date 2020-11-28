package com.cenfotec.examen2.service;

import java.util.List;
import java.util.Optional;

import com.cenfotec.examen2.domain.Categoria;
import com.cenfotec.examen2.domain.Workshop;

public interface WorkshopService {
	public void save(Workshop workshop);

	public Optional<Workshop> get(Long id);

		public List<Workshop> getAll();

		public List<Workshop> listCategoria(String categoria);

	
	//  public List<Workshop> listPalabraClave(String palabraClave);
	  
	 

}
