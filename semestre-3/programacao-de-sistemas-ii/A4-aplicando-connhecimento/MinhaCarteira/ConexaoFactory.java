import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class ConexaoFactory {
    // Configurações do Banco de Dados no Docker
    private static final String URL = "jdbc:mariadb://127.0.0.1:3306/minhacarteira";
    private static final String USER = "root";
    private static final String PASS = "mackenzie";

    public static Connection conectar() throws SQLException {
        try {
            // Tenta carregar o Driver que você adicionou nas Referenced Libraries
            Class.forName("org.mariadb.jdbc.Driver");
            return DriverManager.getConnection(URL, USER, PASS);
        } catch (ClassNotFoundException e) {
            throw new SQLException("Driver MariaDB não encontrado nas bibliotecas.");
        }
    }
}