package dev.diegovsc42.MatchUp_API.model;

import java.util.ArrayList;
import java.util.List;


public class Equipe {
    int tamanho;
    List<String> jogadores;

    public Equipe(int tamanho) {
        this.tamanho = tamanho;
        this.jogadores = new ArrayList<>();
    }
    public int getTamanho() {
        return tamanho;
    }

    public List<String> getJogadores() {
        return jogadores;
    }

    public void adicionarJogadorNoInicio(String jogador) {
        jogadores.add(0, jogador);
    }

    public void adicionarJogadorNoFinal(String jogador) {
        jogadores.add(jogador);
    }

    public String removerJogadorDoInicio() {
        return jogadores.remove(0);
    }

    public String removerJogador(int index){
        return jogadores.remove(index);
    }
    public String removerJogadorDoFinal() {
        return jogadores.remove(jogadores.size()-1);
    }

}
