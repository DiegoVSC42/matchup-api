package dev.diegovsc42.MatchUp_API.service.strategy.separacao;

import dev.diegovsc42.MatchUp_API.model.Partida;
import org.springframework.stereotype.Component;

@Component
public class MeioStrategy implements SeparacaoStrategy{
    @Override
    public Partida separarJogadores(int quantidadeMovida, Partida partida) {
        int tamanhoEquipes = partida.getEquipeA().getTamanho();
        int inicio = (tamanhoEquipes / 2) - (quantidadeMovida / 2);
        for(int i = inicio; i < inicio + quantidadeMovida; i++){
            String aux = partida.getEquipeA().getJogadores().get(i);
            partida.getEquipeA().getJogadores().set(i, partida.getEquipeB().getJogadores().get(i));
            partida.getEquipeB().getJogadores().set(i, aux);
        }
        return partida;
    }
}
