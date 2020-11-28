package com.cenfotec.examen2.service;

import java.util.List;
import java.util.Optional;

import org.springframework.dao.DataAccessException;

import com.cenfotec.examen2.domain.Categoria;
import com.cenfotec.examen2.domain.Workshop;

public interface CategoriaService {
	public void save(Categoria categoria);
	public Optional<Categoria> get(Long id);
	public List<Categoria> getAll();
	public Categoria getCategoriaByNombre (String pCategoria);

	
}
