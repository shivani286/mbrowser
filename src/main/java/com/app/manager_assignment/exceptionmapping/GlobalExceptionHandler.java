package com.app.manager_assignment.exceptionmapping;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import com.app.manager_assignment.dto.ExceptionResponse;

@RestControllerAdvice
public class GlobalExceptionHandler extends ResponseEntityExceptionHandler {

	@ExceptionHandler(EntityAlreadyExistsException.class)
	public ResponseEntity<ExceptionResponse> handleResourceAlreadyExists(WebRequest request, Exception exception) {
		ExceptionResponse exceptionResponse = new ExceptionResponse(HttpStatus.CONFLICT.value(),
				exception.getLocalizedMessage());
		return new ResponseEntity<ExceptionResponse>(exceptionResponse, HttpStatus.CONFLICT);
	}

	@ExceptionHandler(IllegalArgumentException.class)
	protected ResponseEntity<ExceptionResponse> handleIllegalArgument(WebRequest request, Exception exception) {
		ExceptionResponse exceptionResponse = new ExceptionResponse(HttpStatus.BAD_REQUEST.value(),
				exception.getLocalizedMessage());
		return new ResponseEntity<ExceptionResponse>(exceptionResponse, HttpStatus.BAD_REQUEST);
	}

	@ExceptionHandler(NullPointerException.class)
	protected ResponseEntity handleOtherExceptions(WebRequest request, Exception exception) {
		ExceptionResponse exceptionResponse = new ExceptionResponse(HttpStatus.INTERNAL_SERVER_ERROR.value(),
				exception.getLocalizedMessage());
		return new ResponseEntity<ExceptionResponse>(exceptionResponse, HttpStatus.INTERNAL_SERVER_ERROR);
	}

	@ExceptionHandler(InvalidLoginCredentials.class)
	protected ResponseEntity<ExceptionResponse> handleInvalidLoginCredentials(WebRequest request, Exception exception) {
		ExceptionResponse exceptionResponse = new ExceptionResponse(HttpStatus.UNAUTHORIZED.value(),
				exception.getLocalizedMessage());
		return new ResponseEntity<ExceptionResponse>(exceptionResponse, HttpStatus.UNAUTHORIZED);
	}

}
