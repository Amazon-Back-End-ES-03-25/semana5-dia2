package com.ironhack.semana5_dia2.exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.method.annotation.MethodArgumentTypeMismatchException;

@RestControllerAdvice // para decir que escuche al Rest Controller y a las excepciones que lanza
public class GlobalExceptionHandler {

    // Un método que me devuelva HTTP 404 + mensaje cuando no encuentra el producto/libro
    // @ExceptionHandler métodos dentro del Handler que indican QUÉ hacer cuando se lanza X excepción
    // Entre paréntesis y llaves {} le indico las excepciones que aplican a este método
    @ExceptionHandler({ProductNotFoundException.class, BookNotFoundException.class})
    public ResponseEntity<String> handleProductNotFound(Exception ex) { // Ponemos Exeption para que entren ambas clases
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(ex.getMessage());
    }

    // ResponseEntity --> wrapper class para mandar objetos + códigos de estado

    // Un método que me devuelva HTTP 400 + mensaje cuando (p.e) en el endpoint mando una string cuando esperaba un Long
    @ExceptionHandler(MethodArgumentTypeMismatchException.class)
    public ResponseEntity<String> handleMethodArgumentTypeMismatch(MethodArgumentTypeMismatchException ex) {
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(ex.getMessage());
    }

    // Para agrupar en un solo método todos los momentos que quiero devolver HTTP 400 + mensaje
    @ExceptionHandler({MethodArgumentNotValidException.class, HttpMessageNotReadableException.class})
    // entre paréntesis y llaves {} le indico las excepciones que aplican a este método
    public ResponseEntity<String> handleHttpMessageNotReadable(Exception ex) {
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(ex.getMessage());
    }
}
