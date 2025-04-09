package dev.diegovsc42.MatchUp_API.service;

import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

@Service
public class ListaService {

    public List<String> extrairNomes(String lista) {
        /*
         * Expressão regular que captura linhas que:
         * - Podem começar com espaços em branco (\\s*)
         * - Seguidas de 1 ou 2 dígitos (\\d{1,2})
         * - Seguidas de até 3 caracteres que não sejam letras ou espaços
         *   (separadores como '-', '*', '=>', etc) (\\s*[^\\w\\s]{1,3})
         * - Seguidas de espaços em branco (\\s*)
         * - E por fim captura o restante da linha como o nome (.+)
         * A flag MULTILINE permite que o padrão seja aplicado linha por linha no texto.
         */
        Pattern patternNumerados = Pattern.compile("^\\s*\\d{1,2}\\s*[^\\w\\s]{1,3}\\s*(.+)$", Pattern.MULTILINE);
        Matcher matcher = patternNumerados.matcher(lista);
        List<String> nomes = new ArrayList<>();

        // Verifica se a lista é numerada (caso original)
        if (matcher.find()) {
            matcher.reset(); // Reinicia o matcher para processar desde o início
            while (matcher.find()) {
                String nome = matcher.group(1)
                        .replaceAll("[\\u00A0\\u2007\\u202F]+", " ")
                        .trim();

                /*
                 * Verifica se a string 'nome' NÃO corresponde a um padrão de horário, como:
                 * - "21 as 23"
                 * - "21as23"
                 * - "21:00 as 23:00"
                 * - "21 as 23:00"
                 *
                 * O padrão corresponde a:
                 * - 1 ou 2 dígitos iniciais (\\d{1,2})
                 * - Opcionalmente, dois pontos ou espaço entre os horários ([:\\s]?)
                 * - A palavra 'as' com ou sem espaço (\\s?as\\s?)
                 * - Mais 1 ou 2 dígitos (\\d{1,2})
                 * - Opcionalmente, minutos no formato ":00" (:(\\d{2}))?
                 *
                 * Se a string NÃO for um horário, então ela é adicionada à lista de nomes.
                 */
                if (!nome.matches("\\d{1,2}[:\\s]?as\\s?\\d{1,2}(:\\d{2})?")) {
                    nomes.add(nome);
                }
            }
        } else {
            // Caso a lista NÃO seja numerada (apenas nomes, um por linha)
            String[] linhas = lista.split("\\r?\\n");
            for (String linha : linhas) {
                String nome = linha.trim();
                // Ignora linhas vazias ou que contenham números (para evitar horários)
                if (!nome.isEmpty() && !nome.matches(".*\\d.*")) {
                    nomes.add(nome);
                }
            }
        }
        return nomes;
    }

}
