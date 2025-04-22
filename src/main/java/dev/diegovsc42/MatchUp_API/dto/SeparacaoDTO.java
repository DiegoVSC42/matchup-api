package dev.diegovsc42.MatchUp_API.dto;

import dev.diegovsc42.MatchUp_API.enums.TipoSeparacao;
import dev.diegovsc42.MatchUp_API.enums.validation.ValueOfEnum;
import dev.diegovsc42.MatchUp_API.model.Partida;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;

public record SeparacaoDTO(
        @Min(value = 1)
        @NotNull
        int quantidadeMovida,

        @NotNull
        @ValueOfEnum(enumClass= TipoSeparacao.class, message = "deve ser um dos valores: [ALEATORIO | EXTREMOS | MEIO | PRIMEIROS | ULTIMOS]")
        String tipoSeparacao,

        @NotNull
        Partida partida
    ){
}
