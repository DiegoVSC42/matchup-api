package dev.diegovsc42.MatchUp_API.controller;


import dev.diegovsc42.MatchUp_API.service.ListaService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.ValidationException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;

import java.util.Collections;
import java.util.List;

@RestController
@RequestMapping("/lista")
@Tag(name = "Lista de jogadores", description = "Local onde pode ser feita a formatacão de uma lista para os padrões da APi")
public class ListaController {
    @Autowired
    private ListaService listaService;

    @Operation(summary = "Extrai todos os nomes de jogadores do texto", method = "POST")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = "Lista de jogadores criada com sucesso"),
            @ApiResponse(responseCode = "400", description = "Corpo da requisição vazio ou inválido")
    })
    @PostMapping(value = "/formatar-lista", consumes = MediaType.TEXT_PLAIN_VALUE)
    public ResponseEntity<?> formatarLista(@RequestBody(required = false) String lista) {
        if(lista == null || lista.trim().isEmpty()) {
                return ResponseEntity.badRequest().body("Corpo da requisição vazio ou inválido");
        }
        List<String> nomes = listaService.extrairNomes(lista);
        return ResponseEntity.ok().body(nomes);
    }
}
