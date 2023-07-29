package com.psja.delivery.common;

import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.http.ResponseEntity;
import org.springframework.http.MediaType;

@ControllerAdvice
public class CommonExceptionHandler {

	@ExceptionHandler( value = SystemException.class )
	public ResponseEntity<String> handleSystemException( SystemException exp ){
		System.out.println( exp.getMessage() );
		return ResponseEntity.status(200).contentType(MediaType.TEXT_PLAIN).body("FAILED");
	}
}
