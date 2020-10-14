package com.voting.sessions.service;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.hamcrest.CoreMatchers.equalTo;
import static org.mockito.Mockito.when;

import java.util.Optional;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import com.voting.sessions.Exception.NotFoundException;
import com.voting.sessions.entity.Agenda;
import com.voting.sessions.repository.AgendaRepository;

@ExtendWith(SpringExtension.class)
public class AgendaServiceTest {

	@Mock
	private AgendaRepository agendaRepository;
	
	@InjectMocks
	private AgendaServiceImpl agendaServiceImpl;
	
	@Test
	public void shoudSaveAgenda() {
		Agenda agenda = new Agenda();
		agenda.setId(1L);
		agenda.setName("First Agenda");
		when(agendaRepository.save(Mockito.any(Agenda.class))).thenReturn(agenda);
		
		Agenda createdAgenda = agendaServiceImpl.save(agenda);
		
		assertThat(createdAgenda.getName(), equalTo("First Agenda"));
	}
	
	@Test
	public void shoudFindAgendaById() {
		Optional<Agenda> agenda = Optional.of(new Agenda());
		agenda.get().setId(2L);
		agenda.get().setName("Second Agenda");
		when(agendaRepository.findById(2L)).thenReturn(agenda);
		Agenda findAgenda = agendaServiceImpl.findById(2L);
		assertThat(findAgenda.getName(), equalTo("Second Agenda"));
	}
	
	@Test
	public void shoudExists() {
		when(agendaRepository.existsById(2L)).thenReturn(true);
		boolean exists = agendaServiceImpl.existsById(2L);
		assertThat(exists, equalTo(true));
	}
	
	@Test
	public void shoudNotFindAgendaById() {
		when(agendaRepository.findById(2L)).thenReturn(null);
		assertThrows(NotFoundException.class, () -> agendaServiceImpl.findById(Mockito.anyLong()));
	}
	
}
