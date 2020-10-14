package com.voting.sessions.client;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import com.voting.sessions.client.response.ValidateCpfResponse;

@FeignClient(url = "https://user-info.herokuapp.com", name = "userCpf")
public interface ValidateCpfClient {
	
	@GetMapping("/users/{cpf}")
	ValidateCpfResponse validateCpf(@PathVariable("cpf") String cpf);
}
