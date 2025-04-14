package dev.diegovsc42.MatchUp_API.service.strategy.separacao;

import dev.diegovsc42.MatchUp_API.model.Partida;
import dev.diegovsc42.MatchUp_API.model.TipoSeparacao;
import org.springframework.stereotype.Component;

@Component
public class PrimeirosStrategy implements SeparacaoStrategy{
    @Override
    public Partida separarJogadores(int quantidadeMovida, Partida partida) {
        for(int i = 0 ; i < quantidadeMovida; i++){
            String aux = partida.getEquipeA().getJogadores().get(i);
            partida.getEquipeA().getJogadores().set(i, partida.getEquipeB().getJogadores().get(i));
            partida.getEquipeB().getJogadores().set(i, aux);
        }
        return partida;
    }
}
