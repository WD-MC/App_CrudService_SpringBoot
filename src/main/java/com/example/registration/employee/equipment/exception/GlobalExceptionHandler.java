package com.example.registration.employee.equipment.exception;

import com.example.registration.employee.equipment.dto.ErrorResponse;
import com.example.registration.employee.equipment.dto.ValidationError;
import com.example.registration.employee.equipment.dto.ValidationErrorResponse;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;

import java.util.List;
import java.util.stream.Collectors;

@ControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(EquipmentNotFoundException.class)
    public ResponseEntity<ErrorResponse> handleEquipmentNotFoundException(
            EquipmentNotFoundException ex, WebRequest request) {

        ErrorResponse errorResponse = new ErrorResponse(
                ex.getMessage(),
                "EQUIPMENT_NOT_FOUND",
                System.currentTimeMillis(),
                request.getDescription(false).replace("uri=", "")
        );

        return new ResponseEntity<>(errorResponse, HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(InvalidEquipmentStatusException.class)
    public ResponseEntity<ErrorResponse> handleInvalidEquipmentStatusException(
            InvalidEquipmentStatusException ex, WebRequest request) {

        ErrorResponse errorResponse = new ErrorResponse(
                ex.getMessage(),
                "INVALID_EQUIPMENT_STATUS",
                System.currentTimeMillis(),
                request.getDescription(false).replace("uri=", "")
        );

        return new ResponseEntity<>(errorResponse, HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(EquipmentBusinessException.class)
    public ResponseEntity<ErrorResponse> handleEquipmentBusinessException(
            EquipmentBusinessException ex, WebRequest request) {

        ErrorResponse errorResponse = new ErrorResponse(
                ex.getMessage(),
                "BUSINESS_RULE_VIOLATION",
                System.currentTimeMillis(),
                request.getDescription(false).replace("uri=", "")
        );

        return new ResponseEntity<>(errorResponse, HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<ValidationErrorResponse> handleValidationException(
            MethodArgumentNotValidException ex, WebRequest request) {

        List<ValidationError> validationErrors = ex.getBindingResult().getFieldErrors()
                .stream()
                .map(error -> new ValidationError(
                        error.getField(),
                        error.getDefaultMessage(),
                        error.getRejectedValue()
                ))
                .collect(Collectors.toList());

        ValidationErrorResponse errorResponse = new ValidationErrorResponse(
                "Erreurs de validation",
                "VALIDATION_FAILED",
                System.currentTimeMillis(),
                request.getDescription(false).replace("uri=", ""),
                validationErrors
        );

        return new ResponseEntity<>(errorResponse, HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(Exception.class)
    public ResponseEntity<ErrorResponse> handleGenericException(
            Exception ex, WebRequest request) {

        ErrorResponse errorResponse = new ErrorResponse(
                "Une erreur interne s'est produite",
                "INTERNAL_SERVER_ERROR",
                System.currentTimeMillis(),
                request.getDescription(false).replace("uri=", "")
        );

        return new ResponseEntity<>(errorResponse, HttpStatus.INTERNAL_SERVER_ERROR);
    }
}
