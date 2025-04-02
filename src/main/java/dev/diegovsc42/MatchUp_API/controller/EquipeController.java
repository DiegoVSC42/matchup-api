package dev.diegovsc42.MatchUp_API.controller;

import dev.diegovsc42.MatchUp_API.model.Equipe;
import dev.diegovsc42.MatchUp_API.service.EquipeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@RestController
public class EquipeController {

    @Autowired
    private EquipeService equipeService;

    @PostMapping("/cadastrar")
    public ResponseEntity<List<Equipe>> cadastrarLista(@RequestBody String lista, @RequestParam int tamanhoEquipe) {
        System.out.println("Time: " + lista);
        System.out.println("Tamanho: " + tamanhoEquipe);

        List<Equipe> equipes = equipeService.converterListaEmEquipes(lista, tamanhoEquipe);

        return ResponseEntity.status(HttpStatus.CREATED).body(equipes);
    }

    @PutMapping("/atualizar")
    public ResponseEntity<List<Equipe>> atualizarLista(@RequestBody List<Equipe> Equipes, @RequestParam int equipePerdedora) {
        List<Equipe> equipes = equipeService.atualizarEquipes(Equipes,equipePerdedora);
        return ResponseEntity.status(HttpStatus.OK).body(equipes);
    }


}
