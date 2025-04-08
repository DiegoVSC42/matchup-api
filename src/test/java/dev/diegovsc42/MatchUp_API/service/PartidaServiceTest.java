package dev.diegovsc42.MatchUp_API.service;

import dev.diegovsc42.MatchUp_API.model.Equipe;
import dev.diegovsc42.MatchUp_API.model.Partida;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.ArrayList;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest
class PartidaServiceTest {
    @Autowired
    private PartidaService partidaService;

    @Nested
    @DisplayName("criarPartida")
    class criarPartidaTests{

        @Test
        @DisplayName("Deveria separar os 6 primeiros para a equipe A, depois mais 6 para a B e o restante para a reserva")
        void test1(){
            //Arrange
            List<String> nomes = List.of(
                    "Lucas", "Mariana", "João", "Ana", "Carlos", "Paulo",
                    "Beatriz", "Felipe", "Larissa", "Gabriel", "Juliana", "Renato",
                    "Rafael", "Camila", "Thiago"
            );
            int tamanhoEquipes = 6;
            Partida partidaEsperada = new Partida(
                    new Equipe(6, new ArrayList<>(List.of("Lucas", "Mariana", "João", "Ana", "Carlos", "Paulo"))),
                    new Equipe(6, new ArrayList<>(List.of("Beatriz", "Felipe", "Larissa", "Gabriel", "Juliana", "Renato"))),
                    new Equipe(3, new ArrayList<>(List.of("Rafael", "Camila", "Thiago")))
            );

            //Act
            Partida resultado = partidaService.criarPartida(nomes, tamanhoEquipes);

            //Assert
            assertThat(partidaEsperada).isEqualTo(resultado);
        }
    }

}