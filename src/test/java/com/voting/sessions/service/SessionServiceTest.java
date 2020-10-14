package com.voting.sessions.service;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.when;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import com.voting.sessions.Exception.NotFoundException;
import com.voting.sessions.Exception.SessionAlreadyCreatedException;
import com.voting.sessions.entity.Agenda;
import com.voting.sessions.entity.Session;
import com.voting.sessions.form.SessionForm;
import com.voting.sessions.repository.SessionRepository;

@ExtendWith(SpringExtension.class)
public class SessionServiceTest {

	@Mock
	SessionRepository sessionRepository;
	
	@Mock
	AgendaServiceImpl agendaServiceImpl;
	
	@InjectMocks
	SessionServiceImpl sessionServiceImpl;
	
	@Test
	public void shoudCreateNewSession() {
		Agenda agenda = new Agenda();
		agenda.setId(3L);
		agenda.setName("Agenda for Session");
		when(agendaServiceImpl.findById(Mockito.anyLong())).thenReturn(agenda);
		
		when(sessionRepository.existsById(Mockito.anyLong())).thenReturn(false);
		
		Long minutes = 1L;
		LocalDateTime startDate = LocalDateTime.now();
		
		Session session = Session.builder()
				.agenda(agenda)
				.startDateSession(startDate)
				.endDateSession(startDate.plusMinutes(1))
				.closedSession(false).build();
		
		SessionForm sessionForm = new SessionForm();
		sessionForm.setAgendaId(agenda.getId());
		sessionForm.setMinutesDuration(minutes);
		
		when(sessionRepository.save(Mockito.any(Session.class))).thenReturn(session);
		
		Session createdSession = sessionServiceImpl.openSession(sessionForm);
		assertThat(createdSession.getAgenda().getName(), equalTo("Agenda for Session"));
	}
	
	@Test
	public void shoudReturnSessionAlreadyCreatedException() {
		Agenda agenda = new Agenda();
		agenda.setId(3L);
		agenda.setName("Agenda for Session");
		when(agendaServiceImpl.findById(Mockito.anyLong())).thenReturn(agenda);
		
		when(sessionRepository.existsById(Mockito.anyLong())).thenReturn(true);
		
		Long minutes = 1L;
		LocalDateTime startDate = LocalDateTime.now();
		
		Session session = Session.builder()
				.agenda(agenda)
				.startDateSession(startDate)
				.endDateSession(startDate.plusMinutes(1))
				.closedSession(false).build();
		
		SessionForm sessionForm = new SessionForm();
		sessionForm.setAgendaId(agenda.getId());
		sessionForm.setMinutesDuration(minutes);
		
		when(sessionRepository.save(Mockito.any(Session.class))).thenReturn(session);

		assertThrows(SessionAlreadyCreatedException.class, () -> sessionServiceImpl.openSession(sessionForm));
	}
	
	@Test
	public void shoudReturnNotFoundAgencia() {
		Agenda agenda = new Agenda();
		agenda.setId(3L);
		agenda.setName("Agenda for Session");
		when(agendaServiceImpl.findById(Mockito.anyLong())).thenThrow(NotFoundException.class);
		
		when(sessionRepository.existsById(Mockito.anyLong())).thenReturn(false);
		
		Long minutes = 1L;
		LocalDateTime startDate = LocalDateTime.now();
		
		Session session = Session.builder()
				.agenda(agenda)
				.startDateSession(startDate)
				.endDateSession(startDate.plusMinutes(1))
				.closedSession(false).build();
		
		SessionForm sessionForm = new SessionForm();
		sessionForm.setAgendaId(agenda.getId());
		sessionForm.setMinutesDuration(minutes);
		
		when(sessionRepository.save(Mockito.any(Session.class))).thenReturn(session);

		assertThrows(NotFoundException.class, () -> sessionServiceImpl.openSession(sessionForm));
	}
	
	@Test
	public void shoudBeOpenSession() {
		when(agendaServiceImpl.existsById(Mockito.anyLong())).thenReturn(true);
		Optional<Session> optionalAgenda = Optional.of(Session.builder().startDateSession(LocalDateTime.now()).endDateSession(LocalDateTime.now().plusMinutes(1)).build());
		when(sessionRepository.findByAgenda_Id(Mockito.anyLong())).thenReturn(optionalAgenda);
		
		assertTrue(sessionServiceImpl.isOpen(Mockito.anyLong()));
	}
	
	@Test
	public void shoudNotFindSession() {
		when(agendaServiceImpl.existsById(Mockito.anyLong())).thenReturn(true);
		assertThrows(NotFoundException.class, () -> sessionServiceImpl.isOpen(Mockito.anyLong()));
	}
	
	@Test
	public void shoudNotFoundAgendan() {
		when(agendaServiceImpl.existsById(Mockito.anyLong())).thenReturn(false);
		assertThrows(NotFoundException.class, () -> sessionServiceImpl.isOpen(Mockito.anyLong()));
	}
	
	@Test
	public void shoudBeClosedSession() {
		when(agendaServiceImpl.existsById(Mockito.anyLong())).thenReturn(true);
		Optional<Session> optionalAgenda = Optional.of(Session.builder().startDateSession(LocalDateTime.now().plusMinutes(1)).endDateSession(LocalDateTime.now()).build());
		when(sessionRepository.findByAgenda_Id(Mockito.anyLong())).thenReturn(optionalAgenda);
		
		assertFalse(sessionServiceImpl.isOpen(Mockito.anyLong()));
	}
	
	@Test
	public void shoudListCloseSessions() {
		List<Session> sessions = new ArrayList<Session>();
		sessions.add(Session.builder().closedSession(false).build());
		when(sessionRepository.findEndedSessions(Mockito.any(), Mockito.anyBoolean())).thenReturn(sessions);
		
		when(sessionRepository.save(Mockito.any())).thenReturn(Session.builder().closedSession(true).build());
		
		List<Session> returnedSessions = sessionServiceImpl.closeSessions();
		
		assertNotNull(returnedSessions);
		assertThat(returnedSessions.size(), equalTo(1));
	}
	
	@Test
	public void shoudList2CloseSessions() {
		List<Session> sessions = new ArrayList<Session>();
		sessions.add(Session.builder().closedSession(false).build());
		sessions.add(Session.builder().closedSession(false).build());
		when(sessionRepository.findEndedSessions(Mockito.any(), Mockito.anyBoolean())).thenReturn(sessions);
		
		when(sessionRepository.save(Mockito.any())).thenReturn(Session.builder().closedSession(true).build());
		
		List<Session> returnedSessions = sessionServiceImpl.closeSessions();
		
		assertNotNull(returnedSessions);
		assertThat(returnedSessions.size(), equalTo(2));
	}
}
