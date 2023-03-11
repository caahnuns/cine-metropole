package br.com.ada.filmes.dao;

import br.com.ada.filmes.model.Filme;
import br.com.ada.filmes.model.Noticia;
import com.fasterxml.jackson.core.exc.StreamReadException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.DatabindException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.stereotype.Component;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

@Component
public class NoticiaDAO {

    private static ObjectMapper objectMapper = new ObjectMapper();
    private static List<Noticia> noticias = new ArrayList<>();
    private static int proximoId = 1;

    public NoticiaDAO() throws IOException {
        this.carregarJson();
    }

    public void carregarJson() throws StreamReadException, DatabindException, IOException {
        List<Noticia> listaNoticias = objectMapper.readValue(new File("src/main/resources/json/noticias.json"), new TypeReference<List<Noticia>>(){});
        noticias = listaNoticias;
        if (listaNoticias.size() > 0) {
            proximoId = listaNoticias.get(listaNoticias.size()-1).getId()+1;
        }
    }

    public void adicionar(Noticia noticia) {
        noticia.setId(proximoId++);
        noticias.add(noticia);
    }

    public List<Noticia> buscar() {
        return noticias;
    }

    public Noticia buscarPorId(int id) {
        return noticias.stream()
                .filter(n -> n.getId() == id)
                .findFirst()
                .orElse(null);
    }

    public List<Noticia> buscarMaisRecentes(int limit) {
        return noticias.stream()
                .sorted(Comparator.comparingInt(Noticia::getId).reversed())
                .limit(limit)
                .collect(Collectors.toList());
    }

    public void atualizar(Noticia noticia) {
        for(int i = 0; i < noticias.size(); i++) {
            Noticia n = noticias.get(i);
            if(n.getId() == noticia.getId()) {
                noticias.set(i, noticia);
                break;
            }
        }
    }

    public void remover(int id) {
        noticias.removeIf(n -> n.getId() == id);
    }

}
