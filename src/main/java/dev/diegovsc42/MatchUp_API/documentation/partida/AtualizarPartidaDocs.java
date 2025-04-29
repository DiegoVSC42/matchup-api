package dev.diegovsc42.MatchUp_API.documentation.partida;

import dev.diegovsc42.MatchUp_API.dto.AtualizacaoDTO;
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

public interface AtualizarPartidaDocs {
    String body200A =
        """
            {
                "equipePerdedora":"A",
                "partida":{
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
            }
        """;
    String body200B =
        """
            {
                    	"equipePerdedora":"B",
                    	"partida":{
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
                    }
        """;
    String body400EquipePerdedoraVazia =
        """
            {
                    	"equipePerdedora":"",
                    	"partida":{
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
                    }
        """;
    String body400PartidaVazia ="""
        {
                "equipePerdedora":"A"
        }
        """;
    String body400BodyVazio = "\"\"";

    String response200A = """
            {
            	"equipeA": {
            		"tamanho": 6,
            		"jogadores": [
            			"Gustavo",
            			"Rafael",
            			"Beatriz",
            			"Pedro",
            			"Carolina",
            			"Vinicius"
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
            			"Júlia",
            			"Thiago",
            			"Larissa",
            			"Marco",
            			"Leandro",
            			"Renato",
            			"Rogério",
            			"Caio",
            			"Nina"
            		]
            	}
            }
            """;

    String response200B = """
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
            			"Gustavo",
            			"Rafael",
            			"Beatriz",
            			"Pedro",
            			"Carolina",
            			"Vinicius"
            		]
            	},
            	"reserva": {
            		"tamanho": 9,
            		"jogadores": [
            			"Júlia",
            			"Thiago",
            			"Larissa",
            			"Letícia",
            			"Daniel",
            			"Eduardo",
            			"Camila",
            			"Otávio",
            			"Fernanda"
            		]
            	}
            }
            """;
    String response400EquipePerdedoraVazia = """
            {
            	"erros": {
            		"equipePerdedora": "deve ser um dos valores: [A | B]"
            	}
            }
            """;
    String response400PartidaVazia = """
            {
            	"erros": {
            		"partida": "não deve ser nulo"
            	}
            }
            """;
    String response400BodyVazio = """
            {
            	"erros": {
            		"body": "corpo da requisição está ausente"
            	}
            }
            """;
    @Operation(
            summary = "Atualiza uma partida substituindo os jogadores do time derrotado pelos jogadores da reserva",
            description = "Processa uma lista formatada e um valor para equipe derrotada e substitui os jogadores dessa equipe pelos da reserva de acordo com a orde mde chegada",
            method = "PUT",
            requestBody = @RequestBody(
                    description = "Valor para tamanho das equipe e lista de jogadores formatada",
                    content = @Content(
                            mediaType = MediaType.APPLICATION_JSON_VALUE,
                            schema = @Schema(implementation = AtualizacaoDTO.class),
                            examples = {
                                    @ExampleObject(
                                            summary = "Partida completa com A derrotada",
                                            name = "Formato válido - 21 jogadores e equipe perdedora = A",
                                            value = body200A
                                    ),
                                    @ExampleObject(
                                            summary = "Partida completa com B derrotada",
                                            name = "Formato válido - 21 jogadores e equipe perdedora = B",
                                            value = body200B
                                    ),
                                    @ExampleObject(
                                            summary = "Equipe perdedora inválido ou vazio",
                                            name = "Formato inválido - equipe perdedora vazio",
                                            value = body400EquipePerdedoraVazia
                                    ),
                                    @ExampleObject(
                                            summary = "Partida vazia",
                                            name = "Formato inválido - lista de jogadores não pode estar vazia",
                                            value = body400PartidaVazia
                                    ),
                                    @ExampleObject(
                                            summary = "Corpo da requisição vazio",
                                            name = "Formato inválido - corpo da requisição não pode estar vazio",
                                            value = body400BodyVazio
                                    ),

                            }
                    )
            )
    )
    @ApiResponses(value = {
            @ApiResponse(
                    responseCode = "200",
                    description = "Partida atualizada com sucesso",
                    content = @Content(
                            mediaType = MediaType.APPLICATION_JSON_VALUE,
                            schema = @Schema(implementation = AtualizacaoDTO.class),
                            examples = {
                                    @ExampleObject(
                                            summary = "Partida completa com A derrotada",
                                            name = "Formato válido - 21 jogadores e equipe perdedora = A",
                                            value = response200A
                                    ),
                                    @ExampleObject(
                                            summary = "Partida completa com B derrotada",
                                            name = "Formato válido - 21 jogadores e equipe perdedora = B",
                                            value = response200B
                                    ),
                            }
                    )

            ),
            @ApiResponse(
                    responseCode = "400",
                    description = "Partida e/ou EquipePerdedora inválidos",
                    content = @Content(
                            mediaType = MediaType.APPLICATION_JSON_VALUE,
                            examples = {
                                    @ExampleObject(
                                            summary = "Equipe perdedora inválido ou vazio",
                                            name = "Formato inválido - equipe perdedora vazio",
                                            value = response400EquipePerdedoraVazia
                                    ),
                                    @ExampleObject(
                                            summary = "Partida vazia",
                                            name = "Formato inválido - lista de jogadores não pode estar vazia",
                                            value = response400PartidaVazia
                                    ),
                                    @ExampleObject(
                                            summary = "Corpo da requisição vazio",
                                            name = "Formato inválido - corpo da requisição não pode estar vazio",
                                            value = response400BodyVazio
                                    ),
                            }
                    )
            )
    })
    ResponseEntity<Partida> atualizarPartida(AtualizacaoDTO quest);
}
