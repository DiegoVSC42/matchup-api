package dev.diegovsc42.MatchUp_API.service;

import dev.diegovsc42.MatchUp_API.model.Equipe;
import dev.diegovsc42.MatchUp_API.model.EquipePerdedora;
import dev.diegovsc42.MatchUp_API.model.Partida;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class PartidaService {

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

    public Partida separarJogadores(int quantidadeMovida, Partida partida) {
        for(int i = 0 ; i < quantidadeMovida; i++){
            String aux = partida.getEquipeA().getJogadores().get(i);
            partida.getEquipeA().getJogadores().set(i, partida.getEquipeB().getJogadores().get(i));
            partida.getEquipeB().getJogadores().set(i, aux);
        }
        return partida;
    }
}
