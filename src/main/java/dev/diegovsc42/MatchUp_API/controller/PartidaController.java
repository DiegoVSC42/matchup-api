package dev.diegovsc42.MatchUp_API.controller;

import dev.diegovsc42.MatchUp_API.dto.AtualizacaoDTO;
import dev.diegovsc42.MatchUp_API.dto.InicioDTO;
import dev.diegovsc42.MatchUp_API.dto.SeparacaoDTO;
import dev.diegovsc42.MatchUp_API.model.Partida;
import dev.diegovsc42.MatchUp_API.model.TipoSeparacao;
import dev.diegovsc42.MatchUp_API.service.PartidaService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import jakarta.validation.Valid;
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
    @Operation(summary = "Inicia uma partida a partir de uma lista de jogadores", method = "POST")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = "Partida criada com sucesso"),
            @ApiResponse(responseCode = "400", description = "Lista de jogadores vazia ou inválida")
    })
    public ResponseEntity<Partida> iniciarPartida(@RequestBody @Valid InicioDTO request) {
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
        Partida partida = partidaService.separarEquipes(request.quantidadeMovida(), request.tipoSeparacao(),request.partida());
        return ResponseEntity.status(HttpStatus.OK).body(partida);
    }
}
