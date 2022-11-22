package br.com.alura.jdbc;

import br.com.alura.jdbc.dao.ProdutoDAO;
import br.com.alura.jdbc.modelo.Produto;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

public class TestaInsercaoComProduto {
    public static void main(String[] args) throws SQLException {
        Produto comoda = new Produto("Cômoda", "Cômoda vertical");

        try (Connection connection = new ConnectionFactory().recuperarConexao()) {
            ProdutoDAO produtoDAO = new ProdutoDAO(connection);
            produtoDAO.salvar(comoda);

            List<Produto> produtos = produtoDAO.listar();

            produtos.forEach(System.out::println);
        }
    }
}
