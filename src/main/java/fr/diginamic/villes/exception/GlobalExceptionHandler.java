package fr.diginamic.villes.exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class GlobalExceptionHandler {

    // Handles InvalidVilleException and returns 400 Bad Request with the error message
    @ExceptionHandler({InvalidVilleException.class})
    public ResponseEntity<String> handleInvalidVilleException(InvalidVilleException e) {
        return new ResponseEntity<>(e.getMessage(), HttpStatus.BAD_REQUEST);
    }

    // Handles InvalidDepartementException and returns 400 Bad Request with the error message
    @ExceptionHandler({InvalidDepartementException.class})
    public ResponseEntity<String> handleInvalidDepartementException(InvalidDepartementException e) {
        return new ResponseEntity<>(e.getMessage(), HttpStatus.BAD_REQUEST);
    }

}
