package br.com.alura.jdbc;

import br.com.alura.jdbc.dao.CategoriaDAO;
import br.com.alura.jdbc.modelo.Categoria;
import br.com.alura.jdbc.modelo.Produto;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

public class TestaListagemDeCategorias {
    public static void main(String[] args) throws SQLException {
        try (Connection connection = new ConnectionFactory().recuperarConexao()) {
            List<Categoria> categoriaList = new CategoriaDAO(connection).listarComProdutos();

            categoriaList.forEach(
                    categoria -> {
                        System.out.println(categoria);
                        for (Produto produto : categoria.getProdutos()) {
                            System.out.println(categoria.getNome() + " - " + produto.getNome());
                        }
                    });
        }
    }
}

