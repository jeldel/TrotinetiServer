package repository.db.impl;

import controller.Controller;
import domain.GradEnum;
import domain.Korisnik;
import domain.TipKorisnika;
import repository.db.DBConnectionFactory;
import repository.db.DBRepository;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class KorisnikRepository implements DBRepository<Korisnik, String> {

    Connection connection;

    public KorisnikRepository() {
    }

    public List<Korisnik> getAll() {
        try {
            List<Korisnik> korisnici = new ArrayList<>();
            String query = "SELECT korisnikID, brojLicneKarte, ime, prezime, email, grad, telefon, username, sifra, tipKorisnika FROM korisnik";
            System.out.println(query);
            connection = DBConnectionFactory.getInstance().getConnection();
            Statement statement = connection.createStatement();
            ResultSet rs = statement.executeQuery(query);
            while (rs.next()) {
                Korisnik k = new Korisnik();
                k.setkorisnikID(rs.getLong("korisnikID"));
                k.setBrojLicneKarte(rs.getLong("brojLicneKarte"));
                k.setIme(rs.getString("ime"));
                k.setPrezime(rs.getString("prezime"));
                k.setEmail(rs.getString("email"));
                k.setGrad(GradEnum.valueOf(rs.getString("grad")));
                k.setTelefon(rs.getString("telefon"));
                k.setUsername(rs.getString("username"));
                k.setSifra(rs.getString("sifra"));
                k.setTipKorisnika(TipKorisnika.valueOf(rs.getString("tipKorisnika")));
                korisnici.add(k);
            }
            rs.close();
            statement.close();
            System.out.println("Uspesno ucitana lista korisnika");
            return korisnici;
        } catch (SQLException e) {
            System.out.println("Neuspesno ucitana lista korisnika");
            throw new RuntimeException(e);
        }

    }

    public List<Korisnik> getAllByCriteria(String username) {
        try {
            List<Korisnik> korisnici = new ArrayList<>();
            String query = "SELECT korisnikID, brojLicneKarte, ime, prezime, email, grad, telefon, username, sifra, tipKorisnika FROM korisnik WHERE username = '" + username + "'";
            System.out.println(query);
            connection = DBConnectionFactory.getInstance().getConnection();
            Statement statement = connection.createStatement();
            ResultSet rs = statement.executeQuery(query);
            while (rs.next()) {
                Korisnik k = new Korisnik();
                k.setkorisnikID(rs.getLong("korisnikID"));
                k.setBrojLicneKarte(rs.getLong("brojLicneKarte"));
                k.setIme(rs.getString("ime"));
                k.setPrezime(rs.getString("prezime"));
                k.setEmail(rs.getString("email"));
                k.setGrad(GradEnum.valueOf(rs.getString("grad")));
                k.setTelefon(rs.getString("telefon"));
                k.setUsername(rs.getString("username"));
                k.setSifra(rs.getString("sifra"));
                k.setTipKorisnika(TipKorisnika.valueOf(rs.getString("tipKorisnika")));
                korisnici.add(k);
            }
            rs.close();
            statement.close();
            System.out.println("Uspesno ucitana lista korisnika po zadatom kriterijumu");
            return korisnici;
        } catch (SQLException e) {
            System.out.println("Neuspesno ucitana lista korisnika po zadatom kriterijumu");
            throw new RuntimeException(e);
        }

    }

    public void add(Korisnik korisnik) {
        try {
            String query = "INSERT INTO korisnik (brojLicneKarte, ime, prezime, email, grad, telefon, username, sifra, tipKorisnika) VALUES (?,?,?,?,?,?,?,?,?)";
            System.out.println(query);
            connection = DBConnectionFactory.getInstance().getConnection();

            PreparedStatement statement = connection.prepareStatement(query, Statement.RETURN_GENERATED_KEYS);
            statement.setLong(1, korisnik.getBrojLicneKarte());
            statement.setString(2, korisnik.getIme());
            statement.setString(3, korisnik.getPrezime());
            statement.setString(4, korisnik.getEmail());
            statement.setString(5, String.valueOf(korisnik.getGrad()));
            statement.setString(6, korisnik.getTelefon());
            statement.setString(7, korisnik.getUsername());
            statement.setString(8, korisnik.getSifra());
            statement.setString(9, String.valueOf(korisnik.getTipKorisnika()));

            statement.executeUpdate();
            ResultSet rs = statement.getGeneratedKeys();
            if (rs.next()) {
                korisnik.setkorisnikID(rs.getLong(1));
            }

            rs.close();
            statement.close();
            System.out.println("Uspesno kreiran korisnik");
        } catch (SQLException e) {
            System.out.println("Neuspesno kreiran korisnik");
            throw new RuntimeException(e);
        }
    }

    public void delete(String username) {
        try {
            String query = "DELETE FROM korisnik WHERE username = ? ";
            System.out.println(query);
            connection = DBConnectionFactory.getInstance().getConnection();

            PreparedStatement statement = connection.prepareStatement(query);
            statement.setString(1, username);
            statement.executeUpdate();
            statement.close();
            System.out.println("Uspesno brisanje korisnika");
        } catch (SQLException e) {
            System.out.println("Neuspesno brisanje korisnika");
            throw new RuntimeException(e);
        }
    }

    public void update(Korisnik korisnik) {
        try {
            String query = "UPDATE korisnik SET brojLicneKarte = " + korisnik.getBrojLicneKarte() +
                    ", ime = '" + korisnik.getIme() + "' , prezime = '" + korisnik.getPrezime() +
                    "' , email  = '" + korisnik.getEmail() + "' , grad  = '" + korisnik.getGrad() +
                    "' , telefon = '" + korisnik.getTelefon() + "', sifra = '" + korisnik.getSifra() +
                    "'  , tipKorisnika  = '" + korisnik.getTipKorisnika() + "' WHERE username = '" + korisnik.getUsername() +"'";
            System.out.println(query);
            connection = DBConnectionFactory.getInstance().getConnection();

            Statement statement = connection.createStatement();
            statement.executeUpdate(query);
            statement.close();
            System.out.println("Uspesno azuriranje korisnika");
        } catch (SQLException e) {
            System.out.println("Neuspesno azuriranje korisnika");
            throw new RuntimeException(e);
        }
    }
}
