package dev.diegovsc42.MatchUp_API.exception;

import dev.diegovsc42.MatchUp_API.dto.ErrorDTO;
import org.springframework.http.HttpStatus;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;

import java.util.HashMap;
import java.util.Map;

@ControllerAdvice
public class ValidationExceptionHandler {

    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ResponseBody
    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ErrorDTO exceptionHandler(MethodArgumentNotValidException ex){
        Map<String, String> mapCamposComErro = new HashMap<>();

        ex.getBindingResult().getAllErrors().forEach((error) -> {
            String fieldName = ((FieldError) error).getField();
            String errorMessage = error.getDefaultMessage();
            mapCamposComErro.put(fieldName, errorMessage);
        });
        return new ErrorDTO(mapCamposComErro);
    }

    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ResponseBody
    @ExceptionHandler(HttpMessageNotReadableException.class)
    public ErrorDTO handleBodyMissing(HttpMessageNotReadableException ex) {
        Map<String, String> erro = new HashMap<>();

        if (ex.getMessage() != null && ex.getMessage().contains("Required request body is missing")) {
            erro.put("body", "corpo da requisição está ausente");
        } else {
            erro.put("body", "corpo da requisição inválido ou malformado");
        }

        return new ErrorDTO(erro);
    }
}
