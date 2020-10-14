package com.voting.sessions.form;

import java.time.LocalDateTime;
import java.util.Optional;

import com.voting.sessions.entity.Agenda;
import com.voting.sessions.entity.Session;

import lombok.Data;

@Data
public class SessionForm {

	private Long agendaId;
	
	private Long minutesDuration;
	
	public Session formToSessionEntity(Agenda agenda) {
		LocalDateTime startDateSession = LocalDateTime.now();
		return Session.builder()
				.agenda(agenda)
				.startDateSession(startDateSession)
				.endDateSession(validateEndDateSession(minutesDuration, startDateSession))
				.closedSession(false).build();
	}
	
	private LocalDateTime validateEndDateSession(Long endDateSession, LocalDateTime startDateSession) {
		return Optional.ofNullable(endDateSession)
				.map(minutes -> startDateSession.plusMinutes(minutes))
				.orElse(startDateSession.plusMinutes(1));
	}
}
