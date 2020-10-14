package com.voting.sessions.Exception;

public class SessionAlreadyCreatedException  extends RuntimeException{

	private static final long serialVersionUID = 4817461082402512951L;

	public SessionAlreadyCreatedException(String message) {
		super(message);
	}
}
