package repository.db.impl;

import domain.Osoba;
import repository.db.DBBroker;
import repository.db.DBRepository;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class OsobaRepository implements DBRepository <Osoba, Long> {
    Connection connection;

    public OsobaRepository() {
    }

    public void add(Osoba osoba) {
        try {
            String query = "INSERT INTO osoba (brojLicneKarte, ime, prezime) VALUES (?,?,?)";
            System.out.println(query);
            connection = DBBroker.getInstance().getConnection();

            PreparedStatement statement = connection.prepareStatement(query);
            statement.setLong(1, osoba.getBrojLicneKarte());
            statement.setString(2, osoba.getIme());
            statement.setString(3, osoba.getPrezime());

            statement.executeUpdate();
            statement.close();
            System.out.println("Uspesno kreirana osoba");
        } catch (SQLException e) {
            System.out.println("Neuspesno kreirana osoba");
            throw new RuntimeException(e);
        }
    }

    @Override
    public void update(Osoba param) throws Exception {
        throw new Exception("Nije jos implementirano");
    }

    public List<Osoba> getAll() {
        try {
            List<Osoba> osobe = new ArrayList<>();
            String query = "SELECT brojLicneKarte, ime, prezime FROM osoba";
            System.out.println(query);
            connection = DBBroker.getInstance().getConnection();
            Statement statement = connection.createStatement();
            ResultSet rs = statement.executeQuery(query);
            while (rs.next()) {
                Osoba a = new Osoba();
                a.setBrojLicneKarte(rs.getLong("brojLicneKarte"));
                a.setIme(rs.getString("ime"));
                a.setPrezime(rs.getString("prezime"));
                osobe.add(a);
            }
            rs.close();
            statement.close();
            System.out.println("Uspesno ucitana lista osoba");
            return osobe;
        } catch (SQLException e) {
            System.out.println("Neuspesno ucitana lista osoba");
            throw new RuntimeException(e);
        }
    }

    public List<Osoba> getAllByCriteria(Long brojLicneKarte) {
        try {
            List<Osoba> osobe = new ArrayList<>();
            String query = "SELECT brojLicneKarte, ime, prezime FROM osoba WHERE brojLicneKarte = " + brojLicneKarte;
            System.out.println(query);
            connection = DBBroker.getInstance().getConnection();
            Statement statement = connection.createStatement();
            ResultSet rs = statement.executeQuery(query);
            while (rs.next()) {
                Osoba o = new Osoba();
                o.setBrojLicneKarte(rs.getLong("brojLicneKarte"));
                o.setIme(rs.getString("ime"));
                o.setPrezime(rs.getString("prezime"));
                osobe.add(o);
            }
            rs.close();
            statement.close();
            System.out.println("Uspesno ucitana osoba po broju licne karte");
            return osobe;
        } catch (SQLException e) {
            System.out.println("Neuspesno ucitana osoba po broju licne karte");
            throw new RuntimeException(e);
        }

    }

    public void delete(Long brojLicneKarte) {
        try {
            String query = "DELETE FROM osoba WHERE brojLicneKarte = ? ";
            System.out.println(query);
            connection = DBBroker.getInstance().getConnection();

            PreparedStatement statement = connection.prepareStatement(query);
            statement.setLong(1, brojLicneKarte);
            statement.executeUpdate();
            statement.close();
            System.out.println("Uspesno brisanje osoba");
        } catch (SQLException e) {
            System.out.println("Neuspesno brisanje osobe");
            throw new RuntimeException(e);
        }
    }
}
