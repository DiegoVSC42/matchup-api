package dev.diegovsc42.MatchUp_API.dto;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;

import java.util.List;

public record InicioDTO(
        @Min(value = 1)
        @NotNull
        Integer tamanhoEquipes,
        @NotEmpty()
        @NotNull
        List<String>jogadores
    ) {
}
