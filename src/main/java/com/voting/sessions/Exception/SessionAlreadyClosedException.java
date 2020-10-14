package com.voting.sessions.Exception;

public class SessionAlreadyClosedException extends RuntimeException{

	private static final long serialVersionUID = -1930994408283522364L;

	public SessionAlreadyClosedException(String message) {
		super(message);
	}
}
