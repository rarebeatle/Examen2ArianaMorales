package com.cenfotec.examen2.repo;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.cenfotec.examen2.domain.Categoria;
import com.cenfotec.examen2.domain.Workshop;

public interface WorkshopRepository extends JpaRepository<Workshop, Long> {

	public List<Workshop> findAllByCategoria(String categoria);


	// public List<Workshop> findAllByCategoria(String categoria);

}
