package dev.diegovsc42.MatchUp_API.documentation.partida;

import dev.diegovsc42.MatchUp_API.dto.InicioDTO;
import dev.diegovsc42.MatchUp_API.model.Partida;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.ExampleObject;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.parameters.RequestBody;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;

public interface IniciarPartidaDocs {

    String bodyEX1 =
        """
            {
            	"tamanhoEquipes":6,
            	"jogadores":    \s
            	[
            		"Marco",
            		"Leandro",
            		"Renato",
            		"Rogério",
            		"Caio",
            		"Nina",
            		"Letícia",
            		"Daniel",
            		"Eduardo",
            		"Camila",
            		"Otávio",
            		"Fernanda",
            		"Gustavo",
            		"Rafael",
            		"Beatriz",
            		"Pedro",
            		"Carolina",
            		"Vinicius",
            		"Júlia",
            		"Thiago",
            		"Larissa"
            	]
            }
        """;
    String bodyEX2 = "\"\"";
    String bodyEX3 =
        """
            {
                "tamanhoEquipes":0,
                "jogadores":\s
            ["Marco","Leandro","Renato"]}
        """;
    String bodyEX4 =
        """
            {
                "tamanhoEquipes":1,
                "jogadores":\s
            []
            }
        """;
    String response200 =
        """
            {
                "equipeA": {
                    "tamanho": 6,
                    "jogadores": [
                        "Marco",
                        "Leandro",
                        "Renato",
                        "Rogério",
                        "Caio",
                        "Nina"
                    ]
                },
                "equipeB": {
                    "tamanho": 6,
                    "jogadores": [
                        "Letícia",
                        "Daniel",
                        "Eduardo",
                        "Camila",
                        "Otávio",
                        "Fernanda"
                    ]
                },
                "reserva": {
                    "tamanho": 9,
                    "jogadores": [
                        "Gustavo",
                        "Rafael",
                        "Beatriz",
                        "Pedro",
                        "Carolina",
                        "Vinicius",
                        "Júlia",
                        "Thiago",
                        "Larissa"
                    ]
                }
            }
        """;
    String responseBodyEmpty = """
        {
        	"erros": {
        		"body": "corpo da requisição está ausente"
        	}
        }
        """;

    String responseEquipeZero = """
        {
        	"erros": {
        		"tamanhoEquipes": "deve ser maior que ou igual à 1"
        	}
        }
        """;

    String responseJogadoresVazio = """
        {
            "erros": {
                "jogadores": "não deve estar vazio"
            }
        }
        """;
    @Operation(
            summary = "Inicia uma partida a partir de uma lista de jogadores",
            description = "Processa uma lista formatada e um valor para tamanho das equipes e retorna as equipes separadas por ordem de chegada",
            method = "POST",
            requestBody = @RequestBody(
                    description = "Valor para tamanho das equipe e lista de jogadores formatada",
                    content = @Content(
                            mediaType = MediaType.APPLICATION_JSON_VALUE,
                            schema = @Schema(implementation = InicioDTO.class),
                            examples = {
                                    @ExampleObject(
                                            summary = "Lista válida",
                                            name = "Formato válido - 21 jogadores e times de tamanho 6",
                                            value = bodyEX1
                                    ),
                                    @ExampleObject(
                                            summary = "Lista sem conteúdo",
                                            name = "Formato inválido - corpo vazio",
                                            value = bodyEX2
                                    ),
                                    @ExampleObject(
                                            summary = "Tamanho de equipes igual a zero",
                                            name = "Formato inválido - tamanho de equipes inválido, deve ser maior que zero",
                                            value = bodyEX3
                                    ),
                                    @ExampleObject(
                                            summary = "Lista de jogadores vazia",
                                            name = "Formato inválido - lista de jogadores não pode estar vazia",
                                            value = bodyEX4
                                    ),
                            }
                    )
            )
    )
    @ApiResponses(value = {
            @ApiResponse(
                    responseCode = "200",
                    description = "Partida iniciada com sucesso",
                    content = @Content(
                            mediaType = MediaType.APPLICATION_JSON_VALUE,
                            schema = @Schema(implementation = InicioDTO.class),
                            examples = {
                                    @ExampleObject(
                                            summary = "Lista válida",
                                            name = "Formato válido - 21 jogadores e times de tamanho 6",
                                            value = response200
                                    ),
                            }
                    )
            ),
            @ApiResponse(
                    responseCode = "400",
                    description = "Requisição inválida",
                    content = @Content(
                            mediaType = MediaType.APPLICATION_JSON_VALUE,
                            examples = {
                                    @ExampleObject(
                                            summary = "Lista sem conteúdo",
                                            name = "Formato inválido - corpo vazio",
                                            value = responseBodyEmpty
                                    ),
                                    @ExampleObject(
                                            summary = "Tamanho de equipes igual a zero",
                                            name = "Formato inválido - tamanho de equipes inválido, deve ser maior que zero",
                                            value = responseEquipeZero
                                    ),
                                    @ExampleObject(
                                            summary = "Lista de jogadores vazia",
                                            name = "Formato inválido - lista de jogadores não pode estar vazia",
                                            value = responseJogadoresVazio
                                    )
                            }
                    )
            )
    })
    ResponseEntity<Partida> iniciarPartida(InicioDTO request);

}
