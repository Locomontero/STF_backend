package com.voting.sessions.controller;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.voting.sessions.dto.AgendaDto;
import com.voting.sessions.form.AgendaForm;
import com.voting.sessions.service.AgendaService;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

@RestController
@RequestMapping(value = "/api/agenda")
@Api(value  = "Api Agenda")
public class AgendaController {

	@Autowired
	AgendaService agendaService;
	
	@PostMapping
	@ApiOperation(value = "Register a new Agenda")
	public ResponseEntity<AgendaDto> register(@RequestBody @Valid AgendaForm agendaForm) {
		return ResponseEntity.ok(new AgendaDto(agendaService.save(agendaForm.formtoEntity())));
	}

}
