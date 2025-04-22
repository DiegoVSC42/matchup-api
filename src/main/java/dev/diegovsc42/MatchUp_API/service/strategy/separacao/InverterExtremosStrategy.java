package dev.diegovsc42.MatchUp_API.service.strategy.separacao;

import dev.diegovsc42.MatchUp_API.model.Partida;

public class InverterExtremosStrategy implements SeparacaoStrategy {
    @Override
    public Partida separarJogadores(int quantidadeMovida, Partida partida) {
        for(int i = 0 ; i < quantidadeMovida; i++){
            String aux = partida.getEquipeA().getJogadores().get(i + quantidadeMovida);
            partida.getEquipeA().getJogadores().set(i + quantidadeMovida, partida.getEquipeB().getJogadores().get(i));
            partida.getEquipeB().getJogadores().set(i, aux);
        }
        return partida;
    }
}
