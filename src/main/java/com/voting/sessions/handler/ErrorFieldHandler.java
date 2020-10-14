package com.voting.sessions.handler;

import java.util.List;
import java.util.Locale;
import java.util.stream.Collectors;

import org.springframework.context.MessageSource;
import org.springframework.validation.FieldError;

import lombok.Getter;

@Getter
public class ErrorFieldHandler {
	private String field;
	private String error;

	private static MessageSource source;

	public ErrorFieldHandler(FieldError fieldError) {
		this.field = fieldError.getField();
		this.error = source.getMessage(fieldError, Locale.US);
	}

	public static List<ErrorFieldHandler> fieldErros(List<FieldError> filedErrors, MessageSource messageSource) {
		source = messageSource;
		return filedErrors.stream().map(ErrorFieldHandler::new).collect(Collectors.toList());
	}
}
