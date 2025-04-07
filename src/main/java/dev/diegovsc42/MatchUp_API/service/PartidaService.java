package dev.diegovsc42.MatchUp_API.service;

import dev.diegovsc42.MatchUp_API.model.Equipe;
import dev.diegovsc42.MatchUp_API.model.EquipePerdedora;
import dev.diegovsc42.MatchUp_API.model.Partida;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

@Service
public class PartidaService {

    public Partida separarEquipes(List<String> nomes, int tamanhoEquipes){
        Partida partida = new Partida(
                new Equipe(tamanhoEquipes, new ArrayList<>()),
                new Equipe(tamanhoEquipes, new ArrayList<>()),
                new Equipe(nomes.size() - (tamanhoEquipes*2), new ArrayList<>())
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
    public Partida atualizarEquipes(Partida partida, char equipePerdedora) {
        Partida novaPartida = new Partida(partida.getEquipeA(),partida.getEquipeB(),partida.getReserva());

        if(EquipePerdedora.fromChar(equipePerdedora).equals(EquipePerdedora.A)) {
            novaPartida.setEquipeA(substituirJogadoresComReserva(novaPartida.getEquipeA(), novaPartida.getReserva()));
        } else {
            novaPartida.setEquipeB(substituirJogadoresComReserva(novaPartida.getEquipeB(), novaPartida.getReserva()));
        }

        return novaPartida;
    }

    public Partida iniciarNovaPartidaComJogadoresSeparados(
            int quantidadeMovida,
            char equipePerdedora,
            Partida partida
        ){
        Partida partidaAtualizada = atualizarEquipes(partida, equipePerdedora);
        return redistribuirJogadores(partidaAtualizada,quantidadeMovida);
    }

    private Partida redistribuirJogadores(Partida partida, int quantidadeMovida) {
        Partida novaPartida = new Partida(partida.getEquipeA(),partida.getEquipeB(),partida.getReserva());
        for(int i = 0 ; i < quantidadeMovida; i++){
            String aux = novaPartida.getEquipeA().getJogadores().get(i);
            novaPartida.getEquipeA().getJogadores().set(i, novaPartida.getEquipeB().getJogadores().get(i));
            novaPartida.getEquipeB().getJogadores().set(i, aux);
        }
        return novaPartida;
    }

    private Equipe substituirJogadoresComReserva(Equipe perdedores, Equipe reservas){
        Equipe novaEquipe = new Equipe(perdedores.getTamanho(),perdedores.getJogadores());
        List<String> jogadoresRemovidosDaReserva = new ArrayList<>();

        int menorTamanho = Math.min(perdedores.getJogadores().size() - 1, reservas.getJogadores().size() - 1);

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
