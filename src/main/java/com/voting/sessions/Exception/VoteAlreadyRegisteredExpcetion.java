package com.voting.sessions.Exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

import lombok.Getter;

@Getter
@ResponseStatus(HttpStatus.BAD_REQUEST)
public class VoteAlreadyRegisteredExpcetion extends RuntimeException{

	private static final long serialVersionUID = 3760690550371817086L;

	public VoteAlreadyRegisteredExpcetion(String message) {
	    	super(message);
	    }
}
