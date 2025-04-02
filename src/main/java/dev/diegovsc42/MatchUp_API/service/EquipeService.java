package dev.diegovsc42.MatchUp_API.service;

import dev.diegovsc42.MatchUp_API.model.Equipe;
import org.apache.logging.log4j.util.Strings;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

@Service
public class EquipeService {

    public List<Equipe> converterListaEmEquipes(String lista, int tamanhoEquipes){
        List<String> nomes = formataNomes(lista);
        return separarEquipes(nomes, tamanhoEquipes);
    }
    private List<String> formataNomes(String lista){
        // Regex para capturar os nomes da lista numerada
        Pattern pattern = Pattern.compile("^\\d+\\.\\s*(.+)", Pattern.MULTILINE);
        Matcher matcher = pattern.matcher(lista);

        List<String> nomes = new ArrayList<>();
        // Itera sobre os nomes encontrados
        while (matcher.find()) {
            nomes.add(matcher.group(1).trim());
        }
        return nomes;
    }
    private List<Equipe> separarEquipes(List<String> nomes,int tamanhoEquipes){
        List<Equipe> equipes = new ArrayList<>();
        Equipe equipe1 = new Equipe(tamanhoEquipes);
        Equipe equipe2 = new Equipe(tamanhoEquipes);
        Equipe reservas = new Equipe(nomes.size() - (tamanhoEquipes*2));

        for(int i = 0; i < nomes.size(); i++){
            if(i < tamanhoEquipes){
                equipe1.getJogadores().add(nomes.get(i));
            }else if(i < tamanhoEquipes * 2){
                equipe2.getJogadores().add(nomes.get(i));
            }else{
                reservas.getJogadores().add(nomes.get(i));
            }
        }
        equipes.add(equipe1);
        equipes.add(equipe2);
        equipes.add(reservas);

        return equipes;
    }


    public List<Equipe> atualizarEquipes(List<Equipe> equipes, int equipePerdedora) {
        Equipe perdedores = equipes.get(equipePerdedora);
        Equipe reservas = equipes.get(2);
        realizarSubstituicoes(perdedores, reservas);
        return equipes;
    }

    private void realizarSubstituicoes(Equipe perdedores,Equipe reservas){
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

    }
}
