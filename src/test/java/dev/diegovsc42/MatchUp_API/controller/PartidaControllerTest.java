package dev.diegovsc42.MatchUp_API.controller;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;

@SpringBootTest
@AutoConfigureMockMvc
class PartidaControllerTest {

    @Autowired
    MockMvc mvc;

    @Nested
    @DisplayName("Iniciar Partida")
    class iniciarPartidaTests{

        @Test
        @DisplayName("Deve retornar Bad Request(400) para um corpo vazio")
        void test1() throws Exception {
            String json = "";
            var response = mvc.perform(
                    post("/partida/iniciar")
                            .content(json)
                            .contentType(MediaType.APPLICATION_JSON)
            ).andReturn().getResponse();

            assertEquals(400, response.getStatus());
        }
        @Test
        @DisplayName("Deve retornar 200 para corpo válido")
        void test2() throws Exception {
            String json = """
                    {
                    	"tamanhoEquipes":6,
                    	"jogadores":
                    	[   "Marco",
                    		"Leandro",
                    		"Renato",
                    		"Rogério",
                    		"Caio",
                    		"Nina",
                    		"Letícia",
                    		"Daniel",
                    		"Eduardo",
                    		"Camila",
                    		"Otávio",
                    		"Fernanda",
                    		"Gustavo",
                    		"Rafael",
                    		"Beatriz",
                    		"Pedro",
                    		"Carolina",
                    		"Vinicius",
                    		"Júlia",
                    		"Thiago",
                    		"Larissa"
                    	]
                    }
                    """;
            var response = mvc.perform(
                    post("/partida/iniciar")
                            .content(json)
                            .contentType(MediaType.APPLICATION_JSON)
            ).andReturn().getResponse();

            assertEquals(200, response.getStatus());
        }
    }

    @Nested
    @DisplayName("Atualizar Partida")
    class atualizarPartidaTests {

        @Test
        @DisplayName("Deve retornar Bad Request(400) para um corpo vazio")
        void test1() throws Exception {
            String json = "";
            var response = mvc.perform(
                    put("/partida/atualizar")
                            .content(json)
                            .contentType(MediaType.APPLICATION_JSON)
            ).andReturn().getResponse();

            assertEquals(400, response.getStatus());
        }
        @Test
        @DisplayName("Deve retornar 200 para corpo válido")
        void test2() throws Exception {
            String json = """
                    {
                    	"equipePerdedora":"A",
                    	"partida":{
                    		"equipeA": {
                    			"tamanho": 6,
                    			"jogadores": [
                    				"Marco",
                    				"Leandro",
                    				"Renato",
                    				"Rogério",
                    				"Caio",
                    				"Nina"
                    			]
                    		},
                    		"equipeB": {
                    			"tamanho": 6,
                    			"jogadores": [
                    				"Letícia",
                    				"Daniel",
                    				"Eduardo",
                    				"Camila",
                    				"Otávio",
                    				"Fernanda"
                    			]
                    		},
                    		"reserva": {
                    			"tamanho": 9,
                    			"jogadores": [
                    				"Gustavo",
                    				"Rafael",
                    				"Beatriz",
                    				"Pedro",
                    				"Carolina",
                    				"Vinicius",
                    				"Júlia",
                    				"Thiago",
                    				"Larissa"
                    			]
                    		}
                    	}
                    
                    }
                    """;
            var response = mvc.perform(
                    put("/partida/atualizar")
                            .content(json)
                            .contentType(MediaType.APPLICATION_JSON)
            ).andReturn().getResponse();

            assertEquals(200, response.getStatus());
        }
    }

    @Nested
    @DisplayName("Atualizar Partida")
    class separarJogadores {

        @Test
        @DisplayName("Deve retornar Bad Request(400) para um corpo vazio")
        void test1() throws Exception {
            String json = "";
            var response = mvc.perform(
                    put("/partida/separar")
                            .content(json)
                            .contentType(MediaType.APPLICATION_JSON)
            ).andReturn().getResponse();

            assertEquals(400, response.getStatus());
        }
        @Test
        @DisplayName("Deve retornar 200 para corpo válido")
        void test2() throws Exception {
            String json = """
                    {
                      "quantidadeMovida": 3,
                      "tipoSeparacao": "ALEATORIO",
                      "partida": {
                        "equipeA": {
                          "tamanho": 6,
                          "jogadores": [
                            "Lucas",
                            "Mariana",
                            "João",
                            "Ana",
                            "Carlos",
                            "Paulo"
                          ]
                        },
                        "equipeB": {
                          "tamanho": 6,
                          "jogadores": [
                            "Beatriz",
                            "Felipe",
                            "Larissa",
                            "Gabriel",
                            "Juliana",
                            "Renato"
                          ]
                        },
                        "reserva": {
                          "tamanho": 9,
                          "jogadores": [
                            "Rafael",
                            "Camila",
                            "Thiago"
                          ]
                        }
                      }
                    }
                    """;
            var response = mvc.perform(
                    put("/partida/separar")
                            .content(json)
                            .contentType(MediaType.APPLICATION_JSON)
            ).andReturn().getResponse();

            assertEquals(200, response.getStatus());
        }
    }




}