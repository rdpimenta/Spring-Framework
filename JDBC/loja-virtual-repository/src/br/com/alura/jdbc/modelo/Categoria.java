package br.com.alura.jdbc.modelo;

import java.util.ArrayList;
import java.util.List;

public class Categoria {
    private int id;
    private String nome;
    private List<Produto> produtos = new ArrayList<>();

    public Categoria(int id, String nome) {
        this.id = id;
        this.nome = nome;
    }

    public String getNome() {
        return nome;
    }

    public int getId() {
        return id;
    }

    public List<Produto> getProdutos() {
        return produtos;
    }

    public void setId(int id) {
        this.id = id;
    }

    @Override
    public String toString() {
        return "A categoria eh: " + this.nome;
    }

    public void adicionar(Produto produto) {
        produtos.add(produto);
    }
}
