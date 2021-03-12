package br.com.btwow.exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import java.util.List;
import java.util.stream.Collectors;

@ControllerAdvice
public class PlanetServiceExceptionImpl {

  @ExceptionHandler(MethodArgumentNotValidException.class)
  public ResponseEntity<List<String>> handleMethodArgumentNotValidException(MethodArgumentNotValidException exc) {
    return ResponseEntity.status(HttpStatus.BAD_REQUEST)
            .body(exc.getFieldErrors().stream().map(FieldError::getDefaultMessage).collect(Collectors.toList()));
  }

  @ExceptionHandler(Exception.class)
  public ResponseEntity<Exception> handleException(Exception exc) {
    return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(exc);
  }

}
