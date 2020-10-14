package com.voting.sessions.form;

import com.voting.sessions.entity.Agenda;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import lombok.Data;

@Data
public class AgendaForm {
	
	@NotEmpty @NotNull
	private String name;
	
	public Agenda formtoEntity() {
		return Agenda.builder().name(name).build();
	}
}
