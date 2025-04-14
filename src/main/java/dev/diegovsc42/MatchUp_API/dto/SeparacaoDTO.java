package dev.diegovsc42.MatchUp_API.dto;

import dev.diegovsc42.MatchUp_API.model.Partida;
import dev.diegovsc42.MatchUp_API.model.TipoSeparacao;

public record SeparacaoDTO(
        int quantidadeMovida,
        TipoSeparacao tipoSeparacao,
        Partida partida
    ){
}
