package DAO;

import java.io.FileInputStream;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;

/**
 *
 * @author Mauricio
 */
public class Conexao {

    private static Connection instance;
    private static final String URL;
    private static final String USER;
    private static final String PASSWORD;

    static {
        try {
            Properties props = new Properties();
            props.load(Conexao.class.getClassLoader().getResourceAsStream("config/db.properties"));

            URL = props.getProperty("DB_URL");
            USER = props.getProperty("DB_USER");
            PASSWORD = props.getProperty("DB_PASSWORD");
        } catch (IOException e) {
            throw new RuntimeException("Erro ao carregar arquivo db.properties", e);
        }
    }

    // Construtor privado -> ninguém de fora instancia
    private Conexao() {
    }

    public static Connection getInstance() throws SQLException {
        if (instance == null || instance.isClosed()) {
            try {
                // Registrar o driver (não é mais necessário em versões mais novas do JDBC, mas seguro)
                Class.forName("com.mysql.cj.jdbc.Driver");

                instance = DriverManager.getConnection(URL, USER, PASSWORD);
                System.out.println("Conexão com banco OK!");
            } catch (ClassNotFoundException e) {
                throw new SQLException("Driver JDBC não encontrado!", e);
            }
        }
        return instance;
    }

    public static void close() {
        try {
            if (instance != null && !instance.isClosed()) {
                instance.close();
                System.out.println("Conexão com banco fechada.");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

}
