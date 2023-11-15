package ua.com.dashboard.config;

import java.io.IOException;
import java.text.ParseException;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import com.google.gson.JsonIOException;
import com.google.gson.JsonSyntaxException;

import lombok.extern.log4j.Log4j2;

@ControllerAdvice
@Log4j2
public class ExceptionController {

	@ExceptionHandler(ParseException.class)
	public ResponseEntity<String> handleParseException(ParseException e) {
		log.error(e);
		return new ResponseEntity<>(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
	}

	@ExceptionHandler(IOException.class)
	public ResponseEntity<String> handleIOException(IOException e) {
		log.error(e);
		return new ResponseEntity<>(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
	}
    
    @ExceptionHandler(JsonIOException.class)
	public ResponseEntity<String> handleJsonIOException(JsonIOException e) {
    	log.error(e);
		return new ResponseEntity<>(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
	}
    
    @ExceptionHandler(JsonSyntaxException.class)
	public ResponseEntity<String> handleJsonSyntaxException(JsonSyntaxException e) {
    	log.error(e);
		return new ResponseEntity<>(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
	}

	@ExceptionHandler(IllegalArgumentException.class)
	public ResponseEntity<String> handleIllegalArgumentException(IllegalArgumentException e) {
		log.error(e);
		return new ResponseEntity<>(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
	}
}
