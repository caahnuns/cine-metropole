package br.com.ada.filmes.dao;

import br.com.ada.filmes.model.Ator;
import com.fasterxml.jackson.core.exc.StreamReadException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.DatabindException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.stereotype.Component;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

@Component
public class AtorDAO {

    private static ObjectMapper objectMapper = new ObjectMapper();
    private static List<Ator> atores = new ArrayList<>();
    private static int proximoId = 1;

    public AtorDAO() throws IOException {
        this.carregarJson();
    }

    public void carregarJson() throws StreamReadException, DatabindException, IOException {
        List<Ator> listaAtores = objectMapper.readValue(new File("src/main/resources/json/atores.json"), new TypeReference<List<Ator>>(){});
        atores = listaAtores;
        if (listaAtores.size() > 0) {
            proximoId = listaAtores.get(listaAtores.size()-1).getId()+1;
        }
    }

    public void adicionar(Ator ator) {
        ator.setId(proximoId++);
        atores.add(ator);
    }

    public List<Ator> buscar() { return atores; }

    public Ator buscarPorId(int id) {
        return atores.stream()
                .filter(a -> a.getId() == id)
                .findFirst()
                .orElse(null);
    }

    public void atualizar(Ator ator) {
        for(int i = 0; i < atores.size(); i++) {
            Ator a = atores.get(i);
            if(a.getId() == ator.getId()) {
                atores.set(i, ator);
                break;
            }
        }
    }

    public void remover(int id) { atores.removeIf(a -> a.getId() == id); }
}
