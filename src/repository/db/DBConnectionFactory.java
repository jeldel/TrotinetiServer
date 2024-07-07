package repository.db;

import constants.MyServerConstant;

import java.io.FileInputStream;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;

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
                Properties properties = new Properties();
                properties.load(new FileInputStream(MyServerConstant.FILE_PATH));
                String url = properties.getProperty(MyServerConstant.DB_CONFIG_URL);
                String username = properties.getProperty(MyServerConstant.DB_CONFIG_USERNAME);
                String password = properties.getProperty(MyServerConstant.DB_CONFIG_PASSWORD);
                connection = DriverManager.getConnection(url, username, password);
                connection.setAutoCommit(false);
                System.out.println("Uspesna konekcija na bazu");
            }
        } catch (SQLException e) {
            System.out.println("Neuspesna konekcija na bazu");
            throw e;
        } catch(IOException exception){
            exception.printStackTrace();
        }
        return connection;
    }

}
