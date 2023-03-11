package br.com.ada.filmes.model;

public class Noticia {

    private int id;
    private String titulo;
    private String descricao;
    private String imagem;
    private String autor;

    public Noticia() {

    }
    public Noticia(int id, String titulo, String descricao, String imagem, String autor) {
        this.id = id;
        this.titulo = titulo;
        this.descricao = descricao;
        this.imagem = imagem;
        this.autor = autor;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getTitulo() {
        return titulo;
    }

    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    public String getImagem() {
        return imagem;
    }

    public void setImagem(String imagem) {
        this.imagem = imagem;
    }

    public String getAutor() {
        return autor;
    }

    public void setAutor(String autor) {
        this.autor = autor;
    }
}