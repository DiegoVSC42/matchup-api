package dev.diegovsc42.MatchUp_API.dto;

import dev.diegovsc42.MatchUp_API.enums.TipoSeparacao;
import dev.diegovsc42.MatchUp_API.enums.validation.ValueOfEnum;
import dev.diegovsc42.MatchUp_API.model.Partida;
import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;

@Schema(description = "DTO para separação de jogadores entre equipes")
public record SeparacaoDTO(
        @Schema(
                description = "Quantidade de jogadores a serem movidos",
                example = "2",
                minimum = "1"
        )
        @Min(value = 1)
        @NotNull
        int quantidadeMovida,

        @Schema(
                description = "Tipo de estratégia de separação",
                example = "ALEATORIO",
                allowableValues = {"ALEATORIO", "EXTREMOS", "MEIO", "PRIMEIROS", "ULTIMOS"}
        )
        @NotNull
        @ValueOfEnum(enumClass = TipoSeparacao.class, message = "deve ser um dos valores: [ALEATORIO | EXTREMOS | MEIO | PRIMEIROS | ULTIMOS]")
        String tipoSeparacao,

        @Schema(
                description = "Dados da partida que será modificada",
                implementation = Partida.class
        )
        @NotNull
        Partida partida
) {}