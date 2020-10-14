package com.voting.sessions.service;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.voting.sessions.Exception.NotFoundException;
import com.voting.sessions.entity.Agenda;
import com.voting.sessions.repository.AgendaRepository;

@Service
public class AgendaServiceImpl implements AgendaService{

	@Autowired
	AgendaRepository agendaRepository;
	
	@Override
	public Agenda save(Agenda agenda) {
		return agendaRepository.save(agenda);
	}

	@Override
	public Agenda findById(Long id) {
		return Optional.ofNullable(agendaRepository.findById(id))
				.get()
				.orElseThrow(() -> new NotFoundException(String.format("Agenda with id %s not found", id)));
	}
	
	@Override
	public boolean existsById(Long id) {
		return agendaRepository.existsById(id);
	}

}
