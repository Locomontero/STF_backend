package com.voting.sessions.dto;

import java.time.LocalDateTime;

import com.voting.sessions.entity.Session;

import lombok.Data;

@Data
public class SessionDto {

	private Long id;
	
	private LocalDateTime startSessionDate;
	
	private LocalDateTime endSessionDate;
	
	private boolean closedSession;
	
	private String agenda;
	
	public SessionDto(Session session) {
		this.id = session.getId();
		this.startSessionDate = session.getStartDateSession();
		this.endSessionDate = session.getEndDateSession();
		this.closedSession = session.isClosedSession();
		this.agenda = session.getAgenda().getName();
	}
}
