package com.cenfotec.examen2.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.cenfotec.examen2.domain.Categoria;
import com.cenfotec.examen2.domain.Workshop;
import com.cenfotec.examen2.repo.WorkshopRepository;

@Service
public class WorkshopServiceImpl implements WorkshopService {
	@Autowired
	WorkshopRepository workRepository;
	
	@Autowired
	CategoriaService categoriaService;
	

	@Autowired
	WorkshopService workService;

	@Override
	public void save(Workshop workshop) {
		workRepository.save(workshop);

	}

	@Override
	public Optional<Workshop> get(Long id) {
		return workRepository.findById(id);
	}

	@Override
	public List<Workshop> getAll() {
		return workRepository.findAll();
	}

	public List<Workshop> listCategoria(String categoria) {
		if (categoria != null) {
			Categoria categoriaInterna = categoriaService.getCategoriaByNombre(categoria);
			return workRepository.findAllByCategoria(String.valueOf(categoriaInterna.getId()));
		}
		return workRepository.findAll();
	}
	

}