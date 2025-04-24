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
    String bodyEX1 =
        """
            {
                    "equipePerdedora": "A",
                    "jogadores":\s
                        [
                        "Marco","Leandro","Renato","Rogério","Caio","Nina","Letícia","Daniel","Eduardo","Camila","Otávio","Fernanda","Gustavo","Rafael","Beatriz","Pedro","Carolina","Vinicius","Júlia","Thiago","Larissa"
                        ]
            }
        """;
    String bodyEX2 =
        """
            {
                    "equipePerdedora": "B",
                    "jogadores":\s
                        [
                        "Marco","Leandro","Renato","Rogério","Caio","Nina","Letícia","Daniel","Eduardo","Camila","Otávio","Fernanda","Gustavo","Rafael","Beatriz","Pedro","Carolina","Vinicius","Júlia","Thiago","Larissa"
                        ]
            }
        """;
    String bodyEX3 =
        """
            {
                    "equipePerdedora": "",
                    "jogadores":\s
                        [
                        "Marco","Leandro","Renato","Rogério","Caio","Nina","Letícia","Daniel","Eduardo","Camila","Otávio","Fernanda","Gustavo","Rafael","Beatriz","Pedro","Carolina","Vinicius","Júlia","Thiago","Larissa"
                        ]
            }
        """;
    String bodyEX4 =
        """
            {
                    "equipePerdedora": "A",
                    "jogadores":\s
                        []
            }
        """;
    String bodyEX5 = "\"\"";
    String responseEX1 = """
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
    String responseEX2  = "Corpo da requisição vazio ou inválido";
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
                                            summary = "Lista completa com A derrotada",
                                            name = "Formato válido - 21 jogadores e equipe perdedora = A",
                                            value = bodyEX1
                                    ),
                                    @ExampleObject(
                                            summary = "Lista completa com B derrotada",
                                            name = "Formato válido - 21 jogadores e equipe perdedora = B",
                                            value = bodyEX2
                                    ),
                                    @ExampleObject(
                                            summary = "Equipe perdedora inválido ou vazio",
                                            name = "Formato inválido - equipe perdedora vazio",
                                            value = bodyEX3
                                    ),
                                    @ExampleObject(
                                            summary = "Lista de jogadores vazia",
                                            name = "Formato inválido - lista de jogadores não pode estar vazia",
                                            value = bodyEX4
                                    ),
                                    @ExampleObject(
                                            summary = "Corpo da requisição vazio",
                                            name = "Formato inválido - corpo da requisição não pode estar vazio",
                                            value = bodyEX5
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
                            examples = @ExampleObject(value = responseEX1)
                    )

            ),
            @ApiResponse(
                    responseCode = "400",
                    description = "Partida e/ou EquipePerdedora inválidos",
                    content = @Content(
                            mediaType = MediaType.APPLICATION_JSON_VALUE,
                            examples = @ExampleObject(value = responseEX2)
                    )
            )
    })
    public ResponseEntity<Partida> atualizarPartida(AtualizacaoDTO quest);
}
