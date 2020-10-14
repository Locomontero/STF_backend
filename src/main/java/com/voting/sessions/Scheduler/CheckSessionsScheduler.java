package com.voting.sessions.Scheduler;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import com.voting.sessions.entity.Session;
import com.voting.sessions.service.AssociatedVoteService;
import com.voting.sessions.service.SessionService;

import lombok.extern.slf4j.Slf4j;

import java.util.List;
import java.util.stream.Collectors;


@Component
@Slf4j
public class CheckSessionsScheduler {
	
	@Autowired
	SessionService sessionService;
	
	@Autowired
	AssociatedVoteService associatedVoteService;
	
	@Scheduled(fixedRate = 5000)
	public void closedSessions() {
		log.info("Running Scheduler Close Sessions");
		List<Session> sessions = sessionService.closeSessions();
		List<String> strings = sessions.stream().map(session -> session.getAgenda().getName()).collect(Collectors.toList());
//		associatedVoteService.getVoteResult(sessions.get(0).getAgenda().getId());
		strings.forEach(s -> log.info("Clossing session for agenda: [{}]",s));
	}
}
