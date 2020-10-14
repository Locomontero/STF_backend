package com.voting.sessions.controller;

import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.voting.sessions.dto.AssociatedVoteResultDto;
import com.voting.sessions.entity.Agenda;
import com.voting.sessions.entity.AssociatedVote;
import com.voting.sessions.form.AssociatedVoteForm;
import com.voting.sessions.form.VoteType;
import com.voting.sessions.service.AssociatedVoteServiceImpl;

@ExtendWith(SpringExtension.class)
@WebMvcTest(AssociatedVoteController.class)
public class AssociatedVoteControllerTest {
	
	@Autowired
	private ObjectMapper objectMapper;
	
	@MockBean
	private AssociatedVoteServiceImpl associatedVoteServiceImpl;
	
	@Autowired
	private MockMvc mockMvc;
	
	@Test
	public void voteInAnAgenda() throws Exception {
		
		when(associatedVoteServiceImpl.vote(Mockito.any(AssociatedVote.class), Mockito.anyLong()))
		.thenReturn(AssociatedVote
				.builder()
				.id(1L)
				.vote(VoteType.SIM)
				.cpf("12345678910")
				.agenda(Agenda
						.builder()
						.name("mock")
						.build())
				.build());
		
		AssociatedVoteForm associatedVoteForm = new AssociatedVoteForm();
		associatedVoteForm.setAgendaId(1L);
		associatedVoteForm.setCpf("12345678910");
		associatedVoteForm.setVote("SIM");
		
		mockMvc.perform(post("/api/associate/vote").contentType(MediaType.APPLICATION_JSON_VALUE)
				.content(objectMapper.writeValueAsString(associatedVoteForm))
				.accept(MediaType.APPLICATION_JSON_VALUE))
		.andDo(print())
		.andExpect(status().isOk());
	}
	
	@Test
	public void getResultsOfAnAgenda() throws Exception {	
		when(associatedVoteServiceImpl.getVoteResult(Mockito.anyLong())).thenReturn(AssociatedVoteResultDto.builder().agendaName("mock").countNo(1L).countYes(1L).result("draw").isVoteSessionFinsh(true).build());
			
		mockMvc.perform(get("/api/associate/vote/result/{agendaId}", "1").contentType(MediaType.APPLICATION_JSON_VALUE)
				.accept(MediaType.APPLICATION_JSON_VALUE))
		.andDo(print())
		.andExpect(status().isOk());
	}

}
