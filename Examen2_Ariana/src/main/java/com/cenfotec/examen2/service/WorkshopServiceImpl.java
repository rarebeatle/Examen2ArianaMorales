package com.cenfotec.examen2.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.cenfotec.examen2.domain.Workshop;
import com.cenfotec.examen2.repo.WorkshopRepository;

@Service
public class WorkshopServiceImpl implements WorkshopService {
	@Autowired
	WorkshopRepository workRepository;
	
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


}
