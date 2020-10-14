package com.voting.sessions.dto;

import com.voting.sessions.entity.Agenda;

import lombok.Data;

@Data
public class AgendaDto {

	private Long id;
	private String name;
	
	
	public AgendaDto(Agenda agenda) {
		this.id = agenda.getId();
		this.name = agenda.getName();
	}
	
}
