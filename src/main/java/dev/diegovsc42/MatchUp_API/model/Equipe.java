package dev.diegovsc42.MatchUp_API.model;

import lombok.Data;
import java.util.List;

@Data
public class Equipe {
    int tamanho;
    List<String> jogadores;

    public Equipe(int tamanho, List<String> jogadores) {
        this.tamanho = tamanho;
        this.jogadores = jogadores;
    }

    public void adicionarJogadorNoInicio(String jogador) {
        jogadores.add(0, jogador);
    }

    public String removerJogador(int index){
        return jogadores.remove(index);
    }

    public String removerJogadorDoFinal() {
        return jogadores.remove(jogadores.size()-1);
    }


}
