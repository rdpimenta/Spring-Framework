package br.com.alura.jdbc.modelo;

public class Produto {
    private Integer id;
    private String nome;
    private String descricao;

    public Produto(String nome, String descricao) {
        this.nome = nome;
        this.descricao = descricao;
    }

    public Produto(int id, String nome, String descricao) {
        this.id = id;
        this.nome = nome;
        this.descricao = descricao;
    }

    public String getNome() {
        return this.nome;
    }

    public String getDescricao() {
        return this.descricao;
    }

    public void setId(int id) {
        this.id = id;
    }

    @Override
    public String toString() {
        return String.format("O produto eh: %d, %s, %s", this.id, this.nome, this.descricao);
    }
}
