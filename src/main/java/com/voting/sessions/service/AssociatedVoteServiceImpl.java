package com.voting.sessions.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.voting.sessions.Exception.SessionAlreadyClosedException;
import com.voting.sessions.Exception.UnableToVoteExecption;
import com.voting.sessions.Exception.VoteAlreadyRegisteredExpcetion;
import com.voting.sessions.client.ValidateCpfClient;
import com.voting.sessions.client.response.CpfValidType;
import com.voting.sessions.client.response.ValidateCpfResponse;
import com.voting.sessions.dto.AssociatedVoteResultDto;
import com.voting.sessions.entity.Agenda;
import com.voting.sessions.entity.AssociatedVote;
import com.voting.sessions.form.VoteType;
import com.voting.sessions.repository.AssociatedVoteRepository;

import lombok.extern.slf4j.Slf4j;

@Service
@Slf4j
public class AssociatedVoteServiceImpl implements AssociatedVoteService{

	private final String DRAW = "draw";
	private final String YES = "yes";
	private final String NO = "no";
	private final String NOT_DEFINED_YET = "Not defined yet";
	
	@Autowired
	AssociatedVoteRepository associatedVoteRepository;
	
	@Autowired
	SessionService sessionService;
	
	@Autowired
	AgendaService agendaService;
	
	@Autowired
	ValidateCpfClient validateCpfClient;
	
	@Override
	public AssociatedVote vote(AssociatedVote associatedVote, Long agendaId) {
		if(!isCpfValid(associatedVote.getCpf())) {
			throw new UnableToVoteExecption("Associate is unable to vote.");
		}
		if(haveVotedInAgenda(associatedVote.getCpf(), agendaId)) {
			throw new VoteAlreadyRegisteredExpcetion(String.format("Vote already registered for associate with cpf: %s", associatedVote.getCpf()));
		}
		
		if(sessionService.isOpen(agendaId)) {
			Agenda agenda = agendaService.findById(agendaId);
			associatedVote.setAgenda(agenda);
			return associatedVoteRepository.save(associatedVote);
		} else {
			throw new SessionAlreadyClosedException("This session is already Closed");
		}
	}
	
	@Override
	public AssociatedVoteResultDto getVoteResult(Long agendaId) {
		boolean isVoteSessionFinsh = true;
		Agenda agenda = agendaService.findById(agendaId);
		if(sessionService.isOpen(agendaId)) {
			isVoteSessionFinsh = false;
		}
		Long countYes = associatedVoteRepository.countByAgenda_IdAndVote(agendaId, VoteType.SIM);
		Long countNo = associatedVoteRepository.countByAgenda_IdAndVote(agendaId, VoteType.NAO);
		
		return AssociatedVoteResultDto.builder().agendaName(agenda.getName()).countYes(countYes).countNo(countNo).isVoteSessionFinsh(isVoteSessionFinsh).result(finalResult(countYes, countNo, isVoteSessionFinsh)).build();
	}
	
	private boolean isCpfValid(String cpf) {
		ValidateCpfResponse validateCpfResponse = validateCpfClient.validateCpf(cpf);
		log.info(validateCpfResponse.getStatus());
		if(CpfValidType.ABLE_TO_VOTE.equals(CpfValidType.valueOf(validateCpfResponse.getStatus()))) {
			return true;
		} else {
			return false;
		}
	}

	private String finalResult(Long yes, Long no, boolean isVoteSessionFinsh) {
		if(!isVoteSessionFinsh) {
			return NOT_DEFINED_YET;
		}
		
		if(yes == no) {
			return DRAW;
		} else if (yes > no) {
			return YES;
		} else if (no > yes) {
			return NO;
		}else {
			return NOT_DEFINED_YET;
		}
	}
	
	private boolean haveVotedInAgenda(String cpf, Long agendaId) {
		return associatedVoteRepository.existsByCpfAndAgenda_Id(cpf, agendaId);
	}

}
