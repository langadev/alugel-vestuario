package com.vestuario.alugel.exceptions;


import com.vestuario.alugel.DTO.ErroCampo;
import com.vestuario.alugel.DTO.ErroResposta;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RestControllerAdvice
public class TratadorDeErros {

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<ErroResposta> tratarValidacoes(MethodArgumentNotValidException ex) {
        List<ErroCampo> erros = ex.getBindingResult()
            .getFieldErrors()
            .stream()
            .map(e -> new ErroCampo(e.getField(), e.getDefaultMessage()))
            .collect(Collectors.toList());

        ErroResposta resposta = new ErroResposta(HttpStatus.BAD_REQUEST.value(), "Campos inválidos", erros);
        return ResponseEntity.badRequest().body(resposta);
    }

    @ExceptionHandler(RecursoNaoEncontradoException.class)
    public ResponseEntity<ErroResposta> tratarNaoEncontrado(RecursoNaoEncontradoException ex) {
        ErroResposta resposta = new ErroResposta(HttpStatus.NOT_FOUND.value(), ex.getMessage(), List.of());
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(resposta);
    }

    @ExceptionHandler(IllegalArgumentException.class)
    public ResponseEntity<ErroResposta> tratarConflito(IllegalArgumentException ex) {
        ErroResposta resposta = ErroResposta.conflito(ex.getMessage());
        return ResponseEntity.status(HttpStatus.CONFLICT).body(resposta);
    }

    @ExceptionHandler(Exception.class)
    public ResponseEntity<ErroResposta> erroInterno(Exception ex) {
        ErroResposta resposta = new ErroResposta(
                HttpStatus.INTERNAL_SERVER_ERROR.value(),
                "Erro inesperado: " + ex.getMessage(),
                List.of()
        );
        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(resposta);
    }
}
   

