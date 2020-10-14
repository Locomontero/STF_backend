package com.voting.sessions.client;

import com.voting.sessions.Exception.ClientExecption;
import com.voting.sessions.Exception.InvalidCpfExecption;

import feign.Response;
import feign.codec.ErrorDecoder;

public class CustomErrorDecode implements ErrorDecoder{

	@Override
	public Exception decode(String methodKey, Response response) {
        switch (response.status()){
        case 404:
            return new InvalidCpfExecption("this CPF is invalid");
        default:
            return new ClientExecption(String.format("Something went wrong when trying to call %s", methodKey));
    }
	}
}
