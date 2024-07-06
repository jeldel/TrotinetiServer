package repository.db.impl;

import domain.Trotinet;
import domain.VrstaTrotinetaEnum;
import repository.db.DBConnectionFactory;
import repository.db.DBRepository;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class TrotinetRepository implements DBRepository <Trotinet, Long> {
    Connection connection;

    public TrotinetRepository() {
    }

    public List<Trotinet> getAll() {
        try {
            List<Trotinet> trotineti = new ArrayList<>();
            String query = "SELECT trotinetID, vrstaTrotineta, model FROM trotinet";
            System.out.println(query);
            connection = DBConnectionFactory.getInstance().getConnection();
            Statement statement = connection.createStatement();
            ResultSet rs = statement.executeQuery(query);
            while (rs.next()) {
                Trotinet t = new Trotinet();
                t.setTrotinetID(rs.getLong("trotinetID"));
                t.setVrstaTrotineta(VrstaTrotinetaEnum.valueOf(rs.getString("vrstaTrotineta")));
                t.setModel(rs.getString("model"));
                trotineti.add(t);
            }
            rs.close();
            statement.close();
            System.out.println("Uspesno ucitana lista trotineta");
            return trotineti;
        } catch (SQLException e) {
            System.out.println("Neuspesno ucitana lista trotineta");
            throw new RuntimeException(e);
        }
    }

    @Override
    public List<Trotinet> getAllByCriteria(Long criteria) throws Exception {
        throw new UnsupportedOperationException("No implementation yet.");
    }

    public List<Trotinet> getAllByVrsta(VrstaTrotinetaEnum vrstaTrotinetaEnum) {
        try {
            List<Trotinet> trotineti = new ArrayList<>();
            String query = "SELECT trotinetID, vrstaTrotineta, model FROM trotinet WHERE vrstaTrotineta = '" + vrstaTrotinetaEnum + "'";
            System.out.println(query);
            connection = DBConnectionFactory.getInstance().getConnection();
            Statement statement = connection.createStatement();
            ResultSet rs = statement.executeQuery(query);
            while (rs.next()) {
                Trotinet t = new Trotinet();
                t.setTrotinetID(rs.getLong("trotinetID"));
                t.setVrstaTrotineta(VrstaTrotinetaEnum.valueOf(rs.getString("vrstaTrotineta")));
                t.setModel(rs.getString("model"));
                trotineti.add(t);
            }
            rs.close();
            statement.close();
            System.out.println("Uspesno ucitana lista trotineta po unesenom kriterijumu");
            return trotineti;
        } catch (SQLException e) {
            System.out.println("Neuspesno ucitana lista trotineta po unesenom kriterijumu");
            throw new RuntimeException(e);
        }

    }

    public Trotinet getById(Long id) {
        try {
            String query = "SELECT trotinetID, vrstaTrotineta, model FROM trotinet WHERE trotinetID = " + id;
            System.out.println(query);
            connection = DBConnectionFactory.getInstance().getConnection();
            Statement statement = connection.createStatement();
            ResultSet rs = statement.executeQuery(query);
            Trotinet t = new Trotinet();
            while (rs.next()) {
                t.setTrotinetID(rs.getLong("trotinetID"));
                t.setVrstaTrotineta(VrstaTrotinetaEnum.valueOf(rs.getString("vrstaTrotineta")));
                t.setModel(rs.getString("model"));
            }
            rs.close();
            statement.close();
            System.out.println("Uspesno ucitana lista trotineta po unesenom kriterijumu");
            return t;
        } catch (SQLException e) {
            System.out.println("Neuspesno ucitana lista trotineta po unesenom kriterijumu");
            throw new RuntimeException(e);
        }
    }

    public void add(Trotinet trotinet) {
        try {
            String query = "INSERT INTO trotinet (vrstaTrotineta, model) VALUES (?,?)";
            System.out.println(query);
            connection = DBConnectionFactory.getInstance().getConnection();

            PreparedStatement statement = connection.prepareStatement(query, Statement.RETURN_GENERATED_KEYS);
            statement.setString(1, trotinet.getVrstaTrotineta().name());
            statement.setString(2, trotinet.getModel());
            statement.executeUpdate();
            ResultSet rs = statement.getGeneratedKeys();
            if (rs.next()) {
                trotinet.setTrotinetID(rs.getLong(1));
            }
            rs.close();
            statement.close();
            System.out.println("Uspesno kreiran trotinet");
        } catch (SQLException e) {
            System.out.println("Neuspesno kreiran trotinet");
            throw new RuntimeException(e);
        }
    }

    public void delete(Long trotinetID) {
        try {
            String query = "DELETE FROM trotinet WHERE trotinetID = ?";
            System.out.println(query);
            connection = DBConnectionFactory.getInstance().getConnection();

            PreparedStatement statement = connection.prepareStatement(query);
            statement.setLong(1, trotinetID);
            statement.executeUpdate();
            statement.close();
            System.out.println("Uspesno brisanje trotineta");
        } catch (SQLException e) {
            System.out.println("Neuspesno brisanje trotineta");
            throw new RuntimeException(e);
        }
    }
}
