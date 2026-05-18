package com.payflow.payflow_api.exception;

import com.payflow.payflow_api.dto.response.ErrorResponse;
import org.springframework.http.HttpStatus;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.time.LocalDateTime;
import java.util.List;

import static org.springframework.http.HttpStatus.BAD_REQUEST;
import static org.springframework.http.HttpStatus.NOT_FOUND;

@RestControllerAdvice
public class GlobalExceptionHandler {
    @ExceptionHandler(RecursoNaoEncontrado.class)
    @ResponseStatus(NOT_FOUND)
    public ErrorResponse handleRecursoNaoEncontrado(RecursoNaoEncontrado ex){
        return new ErrorResponse(404,"Recurso não encontrado", List.of(ex.getMessage()), LocalDateTime.now());
    }

    @ExceptionHandler(RegraDeNegocioException.class)
    @ResponseStatus(BAD_REQUEST)
    public ErrorResponse handleRegraDeNegocioException(RegraDeNegocioException ex){
        return new ErrorResponse(400,ex.getMessage(), List.of(ex.getMessage()), LocalDateTime.now());
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    @ResponseStatus(BAD_REQUEST)
    public ErrorResponse handleValidacao(MethodArgumentNotValidException ex){
        List<String> erros = ex.getBindingResult()
                .getFieldErrors()
                .stream()
                .map(FieldError::getDefaultMessage)
                .toList();
         return new ErrorResponse(400,"Erro de validação",erros, LocalDateTime.now());
    }

    @ExceptionHandler(Exception.class)
    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    public ErrorResponse handleGenerico(Exception ex) {
        return new ErrorResponse(500, "Erro interno", List.of("Ocorreu um erro inesperado"), LocalDateTime.now());
    }
}
