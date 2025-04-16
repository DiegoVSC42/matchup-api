package dev.diegovsc42.MatchUp_API.service;

import dev.diegovsc42.MatchUp_API.model.Equipe;
import dev.diegovsc42.MatchUp_API.model.EquipePerdedora;
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
    class criarPartidaTests {

        @Test
        @DisplayName("Deveria separar os 6 primeiros para a equipe A, depois mais 6 para a B e o restante para a reserva")
        void test1() {
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

        @Test
        @DisplayName("Deveria separar os 6 primeiros para a equipe A, depois mais 6 para a B e a reserva ficar vazia")
        void test2() {
            //Arrange
            List<String> nomes = List.of(
                    "Lucas", "Mariana", "João", "Ana", "Carlos", "Paulo",
                    "Beatriz", "Felipe", "Larissa", "Gabriel", "Juliana", "Renato"
            );
            int tamanhoEquipes = 6;
            Partida partidaEsperada = new Partida(
                    new Equipe(6, new ArrayList<>(List.of("Lucas", "Mariana", "João", "Ana", "Carlos", "Paulo"))),
                    new Equipe(6, new ArrayList<>(List.of("Beatriz", "Felipe", "Larissa", "Gabriel", "Juliana", "Renato"))),
                    new Equipe(0, new ArrayList<>())
            );

            //Act
            Partida resultado = partidaService.criarPartida(nomes, tamanhoEquipes);

            //Assert
            assertThat(partidaEsperada).isEqualTo(resultado);
        }

        @Test
        @DisplayName("Deveria adicionar todos na equipe A quando o número de jogadores for igual ao tamanho da equipe e não houver jogadores suficientes para equipe B")
        void test3() {
            //Arrange
            List<String> nomes = List.of("Lucas", "Mariana", "João", "Ana", "Carlos", "Paulo");
            int tamanhoEquipes = 6;
            Partida partidaEsperada = new Partida(
                    new Equipe(6, new ArrayList<>(List.of("Lucas", "Mariana", "João", "Ana", "Carlos", "Paulo"))),
                    new Equipe(6, new ArrayList<>()),
                    new Equipe(0, new ArrayList<>())
            );

            //Act
            Partida resultado = partidaService.criarPartida(nomes, tamanhoEquipes);

            //Assert
            assertThat(partidaEsperada).isEqualTo(resultado);
        }

        @Test
        @DisplayName("Deveria retornar todas as equipes vazias quando a lista de nomes estiver vazia")
        void test4() {
            //Arrange
            List<String> nomes = List.of();
            int tamanhoEquipes = 5;
            Partida partidaEsperada = new Partida(
                    new Equipe(5, new ArrayList<>()),
                    new Equipe(5, new ArrayList<>()),
                    new Equipe(0, new ArrayList<>())
            );

            //Act
            Partida resultado = partidaService.criarPartida(nomes, tamanhoEquipes);

            //Assert
            assertThat(partidaEsperada).isEqualTo(resultado);
        }

    }

    @Nested
    @DisplayName("atualizarPartida")
    class atualizarPartidaTest {

        @Test
        @DisplayName("Deveria colocar os 3 jogadores da reserva no inicio da equipe A e os 3 ultimos da equipe A vão para o final da reserva")
        void test1() {
            //Arrange
            Partida partida = new Partida(
                    new Equipe(6, new ArrayList<>(List.of("Lucas", "Mariana", "João", "Ana", "Carlos", "Paulo"))),
                    new Equipe(6, new ArrayList<>(List.of("Beatriz", "Felipe", "Larissa", "Gabriel", "Juliana", "Renato"))),
                    new Equipe(3, new ArrayList<>(List.of("Rafael", "Camila", "Thiago")))
            );

            EquipePerdedora equipePerdedora = EquipePerdedora.A;

            Partida partidaEsperada = new Partida(
                    new Equipe(6, new ArrayList<>(List.of("Rafael", "Camila", "Thiago", "Lucas", "Mariana", "João"))),
                    new Equipe(6, new ArrayList<>(List.of("Beatriz", "Felipe", "Larissa", "Gabriel", "Juliana", "Renato"))),
                    new Equipe(3, new ArrayList<>(List.of("Ana", "Carlos", "Paulo")))
            );

            //Act
            Partida resultado = partidaService.atualizarPartida(partida, equipePerdedora);

            //Assert
            assertThat(partidaEsperada).isEqualTo(resultado);
        }

        @Test
        @DisplayName("Deveria colocar os 3 jogadores da reserva no inicio da equipe B e os 3 ultimos da equipe B vão para o final da reserva")
        void test2() {
            //Arrange
            Partida partida = new Partida(
                    new Equipe(6, new ArrayList<>(List.of("Lucas", "Mariana", "João", "Ana", "Carlos", "Paulo"))),
                    new Equipe(6, new ArrayList<>(List.of("Beatriz", "Felipe", "Larissa", "Gabriel", "Juliana", "Renato"))),
                    new Equipe(3, new ArrayList<>(List.of("Rafael", "Camila", "Thiago")))
            );

            EquipePerdedora equipePerdedora = EquipePerdedora.B;

            Partida partidaEsperada = new Partida(
                    new Equipe(6, new ArrayList<>(List.of("Lucas", "Mariana", "João", "Ana", "Carlos", "Paulo"))),
                    new Equipe(6, new ArrayList<>(List.of("Rafael", "Camila", "Thiago","Beatriz", "Felipe", "Larissa"))),
                    new Equipe(3, new ArrayList<>(List.of("Gabriel", "Juliana", "Renato")))
            );

            //Act
            Partida resultado = partidaService.atualizarPartida(partida, equipePerdedora);

            //Assert
            assertThat(partidaEsperada).isEqualTo(resultado);
        }

        @Test
        @DisplayName("Deveria colocar os 6 primeiros jogadores da reserva na equipe A e todos os jogadores da equipe A vão para o final da reserva")
        void test3() {
            //Arrange
            Partida partida = new Partida(
                    new Equipe(6, new ArrayList<>(List.of("Marco", "Leandro", "Renato", "Rogério", "Caio", "Nina"))),
                    new Equipe(6, new ArrayList<>(List.of("Letícia", "Daniel", "Eduardo", "Camila", "Otávio", "Fernanda"))),
                    new Equipe(9, new ArrayList<>(List.of("Gustavo", "Rafael", "Beatriz", "Pedro", "Carolina", "Vinicius", "Júlia", "Thiago", "Larissa")))
            );

            EquipePerdedora equipePerdedora = EquipePerdedora.A;

            Partida partidaEsperada = new Partida(
                    new Equipe(6, new ArrayList<>(List.of("Gustavo", "Rafael", "Beatriz", "Pedro", "Carolina", "Vinicius"))),
                    new Equipe(6, new ArrayList<>(List.of("Letícia","Daniel","Eduardo","Camila","Otávio","Fernanda"))),
                    new Equipe(9, new ArrayList<>(List.of("Júlia", "Thiago", "Larissa","Marco", "Leandro", "Renato", "Rogério", "Caio", "Nina")))
            );

            //Act
            Partida resultado = partidaService.atualizarPartida(partida, equipePerdedora);

            //Assert
            assertThat(partidaEsperada).isEqualTo(resultado);
        }

        @Test
        @DisplayName("Deveria colocar os 6 primeiros jogadores da reserva na equipe B e todos os jogadores da equipe B vão para o final da reserva")
        void test4() {
            //Arrange
            Partida partida = new Partida(
                    new Equipe(6, new ArrayList<>(List.of("Marco", "Leandro", "Renato", "Rogério", "Caio", "Nina"))),
                    new Equipe(6, new ArrayList<>(List.of("Letícia", "Daniel", "Eduardo", "Camila", "Otávio", "Fernanda"))),
                    new Equipe(9, new ArrayList<>(List.of("Gustavo", "Rafael", "Beatriz", "Pedro", "Carolina", "Vinicius", "Júlia", "Thiago", "Larissa")))
            );

            EquipePerdedora equipePerdedora = EquipePerdedora.B;

            Partida partidaEsperada = new Partida(
                    new Equipe(6, new ArrayList<>(List.of("Marco", "Leandro", "Renato", "Rogério", "Caio", "Nina"))),
                    new Equipe(6, new ArrayList<>(List.of("Gustavo", "Rafael", "Beatriz", "Pedro", "Carolina", "Vinicius"))),
                    new Equipe(9, new ArrayList<>(List.of("Júlia", "Thiago", "Larissa","Letícia","Daniel","Eduardo","Camila","Otávio","Fernanda")))
            );

            //Act
            Partida resultado = partidaService.atualizarPartida(partida, equipePerdedora);

            //Assert
            assertThat(partidaEsperada).isEqualTo(resultado);
        }
    }

}