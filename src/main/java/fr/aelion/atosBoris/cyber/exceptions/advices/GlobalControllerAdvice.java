package fr.aelion.atosBoris.cyber.exceptions.advices;

import fr.aelion.atosBoris.cyber.models.ApiError;
import lombok.extern.java.Log;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import java.util.logging.Level;

@Log
@ControllerAdvice(basePackages = "fr.aelion.atosBoris.cyber.controllers")
public class GlobalControllerAdvice {
    @ExceptionHandler({
            jakarta.validation.ConstraintViolationException.class,
            org.springframework.dao.DataIntegrityViolationException.class
    })
    public ResponseEntity<ApiError> dataValidationError(Exception e) {
        log.log(Level.WARNING, e.getMessage());
        return new ResponseEntity<>(
                new ApiError(HttpStatus.BAD_REQUEST.value(), "My validation error", e),
                HttpStatus.BAD_REQUEST);
    }
}
