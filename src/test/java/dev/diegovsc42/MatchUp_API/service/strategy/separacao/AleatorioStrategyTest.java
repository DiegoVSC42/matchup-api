package dev.diegovsc42.MatchUp_API.service.strategy.separacao;

import dev.diegovsc42.MatchUp_API.model.Equipe;
import dev.diegovsc42.MatchUp_API.model.Partida;
import dev.diegovsc42.MatchUp_API.service.PartidaService;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.ArrayList;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
@SpringBootTest
class AleatorioStrategyTest {

    @Autowired
    PartidaService partidaService;

    @Test
    @DisplayName("Deveria manter os times")
    void test2() {
        //Arrange
        Partida partida = new Partida(
                new Equipe(6, new ArrayList<>(List.of("Lucas", "Mariana", "João", "Ana", "Carlos", "Paulo"))),
                new Equipe(6, new ArrayList<>(List.of("Beatriz", "Felipe", "Larissa", "Gabriel", "Juliana", "Renato"))),
                new Equipe(3, new ArrayList<>(List.of("Rafael", "Camila", "Thiago")))
        );

        Partida partidaEsperada = new Partida(
                new Equipe(6, new ArrayList<>(List.of("Lucas", "Mariana", "João", "Ana", "Carlos", "Paulo"))),
                new Equipe(6, new ArrayList<>(List.of("Beatriz", "Felipe", "Larissa", "Gabriel", "Juliana", "Renato"))),
                new Equipe(3, new ArrayList<>(List.of("Rafael", "Camila", "Thiago")))
        );

        partidaEsperada.getEquipeA().getJogadores().sort(String::compareTo);
        partidaEsperada.getEquipeB().getJogadores().sort(String::compareTo);

        //Act
        Partida resultado = partidaService.separarEquipes(0, "ALEATORIO", partida);
        resultado.getEquipeA().getJogadores().sort(String::compareTo);
        resultado.getEquipeB().getJogadores().sort(String::compareTo);

        //Assert
        assertThat(partidaEsperada).isEqualTo(resultado);
    }

    @Test
    @DisplayName("Deveria inverter os times")
    void test3() {
        //Arrange
        Partida partida = new Partida(
                new Equipe(6, new ArrayList<>(List.of("Lucas", "Mariana", "João", "Ana", "Carlos", "Paulo"))),
                new Equipe(6, new ArrayList<>(List.of("Beatriz", "Felipe", "Larissa", "Gabriel", "Juliana", "Renato"))),
                new Equipe(3, new ArrayList<>(List.of("Rafael", "Camila", "Thiago")))
        );

        Partida partidaEsperada = new Partida(
                new Equipe(6, new ArrayList<>(List.of("Beatriz", "Felipe", "Larissa", "Gabriel", "Juliana", "Renato"))),
                new Equipe(6, new ArrayList<>(List.of("Lucas", "Mariana", "João", "Ana", "Carlos", "Paulo"))),
                new Equipe(3, new ArrayList<>(List.of("Rafael", "Camila", "Thiago")))
        );

        partidaEsperada.getEquipeA().getJogadores().sort(String::compareTo);
        partidaEsperada.getEquipeB().getJogadores().sort(String::compareTo);

        //Act
        Partida resultado = partidaService.separarEquipes(6, "ALEATORIO", partida);
        resultado.getEquipeA().getJogadores().sort(String::compareTo);
        resultado.getEquipeB().getJogadores().sort(String::compareTo);

        //Assert
        assertThat(partidaEsperada).isEqualTo(resultado);
    }
}