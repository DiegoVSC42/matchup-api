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
                            "tamanho": 3,
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
                            "tamanho": 3,
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
                            "tamanho": 3,
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
                            "tamanho": 3,
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
                            "tamanho": 3,
                            "jogadores": [
                                "Rafael", "Camila", "Thiago"
                            ]
                        }
                    }
                }
            """;
    String body400QuantidadeInvalida = """
            {
              "quantidadeMovida": 0,
              "tipoSeparacao": "EXTREMOS",
              "partida": {
                "equipeA": {
                  "tamanho": 6,
                  "jogadores": [
                    "Lucas",
                    "Mariana",
                    "João",
                    "Ana",
                    "Carlos",
                    "Paulo"
                  ]
                },
                "equipeB": {
                  "tamanho": 6,
                  "jogadores": [
                    "Beatriz",
                    "Felipe",
                    "Larissa",
                    "Gabriel",
                    "Juliana",
                    "Renato"
                  ]
                },
                "reserva": {
                  "tamanho": 3,
                  "jogadores": [
                    "Rafael",
                    "Camila",
                    "Thiago"
                  ]
                }
              }
            }
            """;
    String body400TipoSeparacaoInvalido = """
            {
              "quantidadeMovida": 3,
              "tipoSeparacao": "",
              "partida": {
                "equipeA": {
                  "tamanho": 6,
                  "jogadores": [
                    "Lucas",
                    "Mariana",
                    "João",
                    "Ana",
                    "Carlos",
                    "Paulo"
                  ]
                },
                "equipeB": {
                  "tamanho": 6,
                  "jogadores": [
                    "Beatriz",
                    "Felipe",
                    "Larissa",
                    "Gabriel",
                    "Juliana",
                    "Renato"
                  ]
                },
                "reserva": {
                  "tamanho": 3,
                  "jogadores": [
                    "Rafael",
                    "Camila",
                    "Thiago"
                  ]
                }
              }
            }
            """;
    String body400PartidaInvalida = """
            {
              "quantidadeMovida": 3,
              "tipoSeparacao": "MEIO",
              "partida": ""
            }
            """;
    String response200Aleatorio =
            """
                    {
                             	"equipeA": {
                             		"tamanho": 6,
                             		"jogadores": [
                             			"Carlos",
                             			"João",
                             			"Mariana",
                             			"Larissa",
                             			"Felipe",
                             			"Beatriz"
                             		]
                             	},
                             	"equipeB": {
                             		"tamanho": 6,
                             		"jogadores": [
                             			"Renato",
                             			"Gabriel",
                             			"Juliana",
                             			"Paulo",
                             			"Ana",
                             			"Lucas"
                             		]
                             	},
                             	"reserva": {
                             		"tamanho": 3,
                             		"jogadores": [
                             			"Rafael",
                             			"Camila",
                             			"Thiago"
                             		]
                             	}
                             }
            """;
    String response200Primeiros = """
            {
                "equipeA": {
                    "tamanho": 6,
                    "jogadores": [
                        "Beatriz",
                        "Felipe",
                        "Larissa",
                        "Ana",
                        "Carlos",
                        "Paulo"
                    ]
                },
                "equipeB": {
                    "tamanho": 6,
                    "jogadores": [
                        "Lucas",
                        "Mariana",
                        "João",
                        "Gabriel",
                        "Juliana",
                        "Renato"
                    ]
                },
                "reserva": {
                    "tamanho": 3,
                    "jogadores": [
                        "Rafael",
                        "Camila",
                        "Thiago"
                    ]
                }
            }
            """;
    String response200Meio = """
            {
            	"equipeA": {
            		"tamanho": 6,
            		"jogadores": [
            			"Lucas",
            			"Mariana",
            			"Larissa",
            			"Gabriel",
            			"Juliana",
            			"Paulo"
            		]
            	},
            	"equipeB": {
            		"tamanho": 6,
            		"jogadores": [
            			"Beatriz",
            			"Felipe",
            			"João",
            			"Ana",
            			"Carlos",
            			"Renato"
            		]
            	},
            	"reserva": {
            		"tamanho": 3,
            		"jogadores": [
            			"Rafael",
            			"Camila",
            			"Thiago"
            		]
            	}
            }
            """;
    String response200Ultimos = """
            {
            	"equipeA": {
            		"tamanho": 6,
            		"jogadores": [
            			"Lucas",
            			"Mariana",
            			"João",
            			"Gabriel",
            			"Juliana",
            			"Renato"
            		]
            	},
            	"equipeB": {
            		"tamanho": 6,
            		"jogadores": [
            			"Beatriz",
            			"Felipe",
            			"Larissa",
            			"Ana",
            			"Carlos",
            			"Paulo"
            		]
            	},
            	"reserva": {
            		"tamanho": 3,
            		"jogadores": [
            			"Rafael",
            			"Camila",
            			"Thiago"
            		]
            	}
            }
            """;
    String response200Extremos = """
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
            		"tamanho": 3,
            		"jogadores": [
            			"Rafael",
            			"Camila",
            			"Thiago"
            		]
            	}
            }
            """;
    String response400QuantidadeInvalida = """
            {
                "erros": {
                    "quantidadeMovida": "deve ser maior que ou igual à 1"
                }
            }
            """;
    String response400TipoSeparacaoInvalido = """
            {
            	"erros": {
            		"tipoSeparacao": "deve ser um dos valores: [ALEATORIO | EXTREMOS | MEIO | PRIMEIROS | ULTIMOS]",
            	}
            }
            """;
    String response400PartidaInvalida = """
            {
            	"erros": {
            		"body": "corpo da requisição inválido ou malformado"
            	}
            }
            """;
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
                                    ),
                                    @ExampleObject(
                                            summary = "Quantidade a ser movida inválida",
                                            name = "separacao-quantidadeMovida-invalida",
                                            value = body400QuantidadeInvalida
                                    ),
                                    @ExampleObject(
                                            summary = "Tipo de separação inválido",
                                            name = "separacao-tipoSeparacao-invalido",
                                            value = body400TipoSeparacaoInvalido
                                    ),
                                    @ExampleObject(
                                            summary = "Partida invalida",
                                            name = "separacao-partida-invalida",
                                            value = body400PartidaInvalida
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
                            examples = {
                                    @ExampleObject(
                                            summary = "Lista completa com 3 jogadores a serem trocados e tipo de separação aleatória",
                                            name = "separacao-aleatoria",
                                            value = response200Aleatorio
                                    ),
                                    @ExampleObject(
                                            summary = "Lista completa com 3 jogadores a serem trocados e tipo de separação pelos primeiros",
                                            name = "separacao-primeiros",
                                            value = response200Primeiros
                                    ),
                                    @ExampleObject(
                                            summary = "Lista completa com 3 jogadores a serem trocados e tipo de separação pelo meio",
                                            name = "separacao-meio",
                                            value = response200Meio
                                    ),
                                    @ExampleObject(
                                            summary = "Lista completa com 3 jogadores a serem trocados e tipo de separação pelos últimos",
                                            name = "separacao-ultimos",
                                            value = response200Ultimos
                                    ),
                                    @ExampleObject(
                                            summary = "Lista completa com 3 jogadores a serem trocados e tipo de separação pelos extremos",
                                            name = "separacao-extremos",
                                            value = response200Extremos
                                    )
                            }
                    )

            ),
            @ApiResponse(
                    responseCode = "400",
                    description = "Dados inválidos",
                    content = @Content(
                            mediaType = MediaType.APPLICATION_JSON_VALUE,
                            examples = {
                                    @ExampleObject(
                                            summary = "Quantidade a ser movida inválida",
                                            name = "separacao-quantidadeMovida-invalida",
                                            value = response400QuantidadeInvalida
                                    ),
                                    @ExampleObject(
                                            summary = "Tipo de separação inválido",
                                            name = "separacao-tipoSeparacao-invalido",
                                            value = response400TipoSeparacaoInvalido
                                    ),
                                    @ExampleObject(
                                            summary = "Partida invalida",
                                            name = "separacao-partida-invalida",
                                            value = response400PartidaInvalida
                                    )
                            }
                    )
            )
    })
    ResponseEntity<?> separarJogadores(SeparacaoDTO quest);

}
