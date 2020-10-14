package com.voting.sessions.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.voting.sessions.dto.SessionDto;
import com.voting.sessions.form.SessionForm;
import com.voting.sessions.service.SessionService;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

@RestController
@RequestMapping(value = "/api/session")
@Api(value = "API Session	")
public class SessionController {

	@Autowired
	SessionService sessionService;
	
	@PostMapping
	@ApiOperation(value = "Create a new session to vote an angeda")
	public ResponseEntity<SessionDto> openSession(@RequestBody SessionForm sessionForm) {
		return ResponseEntity.ok( new SessionDto(sessionService.openSession(sessionForm)));
	}
	
}
