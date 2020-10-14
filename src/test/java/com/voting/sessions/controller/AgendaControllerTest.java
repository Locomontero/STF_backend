package com.voting.sessions.controller;

import static org.mockito.Mockito.when;
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
import com.voting.sessions.entity.Agenda;import com.voting.sessions.form.AgendaForm;
import com.voting.sessions.service.AgendaServiceImpl;

@ExtendWith(SpringExtension.class)
@WebMvcTest(AgendaController.class)
public class AgendaControllerTest {

	@Autowired
	private ObjectMapper objectMapper;
	
	@MockBean
	private AgendaServiceImpl agendaServiceImpl;
	
	@Autowired
	private MockMvc mockMvc;
	
	@Test
	public void registerAnAgenda() throws Exception {
		
		when(agendaServiceImpl.save(Mockito.any(Agenda.class))).thenReturn(Agenda.builder().id(1L).name("mock").build());
		
		AgendaForm agendaForm = new AgendaForm();
		agendaForm.setName("mock agenda 2");
		mockMvc.perform(post("/api/agenda").contentType(MediaType.APPLICATION_JSON_VALUE)
				.content(objectMapper.writeValueAsString(agendaForm))
				.accept(MediaType.APPLICATION_JSON_VALUE))
		.andDo(print())
		.andExpect(status().isOk());
	}
}
