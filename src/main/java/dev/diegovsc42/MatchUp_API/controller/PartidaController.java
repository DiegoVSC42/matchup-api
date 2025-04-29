package dev.diegovsc42.MatchUp_API.controller;

import dev.diegovsc42.MatchUp_API.documentation.partida.AtualizarPartidaDocs;
import dev.diegovsc42.MatchUp_API.documentation.partida.IniciarPartidaDocs;
import dev.diegovsc42.MatchUp_API.documentation.partida.SepararJogadoresDocs;
import dev.diegovsc42.MatchUp_API.dto.AtualizacaoDTO;
import dev.diegovsc42.MatchUp_API.dto.InicioDTO;
import dev.diegovsc42.MatchUp_API.dto.SeparacaoDTO;
import dev.diegovsc42.MatchUp_API.model.Partida;
import dev.diegovsc42.MatchUp_API.service.PartidaService;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


@RestController
@RequestMapping("/partida")
@Tag(name = "Gerenciamento de Partidas", description = "Local onde pode ser feito o inicio/atualização de partidas e a separação de jogadores")
public class PartidaController implements IniciarPartidaDocs, AtualizarPartidaDocs, SepararJogadoresDocs {

    @Autowired
    private PartidaService partidaService;

    @PostMapping("/iniciar")
    public ResponseEntity<Partida> iniciarPartida(@RequestBody @Valid InicioDTO request) {
        Partida partida = partidaService.iniciarPartida(request.jogadores(), request.tamanhoEquipes());
        return ResponseEntity.ok(partida);
    }

    @PutMapping("/atualizar")
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
