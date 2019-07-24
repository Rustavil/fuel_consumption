package ru.rustavil.fuel_consumption.rest;

import org.springframework.context.support.DefaultMessageSourceResolvable;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;
import ru.rustavil.fuel_consumption.domain.exceptions.ResourceNotFoundException;
import ru.rustavil.fuel_consumption.domain.exceptions.ValidationException;
import ru.rustavil.fuel_consumption.rest.dto.ErrorResponseDto;

import javax.validation.ConstraintViolation;
import java.time.LocalDateTime;
import java.util.Map;
import java.util.stream.Collectors;

@ControllerAdvice
public class CustomGlobalExceptionHandler extends ResponseEntityExceptionHandler {

    @ExceptionHandler(ValidationException.class)
    public ResponseEntity<ErrorResponseDto> customHandleNonValid(ValidationException e) {

        ErrorResponseDto error = new ErrorResponseDto();
        Map<String, String> fieldsError = e.getViolations()
                .stream()
                .collect(Collectors.toMap(c -> c.getPropertyPath().toString(), ConstraintViolation::getMessage));

        error.setTimestamp(LocalDateTime.now());
        error.setError("Invalid argument");
        error.setFieldErrors(fieldsError);

        return ResponseEntity.badRequest().body(error);

    }

    @ExceptionHandler(ResourceNotFoundException.class)
    public ResponseEntity<ErrorResponseDto> customHandleNotFound(Exception e) {

        ErrorResponseDto errors = new ErrorResponseDto();
        errors.setTimestamp(LocalDateTime.now());
        errors.setError(e.getMessage());

        return new ResponseEntity<>(errors, HttpStatus.NOT_FOUND);

    }

    @ExceptionHandler(Exception.class)
    public ResponseEntity<ErrorResponseDto> customHandleServerError(Exception e) {

        ErrorResponseDto error = new ErrorResponseDto();
        error.setTimestamp(LocalDateTime.now());
        error.setError(e.getLocalizedMessage());

        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(error);
    }

    @Override
    protected ResponseEntity<Object> handleMethodArgumentNotValid(MethodArgumentNotValidException e,
                                                                  HttpHeaders headers,
                                                                  HttpStatus status, WebRequest request) {
        ErrorResponseDto error = new ErrorResponseDto();
        Map<String, String> fieldsError = e.getBindingResult()
                .getFieldErrors()
                .stream()
                .collect(Collectors.toMap(FieldError::getField, DefaultMessageSourceResolvable::getDefaultMessage));

        error.setTimestamp(LocalDateTime.now());
        error.setError("Invalid argument");
        error.setFieldErrors(fieldsError);

        return ResponseEntity.badRequest().body(error);
    }
}
