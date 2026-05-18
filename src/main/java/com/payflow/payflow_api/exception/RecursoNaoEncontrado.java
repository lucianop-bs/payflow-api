package com.payflow.payflow_api.exception;

public class RecursoNaoEncontrado extends RuntimeException{
    public RecursoNaoEncontrado(String mensagem){
        super(mensagem);
    }
}
