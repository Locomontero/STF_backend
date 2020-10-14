package com.voting.sessions.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.voting.sessions.entity.Agenda;

@Repository
public interface AgendaRepository extends JpaRepository<Agenda, Long>{
}
