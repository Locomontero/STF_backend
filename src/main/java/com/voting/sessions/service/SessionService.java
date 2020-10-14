package com.voting.sessions.service;

import java.util.List;

import com.voting.sessions.entity.Session;
import com.voting.sessions.form.SessionForm;

public interface SessionService {
	
	Session openSession(SessionForm sessionForm);

	boolean isOpen(Long agendaId);
	
	List<Session> closeSessions();
}
