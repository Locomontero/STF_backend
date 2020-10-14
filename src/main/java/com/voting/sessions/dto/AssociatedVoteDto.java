package com.voting.sessions.dto;

import com.voting.sessions.entity.AssociatedVote;

import lombok.Data;

@Data
public class AssociatedVoteDto {
	
	private String agendaName;
	private String vote;
	private String cpf;


	public AssociatedVoteDto(AssociatedVote associatedVote) {
		this.agendaName = associatedVote.getAgenda().getName();
		this.vote = associatedVote.getVote().getDisplayName();
		this.cpf = associatedVote.getCpf();
	}
}
