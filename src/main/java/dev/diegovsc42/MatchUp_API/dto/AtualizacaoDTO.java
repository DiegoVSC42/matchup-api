package dev.diegovsc42.MatchUp_API.dto;

import dev.diegovsc42.MatchUp_API.enums.EquipePerdedora;
import dev.diegovsc42.MatchUp_API.enums.validation.ValueOfEnum;
import dev.diegovsc42.MatchUp_API.model.Partida;
import jakarta.validation.constraints.NotNull;

public record AtualizacaoDTO(
        @NotNull
        @ValueOfEnum(enumClass=EquipePerdedora.class, message = "deve ser um dos valores: [A | B]")
        String equipePerdedora,
        @NotNull
        Partida partida
    ) {
}
