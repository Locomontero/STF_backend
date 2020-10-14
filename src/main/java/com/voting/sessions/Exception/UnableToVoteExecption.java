package com.voting.sessions.Exception;

public class UnableToVoteExecption extends RuntimeException{

	private static final long serialVersionUID = 7384018987268271505L;

	public UnableToVoteExecption(String message) {
		super(message);
	}
}
