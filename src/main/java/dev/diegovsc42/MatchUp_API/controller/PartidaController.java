package dev.diegovsc42.MatchUp_API.controller;

import dev.diegovsc42.MatchUp_API.dto.AtualizacaoRequest;
import dev.diegovsc42.MatchUp_API.dto.CadastroRequest;
import dev.diegovsc42.MatchUp_API.dto.SeparacaoRequest;
import dev.diegovsc42.MatchUp_API.model.Equipe;
import dev.diegovsc42.MatchUp_API.model.Partida;
import dev.diegovsc42.MatchUp_API.service.PartidaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@RestController
@RequestMapping("/partida")
public class PartidaController {

    @Autowired
    private PartidaService partidaService;

    @PostMapping("/cadastrar")
    public ResponseEntity<Partida> cadastrarLista(@RequestBody CadastroRequest request) {
        Partida partida = partidaService.separarEquipes(request.jogadores(), request.tamanhoEquipes());
        return ResponseEntity.status(HttpStatus.CREATED).body(partida);
    }

    @PutMapping("/atualizar")
    public ResponseEntity<Partida> atualizarLista(@RequestBody AtualizacaoRequest request) {
        Partida partida = partidaService.atualizarEquipes(request.partida(),request.equipePerdedora());
        return ResponseEntity.status(HttpStatus.OK).body(partida);
    }

    @PutMapping("/separar")
    public ResponseEntity<Partida> separarLista(@RequestBody SeparacaoRequest request) {
        Partida partidaSeparada = partidaService.iniciarNovaPartidaComJogadoresSeparados(request.quantidadeMovida(),request.equipePerdedora(),request.partida());
        return ResponseEntity.status(HttpStatus.OK).body(partidaSeparada);
    }
}
