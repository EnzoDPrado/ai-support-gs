package infra;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Objects;

public class DatabaseConnection {
    private static final String URL = "jdbc:oracle:thin:@oracle.fiap.com.br:1521:orcl";
    private static final String USUARIO = "your_user";
    private static final String SENHA = "your_password";
    private static Connection connection = null;


    public static Connection getConnection() throws SQLException {
        if (Objects.isNull(connection))
            connection = DriverManager.getConnection(URL, USUARIO, SENHA);


        if(connection.isClosed())
            connection = DriverManager.getConnection(URL, USUARIO, SENHA);


        return connection;
    }

}