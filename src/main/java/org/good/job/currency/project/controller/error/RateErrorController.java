package org.good.job.currency.project.controller.error;

import jakarta.validation.ConstraintViolationException;
import org.good.job.currency.project.service.exception.ExternalApiNameNotExistsException;
import org.good.job.currency.project.service.exception.RateNotFoundException;
import org.springframework.beans.TypeMismatchException;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.client.RestClientException;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.method.annotation.HandlerMethodValidationException;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;


@ControllerAdvice
public class RateErrorController extends ResponseEntityExceptionHandler {

    @Override
    protected ResponseEntity<Object> handleTypeMismatch(TypeMismatchException ex, HttpHeaders headers,
                                                        HttpStatusCode status, WebRequest request) {
        if (ex.getRootCause() instanceof ExternalApiNameNotExistsException apiNameNotExistsException) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.badRequest().build();
    }

    @Override
    protected ResponseEntity<Object> handleHandlerMethodValidationException(HandlerMethodValidationException ex,
                                                                            HttpHeaders headers, HttpStatusCode status,
                                                                            WebRequest request) {
        return ResponseEntity.badRequest().build();
    }

    @ExceptionHandler(ConstraintViolationException.class)
    public ResponseEntity<Object> handleConstraintViolationException(ConstraintViolationException ex) {
        return ResponseEntity.badRequest().build();
    }

    @ExceptionHandler(RestClientException.class)
    public ResponseEntity<Object> handleRestClientException(RestClientException ex) {
        return ResponseEntity.internalServerError().build();
    }

    @ExceptionHandler(RateNotFoundException.class)
    public ResponseEntity<Object> handleRateNotFoundException(RateNotFoundException ex) {
        return ResponseEntity.notFound().build();
    }

}
