package dev.diegovsc42.MatchUp_API.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@EqualsAndHashCode
@NoArgsConstructor
@AllArgsConstructor
public class Equipe {
    @EqualsAndHashCode.Include
    int tamanho;
    @EqualsAndHashCode.Include
    List<String> jogadores;


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
