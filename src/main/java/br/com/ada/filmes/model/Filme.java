package br.com.ada.filmes.model;

public class Filme {

    private int id;
    private String titulo;
    private String genero;
    private int duracao;
    private String sinopse;
    private Double imdb;
    private String imagem;
    private int like;
    private boolean favorito;

    public Filme() {

    }

    public Filme(int id, String titulo, String genero, int duracao, String sinopse, Double imdb, String imagem, int like, boolean favorito) {
        this.id = id;
        this.titulo = titulo;
        this.genero = genero;
        this.duracao = duracao;
        this.sinopse = sinopse;
        this.imdb = imdb;
        this.imagem = imagem;
        this.like = like;
        this.favorito = favorito;
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

    public String getGenero() {
        return genero;
    }

    public void setGenero(String genero) {
        this.genero = genero;
    }

    public int getDuracao() {
        return duracao;
    }

    public void setDuracao(int duracao) {
        this.duracao = duracao;
    }

    public String getSinopse() {
        return sinopse;
    }

    public void setSinopse(String sinopse) {
        this.sinopse = sinopse;
    }

    public Double getImdb() {
        return imdb;
    }

    public void setImdb(Double imdb) {
        this.imdb = imdb;
    }

    public String getImagem() {
        return imagem;
    }

    public void setImagem(String imagem) {
        this.imagem = imagem;
    }

    public int getLike() {
        return like;
    }

    public void setLike(int like) {
        this.like = like;
    }

    public boolean isFavorito() {
        return favorito;
    }

    public void setFavorito(boolean favorito) {
        this.favorito = favorito;
    }
}
