package com.voting.sessions.repository;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.voting.sessions.entity.Session;

public interface SessionRepository extends JpaRepository<Session, Long>{

	Optional<Session> findByAgenda_Id(Long agendaId);
	
	@Query(value = "select s from Session s where closedSession = :close  and s.endDateSession < :now")
	List<Session> findEndedSessions(@Param("now") LocalDateTime now, @Param("close") boolean close);
	
}
