package dev.diegovsc42.MatchUp_API.dto;

import dev.diegovsc42.MatchUp_API.enums.EquipePerdedora;
import dev.diegovsc42.MatchUp_API.enums.validation.ValueOfEnum;
import dev.diegovsc42.MatchUp_API.model.Partida;
import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotNull;

@Schema(description = "DTO para atualização de resultados de partida")
public record AtualizacaoDTO(
        @Schema(
                description = "Equipe perdedora da partida",
                example = "A",
                allowableValues = {"A", "B"},
                type = "string"
        )
        @NotNull
        @ValueOfEnum(enumClass=EquipePerdedora.class, message = "deve ser um dos valores: [A | B]")
        String equipePerdedora,
        @Schema(
                description = "Dados completos da partida",
                implementation = Partida.class
        )
        @NotNull
        Partida partida
    ) {
}
