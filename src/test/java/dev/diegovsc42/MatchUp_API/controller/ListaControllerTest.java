package dev.diegovsc42.MatchUp_API.controller;

import dev.diegovsc42.MatchUp_API.service.ListaService;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.json.JacksonTester;
import org.springframework.http.MediaType;
import org.springframework.test.context.bean.override.mockito.MockitoBean;
import org.springframework.test.web.servlet.MockMvc;

import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;


@SpringBootTest
@AutoConfigureMockMvc
class ListaControllerTest {

    @MockitoBean
    private ListaService listaService;

    @Autowired
    MockMvc mvc;


    @Test
    @DisplayName("Deve retornar Bad Request e uma mensagem dizendo o erro")
    void test1() throws Exception {
        String lista = "";
        var response = mvc.perform(
                post("/lista/formatar-lista")
                        .content(lista)
                        .contentType(MediaType.TEXT_PLAIN_VALUE)
        ).andReturn().getResponse();

        assertEquals(400, response.getStatus());
        assertEquals("Corpo da requisição vazio ou inválido", response.getContentAsString());
    }
    @Test
    @DisplayName("Deve retornar Ok e um JSON com uma lista de nomes")
    void test2() throws Exception {
        String lista = """
                Maria 
                João 
                Ana 
                Pedro 
                Luiza 
                Carlos 
                Julia 
                Lucas 
                Beatriz 
                Gabriel 
                Isabela 
                Rafael 
                Amanda 
                Felipe 
                Laura 
                """;
        List<String> listaEsperada = Arrays.asList("Maria","João","Ana","Pedro","Luiza","Carlos","Julia","Lucas","Beatriz","Gabriel","Isabela","Rafael","Amanda","Felipe","Laura");
        var response = mvc.perform(
                post("/lista/formatar-lista")
                        .content(lista)
                        .contentType(MediaType.TEXT_PLAIN_VALUE)
        ).andReturn().getResponse();

        assertEquals(200, response.getStatus());
    }
}