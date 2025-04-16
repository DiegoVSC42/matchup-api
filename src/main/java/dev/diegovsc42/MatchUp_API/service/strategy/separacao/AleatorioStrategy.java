package dev.diegovsc42.MatchUp_API.service.strategy.separacao;

import dev.diegovsc42.MatchUp_API.model.Partida;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

@Component
public class AleatorioStrategy implements SeparacaoStrategy {
    @Override
    public Partida separarJogadores(int quantidadeMovida, Partida partida) {
        Collections.shuffle(partida.getEquipeA().getJogadores());
        Collections.shuffle(partida.getEquipeB().getJogadores());

        List<String> selecionadosEquipeA = new ArrayList<>(
                partida.getEquipeA().getJogadores().subList(0, quantidadeMovida));

        List<String> selecionadosEquipeB = new ArrayList<>(
                partida.getEquipeB().getJogadores().subList(0, quantidadeMovida));

        partida.getEquipeA().getJogadores().subList(0, quantidadeMovida).clear();
        partida.getEquipeA().getJogadores().addAll(selecionadosEquipeB);

        partida.getEquipeB().getJogadores().subList(0, quantidadeMovida).clear();
        partida.getEquipeB().getJogadores().addAll(selecionadosEquipeA);

        return partida;
    }
}
