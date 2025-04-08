package dev.diegovsc42.MatchUp_API.dto;

import java.util.List;

public record CadastroDTO(
        int tamanhoEquipes,
        List<String>jogadores
    ) {
}
