package repository.db;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DBConnectionFactory {
    private Connection connection;

    private static DBConnectionFactory instance;

    private DBConnectionFactory(){

    }

    public static DBConnectionFactory getInstance(){
        if(instance == null){
            instance = new DBConnectionFactory();
        }
        return instance;
    }

    public Connection getConnection() throws SQLException {
        try {
            if (connection == null || connection.isClosed()) {
                String url = "jdbc:mysql://localhost:3306/trotineti";
                String username = "root";
                String password = "root123!";
                connection = DriverManager.getConnection(url, username, password);
                connection.setAutoCommit(false);
                System.out.println("Uspesna konekcija na bazu");
            }
        } catch (SQLException e) {
            System.out.println("Neuspesna konekcija na bazu");
            throw e;
        }
        return connection;
    }

}
