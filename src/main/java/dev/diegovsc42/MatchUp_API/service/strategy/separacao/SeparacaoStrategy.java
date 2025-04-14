package dev.diegovsc42.MatchUp_API.service.strategy.separacao;

import dev.diegovsc42.MatchUp_API.model.Partida;
import dev.diegovsc42.MatchUp_API.model.TipoSeparacao;

public interface SeparacaoStrategy {
    Partida separarJogadores(int quantidadeMovida, Partida partida);
}
