package com.voting.sessions.service;

import com.voting.sessions.dto.AssociatedVoteResultDto;
import com.voting.sessions.entity.AssociatedVote;

public interface AssociatedVoteService {

	AssociatedVote vote (AssociatedVote associatedVote, Long agendaId);
	
	AssociatedVoteResultDto getVoteResult(Long agendaId);
	
}
