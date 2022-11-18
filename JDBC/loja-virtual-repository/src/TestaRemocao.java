import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;

public class TestaRemocao {
    public static void main(String[] args) throws SQLException {
        ConnectionFactory connectionFactory = new ConnectionFactory();
        Connection connection = connectionFactory.recuperarConexao();

        Statement statement = connection.createStatement();

        statement.execute("DELETE FROM PRODUTO WHERE ID > 1");

        int linhasModificadas = statement.getUpdateCount();

        System.out.println("Quantidade de linhas modificadas: " + linhasModificadas);

        connection.close();
    }
}
