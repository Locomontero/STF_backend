package com.voting.sessions.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.voting.sessions.entity.AssociatedVote;
import com.voting.sessions.form.VoteType;



public interface AssociatedVoteRepository extends JpaRepository<AssociatedVote, Long>{
	
	boolean existsByCpfAndAgenda_Id(String cpf, Long agendaId);
	
	List<AssociatedVote> findAllByAgenda_Id(Long agendaId);
	
	Long countByAgenda_IdAndVote(Long agendaId, VoteType vote);

}
