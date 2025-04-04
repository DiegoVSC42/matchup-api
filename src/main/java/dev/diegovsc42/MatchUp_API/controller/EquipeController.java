package dev.diegovsc42.MatchUp_API.controller;

import dev.diegovsc42.MatchUp_API.model.Equipe;
import dev.diegovsc42.MatchUp_API.model.EquipePerdedora;
import dev.diegovsc42.MatchUp_API.model.Partida;
import dev.diegovsc42.MatchUp_API.service.EquipeService;
import jakarta.servlet.http.Part;
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
    public ResponseEntity<Partida> cadastrarLista(@RequestBody String lista, @RequestParam int tamanhoEquipe) {
        List<String> nomes = equipeService.formataNomes(lista);
        Partida partida = equipeService.separarEquipes(nomes, tamanhoEquipe);
        return ResponseEntity.status(HttpStatus.CREATED).body(partida);
    }

    @PutMapping("/atualizar")
    public ResponseEntity<Partida> atualizarLista(
            @RequestBody Partida partidaAnterior,
            @RequestParam char equipePerdedora
    ) {
        Partida partida = equipeService.atualizarEquipes(partidaAnterior,equipePerdedora);
        return ResponseEntity.status(HttpStatus.OK).body(partida);
    }

    @PutMapping("/separar")
    public ResponseEntity<Partida> separarLista(
            @RequestBody Partida partidaAnterior,
            @RequestParam char equipePerdedora,
            @RequestParam int quantidadeMovida
    ) {
        Partida partidaCopia = new Partida(partidaAnterior.getEquipeA(),partidaAnterior.getEquipeB(),partidaAnterior.getReserva());
        Partida partidaSeparada = equipeService.iniciarNovaPartidaComJogadoresSeparados(partidaCopia,equipePerdedora, quantidadeMovida);

        return ResponseEntity.status(HttpStatus.OK).body(partidaSeparada);
    }
}
