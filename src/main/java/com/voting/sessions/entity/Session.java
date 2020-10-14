package com.voting.sessions.entity;

import java.time.LocalDateTime;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.MapsId;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonManagedReference;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "Session")
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder(toBuilder = true)
public class Session {

	@Id
	private Long id;
	
	private LocalDateTime startDateSession;
	
	private LocalDateTime endDateSession;
	
	private boolean closedSession;
	
	@OneToOne
    @MapsId
    @JsonManagedReference
	private Agenda agenda;
	
}
