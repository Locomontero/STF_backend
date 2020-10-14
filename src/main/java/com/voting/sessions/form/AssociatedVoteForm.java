package com.voting.sessions.form;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

import com.voting.sessions.Exception.InvalidVoteException;
import com.voting.sessions.entity.AssociatedVote;

import lombok.Data;

@Data
public class AssociatedVoteForm {

	@NotNull
	private Long agendaId;
	@NotNull @NotEmpty
	private String vote;
	@NotNull @NotEmpty
	private String cpf;
	
	public AssociatedVote formToAssociatedVoteEntity() {
		try {
			return AssociatedVote.builder().cpf(cpf).vote(VoteType.valueOf(vote.toUpperCase())).build();			
		} catch (IllegalArgumentException e) {
			throw new InvalidVoteException(String.format("Vote `%s` is invalid", vote));
		}
	}
}
