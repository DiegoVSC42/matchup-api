package dev.diegovsc42.MatchUp_API.dto;

import dev.diegovsc42.MatchUp_API.model.EquipePerdedora;
import dev.diegovsc42.MatchUp_API.model.Partida;

public record AtualizacaoRequest(
        char equipePerdedora,
        Partida partida
    ) {
}
