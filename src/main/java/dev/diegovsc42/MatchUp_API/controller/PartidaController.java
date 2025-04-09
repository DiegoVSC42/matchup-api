package dev.diegovsc42.MatchUp_API.controller;

import dev.diegovsc42.MatchUp_API.dto.AtualizacaoDTO;
import dev.diegovsc42.MatchUp_API.dto.inicioDTO;
import dev.diegovsc42.MatchUp_API.dto.SeparacaoDTO;
import dev.diegovsc42.MatchUp_API.model.Partida;
import dev.diegovsc42.MatchUp_API.service.PartidaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


@RestController
@RequestMapping("/partida")
public class PartidaController {

    @Autowired
    private PartidaService partidaService;

    @PostMapping("/iniciar")
    public ResponseEntity<Partida> iniciarPartida(@RequestBody inicioDTO request) {
        Partida partida = partidaService.criarPartida(request.jogadores(), request.tamanhoEquipes());
        return ResponseEntity.status(HttpStatus.CREATED).body(partida);
    }

    @PutMapping("/atualizar")
    public ResponseEntity<Partida> atualizarPartida(@RequestBody AtualizacaoDTO request) {
        Partida partida = partidaService.atualizarPartida(request.partida(),request.equipePerdedora());
        return ResponseEntity.status(HttpStatus.OK).body(partida);
    }

    @PutMapping("/separar")
    public ResponseEntity<Partida> separarJogadores(@RequestBody SeparacaoDTO request) {
        Partida partida = partidaService.separarJogadores(request.quantidadeMovida(),request.partida());
        return ResponseEntity.status(HttpStatus.OK).body(partida);
    }
}
