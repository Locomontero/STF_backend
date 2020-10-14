package com.voting.sessions.entity;

import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToOne;

import com.voting.sessions.form.VoteType;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Entity
@AllArgsConstructor
@NoArgsConstructor
@Builder(toBuilder = true)
public class AssociatedVote {

	@Id @GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	private String cpf;
	
	@Enumerated(EnumType.STRING)
	private VoteType vote;
	
	@OneToOne
	private Agenda agenda;
}
