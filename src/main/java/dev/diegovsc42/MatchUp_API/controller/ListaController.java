package dev.diegovsc42.MatchUp_API.controller;


import dev.diegovsc42.MatchUp_API.documentation.ListaControllerDocs;
import dev.diegovsc42.MatchUp_API.service.ListaService;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/lista")
@Tag(name = "Lista de jogadores", description = "Local onde pode ser feita a formatacão de uma lista para os padrões da API")
public class ListaController implements ListaControllerDocs {
    @Autowired
    private ListaService listaService;

    @PostMapping(value = "/formatar-lista", consumes = MediaType.TEXT_PLAIN_VALUE)
    public ResponseEntity<?> formatarLista(@RequestBody String lista) {
        List<String> nomes = listaService.extrairNomes(lista);
        return ResponseEntity.ok().body(nomes);
    }
}
