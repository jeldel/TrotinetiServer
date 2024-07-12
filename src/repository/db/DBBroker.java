package repository.db;

import constants.MyServerConstant;

import java.io.FileInputStream;
import java.sql.*;
import java.util.ArrayList;
import java.util.Properties;

import domain.AbstractDomainObject;

public class DBBroker {
    private Connection connection;
    private static DBBroker instance;

    private DBBroker() {
        try {
            Properties properties = new Properties();
            properties.load(new FileInputStream(MyServerConstant.FILE_PATH));
            String url = properties.getProperty(MyServerConstant.DB_CONFIG_URL);
            String username = properties.getProperty(MyServerConstant.DB_CONFIG_USERNAME);
            String password = properties.getProperty(MyServerConstant.DB_CONFIG_PASSWORD);
            connection = DriverManager.getConnection(url, username, password);
            connection.setAutoCommit(false);
            System.out.println("Uspesna konekcija na bazu");
        } catch (Exception e) {
            System.out.println("Neuspesna konekcija na bazu");
            e.printStackTrace();
        }
    }

    public static DBBroker getInstance() {
        if (instance == null) {
            instance = new DBBroker();
        }
        return instance;
    }

    public Connection getConnection() throws SQLException {
        return connection;
    }

    public ArrayList<AbstractDomainObject> selectAll(AbstractDomainObject ado) throws SQLException {
        String upit = "SELECT * FROM " + ado.nazivTabele() + " " + ado.alijas()
                + " " + ado.join();
        System.out.println(upit);
        Statement s = connection.createStatement();
        ResultSet rs = s.executeQuery(upit);
        return ado.vratiListu(rs);
    }


    public ArrayList<AbstractDomainObject> selectWithCriteria(AbstractDomainObject ado) throws SQLException {
        String upit = "SELECT * FROM " + ado.nazivTabele() + " " + ado.alijas()
                + " " + ado.join() + " " + ado.uslovZaSelect();
        System.out.println(upit);
        Statement s = connection.createStatement();
        ResultSet rs = s.executeQuery(upit);
        return ado.vratiListu(rs);
    }

    public PreparedStatement insert(AbstractDomainObject ado) throws SQLException {
        String upit = "INSERT INTO " + ado.nazivTabele() + " "
                + ado.koloneZaInsert() + " VALUES(" + ado.vrednostiZaInsert() + ")";
        System.out.println(upit);
        PreparedStatement ps = connection.prepareStatement(upit, Statement.RETURN_GENERATED_KEYS);
        ps.executeUpdate();
        return ps;
    }

    public void update(AbstractDomainObject ado) throws SQLException {
        String upit = "UPDATE " + ado.nazivTabele() + " SET "
                + ado.vrednostiZaUpdate() + " WHERE " + ado.uslov();
        System.out.println(upit);
        Statement s = connection.createStatement();
        s.executeUpdate(upit);
    }

    public void delete(AbstractDomainObject ado) throws SQLException {
        String upit = "DELETE FROM " + ado.nazivTabele() + " WHERE " + ado.uslov();
        System.out.println(upit);
        Statement s = connection.createStatement();
        s.executeUpdate(upit);
    }

}
