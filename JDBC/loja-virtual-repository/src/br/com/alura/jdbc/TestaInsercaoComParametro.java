package br.com.alura.jdbc;

import java.sql.*;

public class TestaInsercaoComParametro {
    public static void main(String[] args) throws SQLException {
        ConnectionFactory connectionFactory = new ConnectionFactory();
        try (Connection connection = connectionFactory.recuperarConexao()) {
            connection.setAutoCommit(false);

            try (
                    PreparedStatement statement = connection.prepareStatement(
                            "INSERT INTO PRODUTO (nome, descricao) VALUES (?, ?)",
                            Statement.RETURN_GENERATED_KEYS
                    )
            ) {
                adicionarVariavel("Mouse", "Mouse sem fio", statement);
                adicionarVariavel("Radio", "Radio de bateria", statement);

                connection.commit();
            } catch (Exception e) {
                e.printStackTrace();
                System.out.println("ROLLBACK");
                connection.rollback();
            }
        }
    }

    private static void adicionarVariavel(String nome, String descricao, PreparedStatement statement) throws SQLException {
        statement.setString(1, nome);
        statement.setString(2, descricao);

        statement.execute();

//        if (Objects.equals(nome, "Radio")) {
//            throw new SQLException("Exceção executada");
//        }

        try (ResultSet resultSet = statement.getGeneratedKeys()) {
            while (resultSet.next()) {
                int id = resultSet.getInt(1);
                System.out.println("O id criado foi: " + id);
            }
        }
    }
}
