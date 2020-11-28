package com.cenfotec.examen2.repo;

import org.springframework.data.jpa.repository.JpaRepository;

import com.cenfotec.examen2.domain.Categoria;


public interface CategoriaRepository extends JpaRepository<Categoria, Long>{
	
	Categoria findAllByNombre (String pNombre);
	 
}
