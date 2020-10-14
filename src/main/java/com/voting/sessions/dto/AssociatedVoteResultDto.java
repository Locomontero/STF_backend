package com.voting.sessions.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

@Data
@AllArgsConstructor
@Builder(toBuilder = true)
public class AssociatedVoteResultDto {

	private String agendaName;
	private Long countYes;
	private Long countNo;
	private String result;
	private boolean isVoteSessionFinsh;
	
}
