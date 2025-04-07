package dev.diegovsc42.MatchUp_API.service;

import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

@Service
public class ListaService {

    public List<String> formataNomes(String lista){

        Pattern pattern = Pattern.compile("^\\d+\\.\\s*(.+)", Pattern.MULTILINE);
        Matcher matcher = pattern.matcher(lista);

        List<String> nomes = new ArrayList<>();

        while (matcher.find()) {
            nomes.add(matcher.group(1).trim());
        }

        return nomes;
    }

}
