package dev.diegovsc42.MatchUp_API.service;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.ArrayList;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest
class ListaServiceTest {

    @Autowired
    private ListaService listaService;

    @Nested
    @DisplayName("extrairNomes")
    class ExtrairNomesTests {

        @Test
        @DisplayName("Deve extrair nomes de lista formatada com números e ponto (ex: '1. Nome')")
        void test1() {
            String lista = """
                Futebol sábado 19h30 às 21h30
                1. Caio
                2. Letícia
                3. Eduardo
                4. Tiago
                5. Ícaro
                6. Rafael
                7. Bruna
                8. Wellington
                9. Douglas (a partir das 20h)
                10. Vitória
                11. Marcelo
                12. Luana
                13. Bruno
                """;
            List<String> esperado = List.of(
                    "Caio", "Letícia", "Eduardo", "Tiago", "Ícaro", "Rafael", "Bruna",
                    "Wellington", "Douglas (a partir das 20h)", "Vitória", "Marcelo", "Luana", "Bruno");
            List<String> resultado = listaService.extrairNomes(lista);
            assertThat(resultado).isEqualTo(esperado);
        }

        @Test
        @DisplayName("Deve retornar lista vazia quando a entrada for vazia")
        void test2() {
            String lista = "";
            List<String> esperado = new ArrayList<>();
            List<String> resultado = listaService.extrairNomes(lista);
            assertThat(resultado).isEqualTo(esperado);
        }

        @Test
        @DisplayName("Deve extrair nomes de lista formatada com números e hífen (ex: '1 - Nome')")
        void test3() {
            String lista = """
                Quem vai quarta?\s
                21:00 as 23:00

                1 - Daniel
                2 - Larissa
                3 - Caio
                4 - Letícia
                5 - Juliana
                6 - Alexandre
                7 - Luísa
                8 - Gabriela
                9 - Fábio
                10 - Renata
                11 - Laís
                12- Vinícius\s
                13- Renan\s
                14- Rafael
                15- Tiago
                """;
            List<String> esperado = List.of(
                    "Daniel", "Larissa", "Caio", "Letícia", "Juliana", "Alexandre", "Luísa",
                    "Gabriela", "Fábio", "Renata", "Laís", "Vinícius", "Renan", "Rafael", "Tiago");
            List<String> resultado = listaService.extrairNomes(lista);
            assertThat(resultado).isEqualTo(esperado);
        }

        @Test
        @DisplayName("Deve extrair nomes mesmo com espaços irregulares ao redor do separador")
        void test4() {
            String lista = """
                1  -    João
                2.     Maria
                3     -Pedro
                4.Ana
                """;
            List<String> esperado = List.of("João", "Maria", "Pedro", "Ana");
            List<String> resultado = listaService.extrairNomes(lista);
            assertThat(resultado).isEqualTo(esperado);
        }

        @Test
        @DisplayName("Deve ignorar linhas que não contêm padrão de lista numerada")
        void test5() {
            String lista = """
                Lista do futsal ⚽
                Confirmados:

                1 - Bruno
                2 - Clara

                Avise se não puder ir!
                3 - Felipe
                """;
            List<String> esperado = List.of("Bruno", "Clara", "Felipe");
            List<String> resultado = listaService.extrairNomes(lista);
            assertThat(resultado).isEqualTo(esperado);
        }

        @Test
        @DisplayName("Deve manter nomes duplicados quando presentes na lista original")
        void test6() {
            String lista = """
                1 - Tiago
                2 - Tiago
                3 - Letícia
                """;
            List<String> esperado = List.of("Tiago", "Tiago", "Letícia");
            List<String> resultado = listaService.extrairNomes(lista);
            assertThat(resultado).isEqualTo(esperado);
        }

        @Test
        @DisplayName("Deve extrair nomes com caracteres especiais (acentos, hífens, pontos)")
        void test7() {
            String lista = """
                1 - José
                2 - André
                3 - Mário Jr.
                4 - João-Paulo
                """;
            List<String> esperado = List.of("José", "André", "Mário Jr.", "João-Paulo");
            List<String> resultado = listaService.extrairNomes(lista);
            assertThat(resultado).isEqualTo(esperado);
        }

