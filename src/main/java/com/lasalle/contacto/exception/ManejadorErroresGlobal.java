package com.lasalle.contacto.exception;

import com.lasalle.contacto.dto.ErrorResponse;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.util.List;
import java.util.stream.Collectors;

/**
 * Centraliza el manejo de errores de toda la API:
 *  - Errores de validacion (@Valid) -> 400 con la lista de mensajes,
 *    en el mismo formato que espera app.js.
 *  - Cualquier otro error inesperado -> 500 con un mensaje generico,
 *    sin exponer detalles internos (stack traces, mensajes de la BD, etc.).
 */
@RestControllerAdvice
public class ManejadorErroresGlobal {

    private static final Logger log = LoggerFactory.getLogger(ManejadorErroresGlobal.class);

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<ErrorResponse> manejarErroresDeValidacion(MethodArgumentNotValidException ex) {
        List<String> errores = ex.getBindingResult().getFieldErrors().stream()
                .map(FieldError::getDefaultMessage)
                .distinct()
                .collect(Collectors.toList());

        log.warn("Formulario de contacto con errores de validación: {}", errores);
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(new ErrorResponse(errores));
    }

    @ExceptionHandler(Exception.class)
    public ResponseEntity<ErrorResponse> manejarErrorGeneral(Exception ex) {
        log.error("Error inesperado al procesar la solicitud", ex);
        List<String> errores = List.of("Ocurrió un problema al procesar tu solicitud. Intenta nuevamente.");
        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(new ErrorResponse(errores));
    }
}
