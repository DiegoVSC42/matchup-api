package dev.diegovsc42.MatchUp_API.dto;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;

import java.util.List;

public record InicioDTO(
        @NotNull(message = "O tamanho da equipe não pode ser nulo")
        @Positive(message = "O tamanho da equipe deve ser maior que zero")
        int tamanhoEquipes,
        @NotEmpty(message = "A lista de jogadores não pode estar vazia")
        List<String>jogadores
    ) {
}