        @Test
        @DisplayName("Deve extrair nomes mesmo com numeração fora de ordem sequencial")
        void test8() {
            String lista = """
                3 - Caio
                1 - Tiago
                2 - Rafael
                """;
            List<String> esperado = List.of("Caio", "Tiago", "Rafael");
            List<String> resultado = listaService.extrairNomes(lista);
            assertThat(resultado).isEqualTo(esperado);
        }

        @Test
        @DisplayName("Deve extrair nomes corretamente mesmo com espaços não visíveis (ex: NBSP)")
        void test9() {
            String lista = """
                1 -  Tiago
                2 - Rafael
                3 -   Luana
                """;
            List<String> esperado = List.of("Tiago", "Rafael", "Luana");
            List<String> resultado = listaService.extrairNomes(lista);
            assertThat(resultado).isEqualTo(esperado);
        }

        @Test
        @DisplayName("Deve extrair nomes que contenham emojis junto ao texto")
        void test10() {
            String lista = """
                1 - Ana 🏐
                2 - Juliana 🎉
                """;
            List<String> esperado = List.of("Ana 🏐", "Juliana 🎉");
            List<String> resultado = listaService.extrairNomes(lista);
            assertThat(resultado).isEqualTo(esperado);
        }

        @Test
        @DisplayName("Deve extrair nomes quando separadores forem tabulações ao invés de espaços")
        void test11() {
            String lista = "1\t-\tCaio\n2\t-\tLetícia\n3\t-\tDouglas";
            List<String> esperado = List.of("Caio", "Letícia", "Douglas");
            List<String> resultado = listaService.extrairNomes(lista);
            assertThat(resultado).isEqualTo(esperado);
        }

        @Test
        @DisplayName("Deve extrair nomes compostos ou com pontuação (hífens, vírgulas)")
        void test12() {
            String lista = """
                1 - João-Paulo
                2 - Maria Clara
                3 - Ana, a madrinha
                """;
            List<String> esperado = List.of("João-Paulo", "Maria Clara", "Ana, a madrinha");
            List<String> resultado = listaService.extrairNomes(lista);
            assertThat(resultado).isEqualTo(esperado);
        }

        @Test
        @DisplayName("Deve extrair nomes independentemente do tipo de separador (-, *, =>, .)")
        void test13() {
            String lista = """
                1 - Caio
                2 * Letícia
                3 => Tiago
                4 . Rafael
                """;
            List<String> esperado = List.of("Caio", "Letícia", "Tiago", "Rafael");
            List<String> resultado = listaService.extrairNomes(lista);
            assertThat(resultado).isEqualTo(esperado);
        }

        @Test
        @DisplayName("Deve extrair nomes de lista não numerada (um nome por linha, sem prefixos)")
        void test14() {
            String lista = """
            Vôlei sábado 19h30 às 21h30
            Marco
            
            Leandro
            
            Renato
            
            Rogério
            
            Caio
            
            Nina
            
            Letícia
            
            Daniel
            
            Eduardo\s
            
            Camila
            
            Otávio
            
            Fernanda
            
            Gustavo
            
            Rafael
            
            Beatriz
            
            Pedro
            
            Carolina
            
            Vinicius
            
            Júlia
            
            Thiago
            
            Larissa
        """;
            List<String> esperado = List.of(
                    "Marco", "Leandro", "Renato", "Rogério", "Caio", "Nina",
                    "Letícia", "Daniel", "Eduardo", "Camila", "Otávio", "Fernanda",
                    "Gustavo", "Rafael", "Beatriz", "Pedro", "Carolina", "Vinicius",
                    "Júlia", "Thiago", "Larissa"
            );
            List<String> resultado = listaService.extrairNomes(lista);
            assertThat(resultado).isEqualTo(esperado);
        }

    }
}
