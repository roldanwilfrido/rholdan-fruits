package com.rholdan.fruits.exception;

import com.rholdan.fruits.domain.dto.StoreErrorDTO;
import jakarta.persistence.EntityExistsException;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestController;

import java.time.Instant;

@ControllerAdvice
@RestController
public class StoreExceptionHandler {

    @ExceptionHandler(EntityExistsException.class)
    public ResponseEntity<StoreErrorDTO> fruitAlreadyExistsException(Exception ex) {

        StoreErrorDTO dto = StoreErrorDTO.builder()
                .time(Instant.now())
                .status(HttpStatus.UNPROCESSABLE_ENTITY.value())
                .message("Fruit already exist!")
                .build();
        return new ResponseEntity<>(dto, HttpStatus.UNPROCESSABLE_ENTITY);

    }

    @ExceptionHandler(EntityNotFoundException.class)
    public ResponseEntity<StoreErrorDTO> fruitNotExistsException(Exception ex) {

        StoreErrorDTO dto = StoreErrorDTO.builder()
                .time(Instant.now())
                .status(HttpStatus.UNPROCESSABLE_ENTITY.value())
                .message("Fruit not exist! Please create it.")
                .build();
        return new ResponseEntity<>(dto, HttpStatus.UNPROCESSABLE_ENTITY);

    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<StoreErrorDTO> validationException(Exception ex) {

        StoreErrorDTO dto = StoreErrorDTO.builder()
                .time(Instant.now())
                .status(HttpStatus.BAD_REQUEST.value())
                .message("Missing fields! Please check your input.")
                .build();
        return new ResponseEntity<>(dto, HttpStatus.BAD_REQUEST);

    }

    @ExceptionHandler(HttpMessageNotReadableException.class)
    public ResponseEntity<StoreErrorDTO> readableException(Exception ex) {

        StoreErrorDTO dto = StoreErrorDTO.builder()
                .time(Instant.now())
                .status(HttpStatus.BAD_REQUEST.value())
                .message("Invalid input! Please check.")
                .build();
        return new ResponseEntity<>(dto, HttpStatus.BAD_REQUEST);

    }

}
