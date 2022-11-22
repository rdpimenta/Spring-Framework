package br.com.alura.jdbc;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class TestaRemocao {
    public static void main(String[] args) throws SQLException {
        ConnectionFactory connectionFactory = new ConnectionFactory();
        Connection connection = connectionFactory.recuperarConexao();

        PreparedStatement statement = connection.prepareStatement("DELETE FROM PRODUTO WHERE ID > ?");
        statement.setInt(1, 1);

        statement.execute();

        int linhasModificadas = statement.getUpdateCount();

        System.out.println("Quantidade de linhas modificadas: " + linhasModificadas);

        connection.close();
    }
}
