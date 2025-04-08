package dev.diegovsc42.MatchUp_API.model;

import com.fasterxml.jackson.annotation.JsonCreator;

public enum EquipePerdedora {
    A, B;

    @JsonCreator
    public static EquipePerdedora from(String value) {
        try{
            return EquipePerdedora.valueOf(value.toUpperCase());
        }catch (IllegalArgumentException | NullPointerException e){
            throw new IllegalArgumentException(value + " não é um valor valido para equipe perdedora, tente A ou B");
        }
    }
}
