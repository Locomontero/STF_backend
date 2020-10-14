package com.voting.sessions.form;

import lombok.Getter;

@Getter
public enum VoteType {
		SIM("Sim"),
		NAO("Não");
	
	private String displayName;
	
	VoteType(String displayName){
		this.displayName = displayName;
	}
}
