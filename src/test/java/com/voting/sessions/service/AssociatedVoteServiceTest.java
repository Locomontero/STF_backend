package com.voting.sessions.service;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.when;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import com.voting.sessions.Exception.UnableToVoteExecption;
import com.voting.sessions.client.ValidateCpfClient;
import com.voting.sessions.client.response.ValidateCpfResponse;
import com.voting.sessions.dto.AssociatedVoteResultDto;
import com.voting.sessions.entity.Agenda;
import com.voting.sessions.entity.AssociatedVote;
import com.voting.sessions.form.VoteType;
import com.voting.sessions.repository.AssociatedVoteRepository;

@ExtendWith(SpringExtension.class)
public class AssociatedVoteServiceTest {
	
	@Mock
	ValidateCpfClient validateCpfClient;
	
	@Mock
	AssociatedVoteRepository associatedVoteRepository;
	
	@Mock
	SessionServiceImpl sessionServiceImpl;
	
	@Mock
	AgendaServiceImpl agendaServiceImpl;
	
	@InjectMocks
	AssociatedVoteServiceImpl associatedVoteServiceImpl;
	
	@Test
	public void shoudVoteYesInAngeda() {
		String cpf = "84786051001";
		
		Agenda agenda = Agenda.builder().id(1L).name("An Agenda").build();
		AssociatedVote associatedVote = AssociatedVote.builder().cpf(cpf).vote(VoteType.SIM).agenda(agenda).build();
		
		ValidateCpfResponse validateCpfResponse = new ValidateCpfResponse();
		validateCpfResponse.setStatus("ABLE_TO_VOTE");
		
		when(validateCpfClient.validateCpf(Mockito.anyString())).thenReturn(validateCpfResponse);
		
		when(associatedVoteRepository.existsByCpfAndAgenda_Id(Mockito.anyString(), Mockito.anyLong())).thenReturn(false);
		
		when(sessionServiceImpl.isOpen(Mockito.anyLong())).thenReturn(true);
		
		when(agendaServiceImpl.findById(Mockito.anyLong())).thenReturn(agenda);
		
		when(associatedVoteRepository.save(Mockito.any(AssociatedVote.class))).thenReturn(associatedVote);
		
		AssociatedVote vote = associatedVoteServiceImpl.vote(associatedVote, agenda.getId());
		
		assertThat(vote.getVote(), equalTo(VoteType.SIM));
	}
	
	@Test
	public void shoudBeUnableToVote() {
		String cpf = "84786051001";
		
		Agenda agenda = Agenda.builder().id(1L).name("An Agenda").build();
		AssociatedVote associatedVote = AssociatedVote.builder().cpf(cpf).vote(VoteType.SIM).agenda(agenda).build();
		
		ValidateCpfResponse validateCpfResponse = new ValidateCpfResponse();
		validateCpfResponse.setStatus("UNABLE_TO_VOTE");
		
		when(validateCpfClient.validateCpf(Mockito.anyString())).thenReturn(validateCpfResponse);
		
		when(associatedVoteRepository.existsByCpfAndAgenda_Id(Mockito.anyString(), Mockito.anyLong())).thenReturn(false);
		
		when(sessionServiceImpl.isOpen(Mockito.anyLong())).thenReturn(true);
		
		when(agendaServiceImpl.findById(Mockito.anyLong())).thenReturn(agenda);
		
		when(associatedVoteRepository.save(Mockito.any(AssociatedVote.class))).thenReturn(associatedVote);
		
		assertThrows(UnableToVoteExecption.class, () ->  associatedVoteServiceImpl.vote(associatedVote, agenda.getId()));
	}
	
	@Test
	public void shouldReturnResults() {
		Agenda agenda = Agenda.builder().id(1L).name("An Agenda").build();
		
		when(agendaServiceImpl.findById(Mockito.anyLong())).thenReturn(agenda);
		
		when(sessionServiceImpl.isOpen(Mockito.anyLong())).thenReturn(true);
		
		when(associatedVoteRepository.countByAgenda_IdAndVote(Mockito.anyLong(), Mockito.any())).thenReturn(Mockito.anyLong());

		AssociatedVoteResultDto getResults = associatedVoteServiceImpl.getVoteResult(1L);
		assertNotNull(getResults);
	}
	
}
