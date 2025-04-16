package dev.diegovsc42.MatchUp_API.service;

import dev.diegovsc42.MatchUp_API.model.Equipe;
import dev.diegovsc42.MatchUp_API.model.EquipePerdedora;
import dev.diegovsc42.MatchUp_API.model.Partida;
import dev.diegovsc42.MatchUp_API.model.TipoSeparacao;
import dev.diegovsc42.MatchUp_API.service.strategy.separacao.*;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
public class PartidaService {
    private final Map<TipoSeparacao, SeparacaoStrategy> mapStrategy = Map.of(
            TipoSeparacao.PRIMEIROS, new PrimeirosStrategy(),
            TipoSeparacao.ALEATORIO, new AleatorioStrategy(),
            TipoSeparacao.MEIO, new MeioStrategy(),
            TipoSeparacao.ULTIMOS, new UltimosStrategy()
    );

    public Partida criarPartida(List<String> nomes, int tamanhoEquipes){
        Partida partida = new Partida(
                new Equipe(tamanhoEquipes, new ArrayList<>()),
                new Equipe(tamanhoEquipes, new ArrayList<>()),
                new Equipe(Math.max(0, nomes.size() - (tamanhoEquipes * 2)), new ArrayList<>())
        );

        for(int i = 0; i < nomes.size(); i++){
            if(i < tamanhoEquipes){
                partida.getEquipeA().getJogadores().add(nomes.get(i));
            }else if(i < tamanhoEquipes * 2){
                partida.getEquipeB().getJogadores().add(nomes.get(i));
            }else{
                partida.getReserva().getJogadores().add(nomes.get(i));
            }
        }

        return partida;
    }

    public Partida atualizarPartida(Partida partida, EquipePerdedora equipePerdedora) {
        Partida novaPartida = new Partida(partida.getEquipeA(),partida.getEquipeB(),partida.getReserva());

        if(equipePerdedora.equals(EquipePerdedora.A)) {
            novaPartida.setEquipeA(substituirJogadoresDaEquipe(novaPartida.getEquipeA(), novaPartida.getReserva()));
        } else {
            novaPartida.setEquipeB(substituirJogadoresDaEquipe(novaPartida.getEquipeB(), novaPartida.getReserva()));
        }

        return novaPartida;
    }

    public Partida separarEquipes(int quantidadeMovida, TipoSeparacao tipoSeparacao, Partida partida) {
        return mapStrategy.get(tipoSeparacao).separarJogadores(quantidadeMovida, partida);
    }

    private Equipe substituirJogadoresDaEquipe(Equipe perdedores, Equipe reservas){
        Equipe novaEquipe = new Equipe(perdedores.getTamanho(),perdedores.getJogadores());
        List<String> jogadoresRemovidosDaReserva = new ArrayList<>();

        int menorTamanho = Math.min(perdedores.getTamanho() - 1, reservas.getTamanho() - 1);

        for (int i = menorTamanho; i >= 0; i--) {
            String jogadorSubstituto = reservas.removerJogador(i);
            perdedores.adicionarJogadorNoInicio(jogadorSubstituto);

            String jogadorRemovido = perdedores.removerJogadorDoFinal();
            jogadoresRemovidosDaReserva.add(jogadorRemovido);
        }
        for (int i = jogadoresRemovidosDaReserva.size() - 1; i >= 0; i--) {
            reservas.getJogadores().add(jogadoresRemovidosDaReserva.get(i));
        }
        return novaEquipe;
    }



}
