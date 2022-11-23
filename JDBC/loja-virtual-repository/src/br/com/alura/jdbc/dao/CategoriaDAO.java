package br.com.alura.jdbc.dao;

import br.com.alura.jdbc.modelo.Categoria;
import br.com.alura.jdbc.modelo.Produto;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class CategoriaDAO {
    private Connection connection;

    public CategoriaDAO(Connection connection) {
        this.connection = connection;
    }

    public List<Categoria> listar() throws SQLException {
        String sql = "SELECT ID, NOME FROM CATEGORIA";

        List<Categoria> categoriaList = new ArrayList<>();

        try (PreparedStatement preparedStatement = connection.prepareStatement(sql)) {
            preparedStatement.execute();

            try (ResultSet resultSet = preparedStatement.getResultSet()) {
                while (resultSet.next()) {
                    int id = resultSet.getInt("ID");
                    String nome = resultSet.getString("NOME");

                    Categoria categoria = new Categoria(id, nome);
                    categoriaList.add(categoria);
                }
            }
        }

        return categoriaList;
    }

    public List<Produto> buscar(Categoria categoria) throws SQLException {
        String sql = "SELECT ID, NOME, DESCRICAO FROM PRODUTO WHERE CATEGORIA_ID = ?";

        List<Produto> produtos = new ArrayList<>();

        try (PreparedStatement preparedStatement = connection.prepareStatement(sql)) {
            preparedStatement.setInt(1, categoria.getId());
            preparedStatement.execute();

            try (ResultSet resultSet = preparedStatement.getResultSet()) {
                while (resultSet.next()) {
                    int id = resultSet.getInt("ID");
                    String nome = resultSet.getString("NOME");
                    String descricao = resultSet.getString("DESCRICAO");

                    Produto produto = new Produto(id, nome, descricao);
                    produtos.add(produto);
                }
            }
        }

        return produtos;
    }

    public List<Categoria> listarComProdutos() throws SQLException {
        Categoria ultima = null;
        String sql = "SELECT C.ID, C.NOME, P.ID, P.NOME, P.DESCRICAO " +
                "FROM CATEGORIA C " +
                "INNER JOIN PRODUTO P ON C.ID = P.CATEGORIA_ID";

        List<Categoria> categoriaList = new ArrayList<>();

        try (PreparedStatement preparedStatement = connection.prepareStatement(sql)) {
            preparedStatement.execute();

            try (ResultSet resultSet = preparedStatement.getResultSet()) {
                while (resultSet.next()) {
                    int id = resultSet.getInt(1);
                    String nome = resultSet.getString(2);

                    if (ultima == null || !ultima.getNome().equals(nome)) {
                        Categoria categoria = new Categoria(id, nome);
                        ultima = categoria;
                        categoriaList.add(categoria);
                    }
                    Produto produto = new Produto(resultSet.getInt(3), resultSet.getString(4), resultSet.getString(5));
                    ultima.adicionar(produto);
                }
            }
        }

        return categoriaList;
    }
}
