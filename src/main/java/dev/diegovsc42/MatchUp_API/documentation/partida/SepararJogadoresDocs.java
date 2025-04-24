package dev.diegovsc42.MatchUp_API.documentation.partida;

import dev.diegovsc42.MatchUp_API.dto.SeparacaoDTO;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.ExampleObject;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.parameters.RequestBody;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;

public interface SepararJogadoresDocs {
    String bodyAleatorio =
            """
                {	
                    "quantidadeMovida": 3,
                    "tipoSeparacao": "ALEATORIO",
                    "partida": {
                        "equipeA": {
                            "tamanho": 6,
                            "jogadores": [
                                "Lucas", "Mariana", "João", "Ana", "Carlos", "Paulo"
                            ]
                        },
                        "equipeB": {
                            "tamanho": 6,
                            "jogadores": [
                                "Beatriz", "Felipe", "Larissa", "Gabriel", "Juliana", "Renato"
                            ]
                        },
                        "reserva": {
                            "tamanho": 9,
                            "jogadores": [
                                "Rafael", "Camila", "Thiago"
                            ]
                        }
                    }
                }
            """;

    String bodyPrimeiros =
            """
                {
                    "quantidadeMovida": 3,
                    "tipoSeparacao": "PRIMEIROS",
                    "partida": {
                        "equipeA": {
                            "tamanho": 6,
                            "jogadores": [
                                "Lucas", "Mariana", "João", "Ana", "Carlos", "Paulo"
                            ]
                        },
                        "equipeB": {
                            "tamanho": 6,
                            "jogadores": [
                                "Beatriz", "Felipe", "Larissa", "Gabriel", "Juliana", "Renato"
                            ]
                        },
                        "reserva": {
                            "tamanho": 9,
                            "jogadores": [
                                "Rafael", "Camila", "Thiago"
                            ]
                        }
                    }
                }
            """;

    String bodyMeio =
            """
                {
                    "quantidadeMovida": 3,
                    "tipoSeparacao": "MEIO",
                    "partida": {
                        "equipeA": {
                            "tamanho": 6,
                            "jogadores": [
                                "Lucas", "Mariana", "João", "Ana", "Carlos", "Paulo"
                            ]
                        },
                        "equipeB": {
                            "tamanho": 6,
                            "jogadores": [
                                "Beatriz", "Felipe", "Larissa", "Gabriel", "Juliana", "Renato"
                            ]
                        },
                        "reserva": {
                            "tamanho": 9,
                            "jogadores": [
                                "Rafael", "Camila", "Thiago"
                            ]
                        }
                    }
                }
            """;

    String bodyUltimos =
            """
                {
                    "quantidadeMovida": 3,
                    "tipoSeparacao": "ULTIMOS",
                    "partida": {
                        "equipeA": {
                            "tamanho": 6,
                            "jogadores": [
                                "Lucas", "Mariana", "João", "Ana", "Carlos", "Paulo"
                            ]
                        },
                        "equipeB": {
                            "tamanho": 6,
                            "jogadores": [
                                "Beatriz", "Felipe", "Larissa", "Gabriel", "Juliana", "Renato"
                            ]
                        },
                        "reserva": {
                            "tamanho": 9,
                            "jogadores": [
                                "Rafael", "Camila", "Thiago"
                            ]
                        }
                    }
                }
            """;

    String bodyExtremos =
            """
                {
                    "quantidadeMovida": 3,
                    "tipoSeparacao": "EXTREMOS",
                    "partida": {
                        "equipeA": {
                            "tamanho": 6,
                            "jogadores": [
                                "Lucas", "Mariana", "João", "Ana", "Carlos", "Paulo"
                            ]
                        },
                        "equipeB": {
                            "tamanho": 6,
                            "jogadores": [
                                "Beatriz", "Felipe", "Larissa", "Gabriel", "Juliana", "Renato"
                            ]
                        },
                        "reserva": {
                            "tamanho": 9,
                            "jogadores": [
                                "Rafael", "Camila", "Thiago"
                            ]
                        }
                    }
                }
            """;

