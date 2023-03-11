package br.com.ada.filmes.controller;

import br.com.ada.filmes.dao.FilmeDAO;
import br.com.ada.filmes.model.Filme;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

@Controller
@RequestMapping("/filme")
public class FilmeController {

    @Autowired
    private FilmeDAO filmeDAO;

    @GetMapping("/novo")
    public String novo(Model model) {
        model.addAttribute("filme", new Filme());
        return "filme_novo";
    }

    @PostMapping("/novo")
    public String adicionar(Filme filme) {
        filmeDAO.adicionar(filme);
        return "redirect:/filme";
    }

    @GetMapping
    public String listar(Model model) {
        List<Filme> lista = filmeDAO.buscar();
        model.addAttribute("filmes", lista);
        return "filme_listar";
    }

    @GetMapping("/editar/{id}")
    public String editar(@PathVariable int id, Model model) {
        Filme filme = filmeDAO.buscarPorId(id);
        model.addAttribute("filme", filme);
        return "filme_editar";
    }

    @PostMapping("/editar")
    public String atualizar(Filme filme) {
        filmeDAO.atualizar(filme);
        return "redirect:/filme";
    }

    @GetMapping("/remover/{id}")
    public String remover(@PathVariable int id) {
        filmeDAO.remover(id);
        return "redirect:/filme";
    }

    @GetMapping("/favoritos")
    public String listarFavoritos(Model model) {
        List<Filme> lista = filmeDAO.buscarFavoritos();
        model.addAttribute("filmes", lista);
        return "filme_favorito";
    }

    @GetMapping("/favoritar/{id}")
    public String favoritar(@PathVariable int id) {
        filmeDAO.favoritar(id);
        return "redirect:/";
    }

    @GetMapping("/like/{id}")
    public String darLike(@PathVariable int id) {
        filmeDAO.darLike(id);
        return "redirect:/";
    }

    @GetMapping("/dislike/{id}")
    public String dislike(@PathVariable int id) {
        filmeDAO.dislike(id);
        return "redirect:/";
    }

}