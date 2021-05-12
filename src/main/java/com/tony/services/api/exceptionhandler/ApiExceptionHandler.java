package com.tony.services.api.exceptionhandler;

import com.tony.services.domain.exception.DomainException;
import com.tony.services.domain.exception.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import java.time.LocalDateTime;
import java.time.OffsetDateTime;
import java.util.ArrayList;
import java.util.List;

@ControllerAdvice
public class ApiExceptionHandler extends ResponseEntityExceptionHandler {

    @Autowired
    private MessageSource messageSource;

    // trata erro ao lançados pela DomainException
    @ExceptionHandler(DomainException.class)
    public ResponseEntity<Object> handlerDomainException(DomainException ex, WebRequest request) {

        HttpStatus status = HttpStatus.BAD_REQUEST;

        Problem problem = new Problem();
        problem.setStatus(status.value());
        problem.setTitle(ex.getMessage());
        problem.setDateHour(OffsetDateTime.now());

        return handleExceptionInternal(ex, problem, new HttpHeaders(), status, request);
    }

    // trata erro ao lançados pela EntityNotFoundException
    @ExceptionHandler(EntityNotFoundException.class)
    public ResponseEntity<Object> handlerDomainException(EntityNotFoundException ex, WebRequest request) {

        HttpStatus status = HttpStatus.NOT_FOUND;

        Problem problem = new Problem();
        problem.setStatus(status.value());
        problem.setTitle(ex.getMessage());
        problem.setDateHour(OffsetDateTime.now());

        return handleExceptionInternal(ex, problem, new HttpHeaders(), status, request);
    }

    // Trata exceções genéricas
    @Override
    protected ResponseEntity<Object> handleMethodArgumentNotValid(MethodArgumentNotValidException ex, HttpHeaders headers, HttpStatus status, WebRequest request) {

        List<Problem.FieldException> fieldExceptionList = new ArrayList<Problem.FieldException>();

        for (ObjectError error : ex.getBindingResult().getAllErrors()) {

            String errorName = ((FieldError) error).getField();
            String errorMessage = messageSource.getMessage(error, LocaleContextHolder.getLocale());

            fieldExceptionList.add(new Problem.FieldException(errorName, errorMessage));
        }

        Problem problem = new Problem();
        problem.setStatus(status.value());
        problem.setTitle("Um ou mais campos estão incorretos.");
        problem.setDateHour(OffsetDateTime.now());
        problem.setFieldExceptions(fieldExceptionList);


        // Trata erros específicos
        //... ... ... ... ... ... ... ... (exception, body, headers, status, request)
        return super.handleExceptionInternal(ex, problem, headers, status, request);
    }
}
