package com.voting.sessions.handler;

import java.time.ZonedDateTime;
import java.util.List;

import javax.validation.ConstraintViolationException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import com.voting.sessions.Exception.ClientExecption;
import com.voting.sessions.Exception.InvalidCpfExecption;
import com.voting.sessions.Exception.InvalidVoteException;
import com.voting.sessions.Exception.NotFoundException;
import com.voting.sessions.Exception.SessionAlreadyClosedException;
import com.voting.sessions.Exception.SessionAlreadyCreatedException;
import com.voting.sessions.Exception.UnableToVoteExecption;
import com.voting.sessions.Exception.VoteAlreadyRegisteredExpcetion;

@RestControllerAdvice
public class ExceptionHandler {
	
	@Autowired
	private MessageSource messageSource;
	
    @org.springframework.web.bind.annotation.ExceptionHandler({ MethodArgumentNotValidException.class })
    public List<ErrorFieldHandler> handleMethodArgumentNotValid(MethodArgumentNotValidException ex) {
    	return ErrorFieldHandler.fieldErros(ex.getBindingResult().getFieldErrors(), messageSource);
    }
     
    @org.springframework.web.bind.annotation.ExceptionHandler({ ConstraintViolationException.class })
    public ResponseEntity<Object>  handleConstraintViolation(ConstraintViolationException ex) {
    	HttpStatus badRquest = HttpStatus.BAD_REQUEST;
    	ApiCustomErrorHandler apiCustomError = 
    			new ApiCustomErrorHandler(
    			ex.getMessage(),
    			badRquest,
    			ZonedDateTime.now());
    	 return new ResponseEntity<Object>(apiCustomError, badRquest);
    }

    @org.springframework.web.bind.annotation.ExceptionHandler({ NotFoundException.class })
    public ResponseEntity<Object> handleNotFound(NotFoundException ex) {
    	HttpStatus notFound = HttpStatus.NOT_FOUND;
    	ApiCustomErrorHandler apiCustomError = 
    			new ApiCustomErrorHandler(
    			ex.getMessage(),
    			notFound,
    			ZonedDateTime.now());
        return new ResponseEntity<Object>(apiCustomError, notFound);
    }
    
    @org.springframework.web.bind.annotation.ExceptionHandler({ VoteAlreadyRegisteredExpcetion.class })
    public ResponseEntity<Object>  handleVoteAlreadyRegistered(VoteAlreadyRegisteredExpcetion ex) {
    	HttpStatus badRquest = HttpStatus.BAD_REQUEST;
    	ApiCustomErrorHandler apiCustomError = 
    			new ApiCustomErrorHandler(
    			ex.getMessage(),
    			badRquest,
    			ZonedDateTime.now());
    	 return new ResponseEntity<Object>(apiCustomError, badRquest);
    }
    
    @org.springframework.web.bind.annotation.ExceptionHandler({ SessionAlreadyClosedException.class })
    public ResponseEntity<Object>  handleSessionAlreadyClosed(SessionAlreadyClosedException ex) {
    	HttpStatus badRquest = HttpStatus.BAD_REQUEST;
    	ApiCustomErrorHandler apiCustomError = 
    			new ApiCustomErrorHandler(
    			ex.getMessage(),
    			badRquest,
    			ZonedDateTime.now());
    	 return new ResponseEntity<Object>(apiCustomError, badRquest);
    }
    
    @org.springframework.web.bind.annotation.ExceptionHandler({ SessionAlreadyCreatedException.class })
    public ResponseEntity<Object>  handleSessionAlreadyCreated(SessionAlreadyCreatedException ex) {
    	HttpStatus badRquest = HttpStatus.BAD_REQUEST;
    	ApiCustomErrorHandler apiCustomError = 
    			new ApiCustomErrorHandler(
    			ex.getMessage(),
    			badRquest,
    			ZonedDateTime.now());
    	 return new ResponseEntity<Object>(apiCustomError, badRquest);
    }
    
    @org.springframework.web.bind.annotation.ExceptionHandler({ InvalidVoteException.class })
    public ResponseEntity<Object>  handleInvalidVote(InvalidVoteException ex) {
    	HttpStatus badRquest = HttpStatus.BAD_REQUEST;
    	ApiCustomErrorHandler apiCustomError = 
    			new ApiCustomErrorHandler(
    			ex.getMessage(),
    			badRquest,
    			ZonedDateTime.now());
    	 return new ResponseEntity<Object>(apiCustomError, badRquest);
    }
    
    @org.springframework.web.bind.annotation.ExceptionHandler({ InvalidCpfExecption.class })
    public ResponseEntity<Object>  handleInvalidCpf(InvalidCpfExecption ex) {
    	HttpStatus badRquest = HttpStatus.BAD_REQUEST;
    	ApiCustomErrorHandler apiCustomError = 
    			new ApiCustomErrorHandler(
    			ex.getMessage(),
    			badRquest,
    			ZonedDateTime.now());
    	 return new ResponseEntity<Object>(apiCustomError, badRquest);
    }
    
    @org.springframework.web.bind.annotation.ExceptionHandler({ ClientExecption.class })
    public ResponseEntity<Object>  handleClientError(ClientExecption ex) {
    	HttpStatus badRquest = HttpStatus.INTERNAL_SERVER_ERROR;
    	ApiCustomErrorHandler apiCustomError = 
    			new ApiCustomErrorHandler(
    			ex.getMessage(),
    			badRquest,
    			ZonedDateTime.now());
    	 return new ResponseEntity<Object>(apiCustomError, badRquest);
    }

    @org.springframework.web.bind.annotation.ExceptionHandler({ UnableToVoteExecption.class })
    public ResponseEntity<Object>  handleUnableToVote(UnableToVoteExecption ex) {
    	HttpStatus badRquest = HttpStatus.BAD_REQUEST;
    	ApiCustomErrorHandler apiCustomError = 
    			new ApiCustomErrorHandler(
    			ex.getMessage(),
    			badRquest,
    			ZonedDateTime.now());
    	 return new ResponseEntity<Object>(apiCustomError, badRquest);
    }
}
