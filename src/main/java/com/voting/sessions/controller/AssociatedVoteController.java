package com.voting.sessions.controller;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.voting.sessions.dto.AssociatedVoteDto;
import com.voting.sessions.dto.AssociatedVoteResultDto;
import com.voting.sessions.form.AssociatedVoteForm;
import com.voting.sessions.service.AssociatedVoteService;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

@RestController
@RequestMapping(value = "/api/associate/vote")
@Api(value = "API AssociatedVote")
public class AssociatedVoteController {

	@Autowired
	AssociatedVoteService associatedVoteService;
	
	@PostMapping
	@ApiOperation(value = "Vote in an Angeda")
	public ResponseEntity<AssociatedVoteDto> vote(@RequestBody @Valid AssociatedVoteForm associatedVoteForm) {
		return ResponseEntity.ok(new AssociatedVoteDto(associatedVoteService.vote(associatedVoteForm.formToAssociatedVoteEntity() , associatedVoteForm.getAgendaId())));
	}
	
	@GetMapping(value = "/result/{agendaId}")
	@ApiOperation(value = "Get Results of an Angenda could be partial or final result")
	public ResponseEntity<AssociatedVoteResultDto> getVoteResult(@PathVariable("agendaId") Long agendaId) {
		return ResponseEntity.ok(associatedVoteService.getVoteResult(agendaId));
	}
}
