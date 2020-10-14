package com.voting.sessions.service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.voting.sessions.Exception.NotFoundException;
import com.voting.sessions.Exception.SessionAlreadyCreatedException;
//import com.voting.sessions.Scheduler.TaskSchedulerVote;
import com.voting.sessions.entity.Agenda;
import com.voting.sessions.entity.Session;
import com.voting.sessions.form.SessionForm;
import com.voting.sessions.repository.SessionRepository;

@Service
public class SessionServiceImpl implements SessionService{

	@Autowired
	AgendaService agendaService;
	
	@Autowired
	SessionRepository sessionRepository;
	
	@Override
	public Session openSession(SessionForm sessionForm) {
		Agenda agenda = agendaService.findById(sessionForm.getAgendaId());
		if(sessionRepository.existsById(agenda.getId())) {
			throw new SessionAlreadyCreatedException("Session for this agenda already exists.");
		}
		Session session = sessionForm.formToSessionEntity(agenda);
		Session openedSession = sessionRepository.save(session);
		return openedSession;
	}


	@Override
	public boolean isOpen(Long agendaId) {
		if(agendaService.existsById(agendaId)) {
			Session session = Optional.ofNullable(sessionRepository.findByAgenda_Id(agendaId))
					.get()
					.orElseThrow(() -> new NotFoundException(String.format("Session not found", agendaId)));
			
			boolean open = LocalDateTime.now().isBefore(session.getEndDateSession());
			
			return open;
		}
		
		throw new NotFoundException(String.format("Agenda with id %s not found", agendaId));
	}
	
	@Override
	public List<Session> closeSessions() {
		List<Session> sessions = sessionRepository.findEndedSessions(LocalDateTime.now(), false);
		
		return sessions.stream().map(session -> {
			session.setClosedSession(true);
			return sessionRepository.save(session);
		}).collect(Collectors.toList());
	}
}
