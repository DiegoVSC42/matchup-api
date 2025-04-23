package dev.diegovsc42.MatchUp_API.controller;

import dev.diegovsc42.MatchUp_API.documentation.partida.iniciarPartidaDocs;
import dev.diegovsc42.MatchUp_API.dto.AtualizacaoDTO;
import dev.diegovsc42.MatchUp_API.dto.InicioDTO;
import dev.diegovsc42.MatchUp_API.dto.SeparacaoDTO;
import dev.diegovsc42.MatchUp_API.model.Partida;
import dev.diegovsc42.MatchUp_API.service.PartidaService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


@RestController
@RequestMapping("/partida")
public class PartidaController implements iniciarPartidaDocs {

    @Autowired
    private PartidaService partidaService;

    @PostMapping("/iniciar")
    public ResponseEntity<Partida> iniciarPartida(@RequestBody @Valid InicioDTO request) {
        Partida partida = partidaService.criarPartida(request.jogadores(), request.tamanhoEquipes());
        return ResponseEntity.ok(partida);
    }

    @PutMapping("/atualizar")
    @Operation(summary = "Atualiza uma partida substituindo os jogadores do time derrotado pelos jogadores da reserva", method = "POST")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Partida atualizada com sucesso"),
            @ApiResponse(responseCode = "400", description = "Partida e/ou EquipePerdedora inválidos")
    })
    public ResponseEntity<Partida> atualizarPartida(@Valid @RequestBody AtualizacaoDTO request) {
        Partida partida = partidaService.atualizarPartida(request.partida(),request.equipePerdedora());
        return ResponseEntity.ok(partida);
    }

    @PutMapping("/separar")
    public ResponseEntity<?> separarJogadores(@Valid @RequestBody SeparacaoDTO request) {
        if (request.quantidadeMovida() > request.partida().getEquipeA().getTamanho()) {
            return ResponseEntity.badRequest().body("A quantidade de jogadores a serem movidos é maior que o tamanho do time");
        }
        Partida partida = partidaService.separarEquipes(
                request.quantidadeMovida(),
                request.tipoSeparacao(),
                request.partida()
        );
        return ResponseEntity.ok(partida);
    }
}