    String response200 =
            """
                    {
                    	"equipeA": {
                    		"tamanho": 6,
                    		"jogadores": [
                    			"Lucas",
                    			"Mariana",
                    			"João",
                    			"Beatriz",
                    			"Felipe",
                    			"Larissa"
                    		]
                    	},
                    	"equipeB": {
                    		"tamanho": 6,
                    		"jogadores": [
                    			"Ana",
                    			"Carlos",
                    			"Paulo",
                    			"Gabriel",
                    			"Juliana",
                    			"Renato"
                    		]
                    	},
                    	"reserva": {
                    		"tamanho": 9,
                    		"jogadores": [
                    			"Rafael",
                    			"Camila",
                    			"Thiago"
                    		]
                    	}
                    }
            """;
    String response400 ="Corpo da requisição vazio ou inválido";
    @Operation(
            summary = "Separa os times de uma partida de várias formas através do valor para quantidade a ser movida e do tipo de separação",
            description = "Processa uma lista formatada, um valor N para quantidade movida e um valor para o tipo de separação que pode ser:\n\n" +
                    "- **ALEATORIO**: Separa N jogadores dos times de forma aleatória\n" +
                    "- **PRIMEIROS**: Substitui os N primeiros da Equipe A com os N primeiros da Equipe B\n" +
                    "- **MEIO**: Substitui N jogadores da equipe A com N jogadores da Equipe B a partir do meio da lista\n" +
                    "- **ULTIMOS**: Substitui os N últimos da Equipe A com os N últimos da Equipe B\n" +
                    "- **EXTREMOS**: Substitui os jogadores das equipes de forma que os N últimos jogadores da Equipe A serão os N primeiros jogadores da Equipe B e vice-versa.",
            method = "PUT",
            requestBody = @RequestBody(
                    description = "Valor para a quantidade de jogadores que serão trocados, qual será o tipo de troca e os dados da partida",
                    content = @Content(
                            mediaType = MediaType.APPLICATION_JSON_VALUE,
                            schema = @Schema(implementation = SeparacaoDTO.class),
                            examples = {
                                    @ExampleObject(
                                            summary = "Lista completa com 3 jogadores a serem trocados e tipo de separação aleatória",
                                            name = "separacao-aleatoria",
                                            value = bodyAleatorio
                                    ),
                                    @ExampleObject(
                                            summary = "Lista completa com 3 jogadores a serem trocados e tipo de separação pelos primeiros",
                                            name = "separacao-primeiros",
                                            value = bodyPrimeiros
                                    ),
                                    @ExampleObject(
                                            summary = "Lista completa com 3 jogadores a serem trocados e tipo de separação pelo meio",
                                            name = "separacao-meio",
                                            value = bodyMeio
                                    ),
                                    @ExampleObject(
                                            summary = "Lista completa com 3 jogadores a serem trocados e tipo de separação pelos últimos",
                                            name = "separacao-ultimos",
                                            value = bodyUltimos
                                    ),
                                    @ExampleObject(
                                            summary = "Lista completa com 3 jogadores a serem trocados e tipo de separação pelos extremos",
                                            name = "separacao-extremos",
                                            value = bodyExtremos
                                    )
                            }
                    )
            )
    )
    @ApiResponses(value = {
            @ApiResponse(
                    responseCode = "200",
                    description = "Jogadores separados com sucesso",
                    content = @Content(
                            mediaType = MediaType.APPLICATION_JSON_VALUE,
                            schema = @Schema(implementation = SeparacaoDTO.class),
                            examples = @ExampleObject(value = response200)
                    )

            ),
            @ApiResponse(
                    responseCode = "400",
                    description = "Dados inválidos",
                    content = @Content(
                            mediaType = MediaType.APPLICATION_JSON_VALUE,
                            examples = @ExampleObject(value = response400)
                    )
            )
    })
    public ResponseEntity<?> separarJogadores(SeparacaoDTO quest);

}
