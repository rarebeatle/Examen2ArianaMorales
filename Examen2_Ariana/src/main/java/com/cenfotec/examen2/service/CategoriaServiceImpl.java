package com.cenfotec.examen2.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.stereotype.Service;

import com.cenfotec.examen2.domain.Categoria;
import com.cenfotec.examen2.domain.Workshop;
import com.cenfotec.examen2.repo.CategoriaRepository;

@Service
public class CategoriaServiceImpl implements CategoriaService{

	@Autowired
	CategoriaRepository cateRepository;
	
	@Override
	public void save(Categoria categoria) {
		cateRepository.save(categoria);
		
	}
	
	@Override
	public Optional<Categoria> get(Long id) {
		return cateRepository.findById(id);

	}

	@Override
	public List<Categoria> getAll() {
		return cateRepository.findAll();

	}

	
}
