package dev.diegovsc42.MatchUp_API.controller;


import dev.diegovsc42.MatchUp_API.service.ListaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/lista")
public class ListaController {
    @Autowired
    private ListaService listaService;

    @PostMapping("/formatar-lista")
    public ResponseEntity<List<String>> formatarLista(@RequestBody String lista) {
        List<String> nomes = listaService.extrairNomes(lista);
        return ResponseEntity.status(HttpStatus.CREATED).body(nomes);
    }

}
