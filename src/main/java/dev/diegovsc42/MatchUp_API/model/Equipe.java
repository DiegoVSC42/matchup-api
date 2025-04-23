package dev.diegovsc42.MatchUp_API.model;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@EqualsAndHashCode
@NoArgsConstructor
@AllArgsConstructor
@Schema(description = "Modelo de equipe")
public class Equipe {
    @EqualsAndHashCode.Include
    @Schema(description = "Tamanho da equipe", example = "6")
    int tamanho;
    @EqualsAndHashCode.Include
    @Schema(description = "Lista de jogadores da equipe", example = "[\"Lucas\", \"Mariana\", \"João\", \"Beatriz\", \"Felipe\", \"Larissa\"]")
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
