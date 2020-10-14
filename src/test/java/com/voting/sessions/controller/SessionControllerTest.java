package com.voting.sessions.controller;

import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.time.LocalDateTime;

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
import com.voting.sessions.entity.Agenda;
import com.voting.sessions.entity.Session;
import com.voting.sessions.form.SessionForm;
import com.voting.sessions.service.SessionServiceImpl;

@ExtendWith(SpringExtension.class)
@WebMvcTest(SessionController.class)
public class SessionControllerTest {

	@Autowired
	private ObjectMapper objectMapper;
	
	@MockBean
	private SessionServiceImpl sessionServiceImpl;
	
	@Autowired
	private MockMvc mockMvc;
	
	@Test
	public void openNewSession() throws Exception {
		
		when(sessionServiceImpl.openSession(Mockito.any(SessionForm.class)))
		.thenReturn(Session
				.builder()
				.id(1L)
				.closedSession(false)
				.startDateSession(LocalDateTime.now())
				.endDateSession(LocalDateTime.now().plusMinutes(1))
				.agenda(Agenda.builder().name("mock").build()).build());
		
		SessionForm sessionForm = new SessionForm();
		sessionForm.setAgendaId(1L);
		sessionForm.setMinutesDuration(1L);
		mockMvc.perform(post("/api/session").contentType(MediaType.APPLICATION_JSON_VALUE)
				.content(objectMapper.writeValueAsString(sessionForm))
				.accept(MediaType.APPLICATION_JSON_VALUE))
		.andDo(print())
		.andExpect(status().isOk());
		
	}
	
}
