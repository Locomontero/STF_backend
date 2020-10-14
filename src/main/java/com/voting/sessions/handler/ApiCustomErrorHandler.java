package com.voting.sessions.handler;

import java.time.ZonedDateTime;

import org.springframework.http.HttpStatus;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class ApiCustomErrorHandler {

	private final String message;
	private final HttpStatus httpStatus;
	private ZonedDateTime timestamp;
}
