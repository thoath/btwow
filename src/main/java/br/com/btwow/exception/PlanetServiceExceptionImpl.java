package br.com.btwow.exception;

import java.util.List;
import java.util.stream.Collectors;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class PlanetServiceExceptionImpl {

  @ExceptionHandler(MethodArgumentNotValidException.class)
  public ResponseEntity<List<String>> handleMethodArgumentNotValidException(
      MethodArgumentNotValidException exc) {
    return ResponseEntity.status(HttpStatus.BAD_REQUEST)
        .body(
            exc.getFieldErrors().stream()
                .map(FieldError::getDefaultMessage)
                .collect(Collectors.toList()));
  }

  @ExceptionHandler(PlanetNotFoundException.class)
  public ResponseEntity handlePlanetNotFoundException(PlanetNotFoundException ex) {
    return ResponseEntity.notFound().build();
  }
}
