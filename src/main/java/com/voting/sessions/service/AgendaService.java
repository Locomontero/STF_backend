package com.voting.sessions.service;

import com.voting.sessions.entity.Agenda;

public interface AgendaService {

	Agenda save(Agenda agenda);
	
	Agenda findById(Long id);
	
	boolean existsById(Long id);
}
