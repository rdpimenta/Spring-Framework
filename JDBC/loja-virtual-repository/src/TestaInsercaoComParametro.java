import java.sql.*;

public class TestaInsercaoComParametro {
    public static void main(String[] args) throws SQLException {
        String nome = "Mouse";
        String descricao = "Mouse sem fio";

        ConnectionFactory connectionFactory = new ConnectionFactory();
        Connection connection = connectionFactory.recuperarConexao();

        PreparedStatement statement = connection.prepareStatement("INSERT INTO PRODUTO (nome, descricao) VALUES (?, ?)", Statement.RETURN_GENERATED_KEYS);

        statement.setString(1, nome);
        statement.setString(2, descricao);

        statement.execute();

        ResultSet resultSet = statement.getGeneratedKeys();
        while (resultSet.next()) {
            int id = resultSet.getInt(1);
            System.out.println("O id criado foi: " + id);
        }

        connection.close();
    }
}
