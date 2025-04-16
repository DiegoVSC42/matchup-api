package dev.diegovsc42.MatchUp_API.enums;

import com.fasterxml.jackson.annotation.JsonCreator;

public enum TipoSeparacao {
    PRIMEIROS,
    ALEATORIO,
    MEIO,
    ULTIMOS;

    @JsonCreator
    public static TipoSeparacao from(String value) {
        try{
            return TipoSeparacao.valueOf(value.toUpperCase());
        }catch (IllegalArgumentException | NullPointerException e){
            throw new IllegalArgumentException(value + " não é um valor valido para Tipo de Separacao, tente Primeiros ou Aleatório");
        }
    }
}
