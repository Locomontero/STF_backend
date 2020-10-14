package com.voting.sessions.Exception;

import lombok.Getter;

@Getter
public class NotFoundException extends RuntimeException {

	/**
	 * 
	 */
	private static final long serialVersionUID = 6725385949830028797L;
	
    public NotFoundException(String message) {
    	super(message);
    }
}
