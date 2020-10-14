package com.voting.sessions.Exception;

public class InvalidVoteException extends RuntimeException{

	private static final long serialVersionUID = -6715350037904078507L;

    public InvalidVoteException(String message) {
    	super(message);
    }
}
