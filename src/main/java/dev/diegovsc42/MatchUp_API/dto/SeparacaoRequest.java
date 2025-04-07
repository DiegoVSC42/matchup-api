package dev.diegovsc42.MatchUp_API.dto;

import dev.diegovsc42.MatchUp_API.model.Partida;

public record SeparacaoRequest (
        int quantidadeMovida,
        char equipePerdedora,
        Partida partida
    ){
}
