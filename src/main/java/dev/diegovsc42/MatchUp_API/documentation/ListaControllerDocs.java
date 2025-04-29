package dev.diegovsc42.MatchUp_API.documentation;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.ExampleObject;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.parameters.RequestBody;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;

import java.util.List;

public interface ListaControllerDocs {
    String bodyEX1 = """
                                                    Vôlei Segunda 19h30 às 21h30
                                                    
                                                    1. Pedro \s
                                                    2. Lívia \s
                                                    3. Rafael \s
                                                    4. Júlia \s
                                                    5. Caio \s
                                                    6. Nina \s
                                                    7. Vinícius \s
                                                    8. Lorena \s
                                                    9. Igor \s
                                                    10. Letícia \s
                                                    11. Marcos \s
                                                    12. Clara \s
                                                    13. Gustavo \s
                                                    14. Beatriz \s
                                                    15. Lucas \s
                                                    """;
    String bodyEX2 ="\"\"";

    String response200 = """
            [
            	"Pedro",
            	"Lívia",
            	"Rafael",
            	"Júlia",
            	"Caio",
            	"Nina",
            	"Vinícius",
            	"Lorena",
            	"Igor",
            	"Letícia",
            	"Marcos",
            	"Clara",
            	"Gustavo",
            	"Beatriz",
            	"Lucas"
            ]
            """;
    String response400 = """
            {
            	"erros": {
            		"body": "corpo da requisição está ausente"
            	}
            }
            """;

    @Operation(
            summary = "Extrai todos os nomes de jogadores do texto",
            description = "Processa um texto contendo nomes de jogadores e retorna uma lista formatada conforme os padrões da API",
            requestBody = @RequestBody(
                    description = "Texto com lista de jogadores não formatada",
                    required = true,
                    content = @Content(
                            mediaType = MediaType.TEXT_PLAIN_VALUE,
                            schema = @Schema(type = "string"),
                            examples = {
                                    @ExampleObject(
                                            name = "Formato válido - 15 jogadores",
                                            summary = "Lista completa com 15 nomes numerados",
                                            value = bodyEX1
                                    ),
                                    @ExampleObject(
                                            name = "Formato inválido - vazio",
                                            summary = "Corpo sem conteúdo",
                                            value = bodyEX2
                                    ),
                            }
                    )
            )
    )
    @ApiResponses(value = {
            @ApiResponse(
                    responseCode = "200",
                    description = "Lista de jogadores formatada com sucesso",
                    content = @Content(
                            mediaType = MediaType.APPLICATION_JSON_VALUE,
                            schema = @Schema(implementation = List.class),
                            examples = @ExampleObject(value = response200)
                    )
            ),
            @ApiResponse(
                    responseCode = "400",
                    description = "Corpo da requisição vazio ou inválido",
                    content = @Content(
                            mediaType = MediaType.APPLICATION_JSON_VALUE,
                            examples = @ExampleObject(value = response400)
                    )
            ),

    })
    ResponseEntity<?> formatarLista(String lista);
}
