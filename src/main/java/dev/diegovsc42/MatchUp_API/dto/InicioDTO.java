package dev.diegovsc42.MatchUp_API.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;

import java.util.List;

@Schema(description = "DTO para iniciar uma nova partida")
public record InicioDTO(
        @Schema(
                description = "Tamanho das equipes (mínimo 1 jogador por equipe)",
                example = "6",
                minimum = "1"
        )
        @Min(value = 1)
        @NotNull
        Integer tamanhoEquipes,

        @Schema(
                description = "Lista de nomes dos jogadores participantes",
                example = "[\"João\", \"Maria\", \"Carlos\", \"Ana\", \"Pedro\", \"Luiza\"]"
        )
        @NotEmpty
        @NotNull
        List<String> jogadores
) {}
