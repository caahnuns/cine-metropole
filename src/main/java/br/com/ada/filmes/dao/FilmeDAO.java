package br.com.ada.filmes.dao;

import br.com.ada.filmes.model.Filme;
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
public class FilmeDAO {

    private static ObjectMapper objectMapper = new ObjectMapper();
    private static List<Filme> filmes = new ArrayList<>();
    private static int proximoId = 1;

    public FilmeDAO() throws IOException {
        this.carregarJson();
    }

    public void carregarJson() throws StreamReadException, DatabindException, IOException {
        List<Filme> listaFilmes = objectMapper.readValue(new File("src/main/resources/json/filmes.json"), new TypeReference<List<Filme>>(){});
        filmes = listaFilmes;
        if (listaFilmes.size() > 0) {
            proximoId = listaFilmes.get(listaFilmes.size()-1).getId()+1;
        }
    }

    public void adicionar(Filme filme) {
        filme.setId(proximoId++);
        filmes.add(filme);
    }

    public List<Filme> buscar() {
        return filmes;
    }

    public Filme buscarPorId(int id) {
        return filmes.stream()
                .filter(f -> f.getId() == id)
                .findFirst()
                .orElse(null);
    }

    public List<Filme> buscarMaisCurtidos(int limit) {
        return filmes.stream()
                .sorted(Comparator.comparingInt(Filme::getLike).reversed())
                .limit(limit)
                .collect(Collectors.toList());
    }

    public List<Filme> buscarFavoritos() {
        return filmes.stream()
                .filter(f -> f.isFavorito() == true)
                .collect(Collectors.toList());
    }

    public void atualizar(Filme filme) {
        for(int i = 0; i < filmes.size(); i++) {
            Filme f = filmes.get(i);
            if(f.getId() == filme.getId()) {
                filmes.set(i, filme);
                break;
            }
        }
    }

    public void remover(int id) {
        filmes.removeIf(f -> f.getId() == id);
    }

    public void darLike(int id) {
        for(int i = 0; i < filmes.size(); i++) {
            Filme f = filmes.get(i);
            if(f.getId() == id) {
                f.setLike(f.getLike() + 1);
                break;
            }
        }
    }

    public void dislike(int id) {
        for(int i = 0; i < filmes.size(); i++) {
            Filme f = filmes.get(i);
            if(f.getId() == id) {
                f.setLike(f.getLike() - 1);
                break;
            }
        }
    }

    public void favoritar(int id) {
        for(int i = 0; i < filmes.size(); i++) {
            Filme f = filmes.get(i);
            if(f.getId() == id) {
                if(f.isFavorito()) {
                    f.setFavorito(false);
                } else {
                    f.setFavorito(true);
                }
                break;
            }
        }
    }
}
