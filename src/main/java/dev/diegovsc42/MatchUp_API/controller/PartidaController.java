package dev.diegovsc42.MatchUp_API.controller;

import dev.diegovsc42.MatchUp_API.dto.AtualizacaoDTO;
import dev.diegovsc42.MatchUp_API.dto.CadastroDTO;
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

    @PostMapping("/cadastrar")
    public ResponseEntity<Partida> cadastrarLista(@RequestBody CadastroDTO request) {
        Partida partida = partidaService.separarEquipes(request.jogadores(), request.tamanhoEquipes());
        return ResponseEntity.status(HttpStatus.CREATED).body(partida);
    }

    @PutMapping("/atualizar")
    public ResponseEntity<Partida> atualizarLista(@RequestBody AtualizacaoDTO request) {
        Partida partida = partidaService.atualizarEquipes(request.partida(),request.equipePerdedora());
        return ResponseEntity.status(HttpStatus.OK).body(partida);
    }

    @PutMapping("/separar")
    public ResponseEntity<Partida> separarLista(@RequestBody SeparacaoDTO request) {
        Partida partidaSeparada = partidaService.iniciarNovaPartidaComJogadoresSeparados(request.quantidadeMovida(),request.equipePerdedora(),request.partida());
        return ResponseEntity.status(HttpStatus.OK).body(partidaSeparada);
    }
}
