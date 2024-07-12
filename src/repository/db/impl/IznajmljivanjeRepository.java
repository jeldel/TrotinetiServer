package repository.db.impl;

import domain.*;
import repository.db.DBConnectionFactory;
import repository.db.DBRepository;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class IznajmljivanjeRepository implements DBRepository<IznajmljivanjeTrotineta, String> {
    Connection connection;

    public IznajmljivanjeRepository() {
    }

    public List<IznajmljivanjeTrotineta> getAll() {
        try {
            List<IznajmljivanjeTrotineta> iznajmljivanjeTrotinetaList = new ArrayList<>();
            String query = "SELECT it.iznajmljivanjeID," +
                    " it.datumVreme," +
                    " it.brojSati," +
                    " it.ukupnaCena," +
                    " it.korisnikID," +
                    " it.trotinetID," +
                    " it.osobaBrojLK, " +
                    " t.trotinetID," +
                    " t.vrstaTrotineta," +
                    " t.model," +
                    " k.korisnikID," +
                    " k.ime," +
                    " k.prezime," +
                    " k.email, " +
                    " k.grad," +
                    " k.telefon," +
                    " k.username," +
                    " k.sifra, " +
                    " o.brojLicneKarte, "+
                    " o.ime, " +
                    " o.prezime " +
                    "FROM iznajmljivanjeTrotineta it INNER JOIN trotinet t ON it.trotinetID = t.trotinetID " +
                    "INNER JOIN korisnik k ON it.korisnikID = k.korisnikID INNER JOIN osoba o ON it.osobaBrojLK = o.brojLicneKarte";
            System.out.println(query);
            connection = DBConnectionFactory.getInstance().getConnection();
            Statement statement = connection.createStatement();
            ResultSet rs = statement.executeQuery(query);

            while (rs.next()) {
                IznajmljivanjeTrotineta it = new IznajmljivanjeTrotineta();
                it.setIznajmljivanjeID(rs.getLong("it.iznajmljivanjeID"));
                it.setDatumVreme(rs.getDate("it.datumVreme"));
                it.setBrojSati(rs.getDouble("it.brojSati"));
                it.setUkupnaCena(rs.getDouble("it.ukupnaCena"));

                Trotinet t = new Trotinet();
                t.setTrotinetID(rs.getLong("t.trotinetID"));
                t.setVrstaTrotineta(VrstaTrotinetaEnum.valueOf(rs.getString("t.vrstaTrotineta")));
                t.setModel(rs.getString("t.model"));
                it.setTrotinet(t);

                Korisnik k = new Korisnik();
                k.setkorisnikID(rs.getLong("k.korisnikID"));
                k.setIme(rs.getString("k.ime"));
                k.setPrezime(rs.getString("k.prezime"));
                k.setEmail(rs.getString("k.email"));
                k.setGrad(GradEnum.valueOf(rs.getString("k.grad")));
                k.setTelefon(rs.getString("k.telefon"));
                k.setUsername(rs.getString("k.username"));
                k.setSifra(rs.getString("k.sifra"));
                it.setKorisnik(k);

                Osoba o = new Osoba();
                o.setBrojLicneKarte(rs.getLong("o.brojLicneKarte"));
                o.setIme(rs.getString("o.ime"));
                o.setPrezime(rs.getString("o.prezime"));
                it.setOsoba(o);

                iznajmljivanjeTrotinetaList.add(it);
            }
            rs.close();
            statement.close();
            System.out.println("Uspesno ucitana lista voznji");
            return iznajmljivanjeTrotinetaList;
        } catch (SQLException e) {
            System.out.println("Neuspesno ucitana lista voznji");
            throw new RuntimeException(e);
        }
    }

    public List<IznajmljivanjeTrotineta> getAllByCriteria(String username) {
        try {
            List<IznajmljivanjeTrotineta> iznajmljivanjeTrotinetaList = new ArrayList<>();
            String query = "SELECT it.iznajmljivanjeID," +
                    " it.datumVreme," +
                    " it.brojSati," +
                    " it.ukupnaCena," +
                    " it.korisnikID," +
                    " it.trotinetID," +
                    " it.osobaBrojLK, " +
                    " t.trotinetID," +
                    " t.vrstaTrotineta," +
                    " t.model," +
                    " k.korisnikID," +
                    " k.ime," +
                    " k.prezime," +
                    " k.email, " +
                    " k.grad," +
                    " k.telefon," +
                    " k.username," +
                    " k.sifra, " +
                    " o.brojLicneKarte, "+
                    " o.ime, " +
                    " o.prezime " +
                    "FROM iznajmljivanjeTrotineta it INNER JOIN trotinet t ON it.trotinetID = t.trotinetID " +
                    "INNER JOIN korisnik k ON it.korisnikID = k.korisnikID INNER JOIN osoba o ON it.osobaBrojLK = o.brojLicneKarte WHERE k.username = '" + username + "'";
            System.out.println(query);
            connection = DBConnectionFactory.getInstance().getConnection();
            Statement statement = connection.createStatement();
            ResultSet rs = statement.executeQuery(query);

            while (rs.next()) {
                IznajmljivanjeTrotineta it = new IznajmljivanjeTrotineta();
                it.setIznajmljivanjeID(rs.getLong("it.iznajmljivanjeID"));
                it.setDatumVreme(rs.getDate("it.datumVreme"));
                it.setBrojSati(rs.getDouble("it.brojSati"));
                it.setUkupnaCena(rs.getDouble("it.ukupnaCena"));

                Trotinet t = new Trotinet();
                t.setTrotinetID(rs.getLong("t.trotinetID"));
                t.setVrstaTrotineta(VrstaTrotinetaEnum.valueOf(rs.getString("t.vrstaTrotineta")));
                t.setModel(rs.getString("t.model"));
                it.setTrotinet(t);

                Korisnik k = new Korisnik();
                k.setkorisnikID(rs.getLong("k.korisnikID"));
                k.setIme(rs.getString("k.ime"));
                k.setPrezime(rs.getString("k.prezime"));
                k.setEmail(rs.getString("k.email"));
                k.setGrad(GradEnum.valueOf(rs.getString("k.grad")));
                k.setTelefon(rs.getString("k.telefon"));
                k.setUsername(rs.getString("k.username"));
                k.setSifra(rs.getString("k.sifra"));
                it.setKorisnik(k);

                Osoba o = new Osoba();
                o.setBrojLicneKarte(rs.getLong("o.brojLicneKarte"));
                o.setIme(rs.getString("o.ime"));
                o.setPrezime(rs.getString("o.prezime"));
                it.setOsoba(o);

                iznajmljivanjeTrotinetaList.add(it);
            }
            rs.close();
            statement.close();
            System.out.println("Uspesno ucitana lista voznji");
            return iznajmljivanjeTrotinetaList;
        } catch (SQLException e) {
            System.out.println("Neuspesno ucitana lista voznji");
            throw new RuntimeException(e);
        }
    }

    public void add(IznajmljivanjeTrotineta iznajmljivanjeTrotineta) {
        Date sqlDate = new Date(iznajmljivanjeTrotineta.getDatumVreme().getTime());
        try {
            String query = "INSERT INTO iznajmljivanjeTrotineta (datumVreme, brojSati, ukupnaCena, korisnikID, trotinetID, osobaBrojLK)  VALUES (?,?,?,?,?,?)";
            System.out.println(query);
            connection = DBConnectionFactory.getInstance().getConnection();

            PreparedStatement statement = connection.prepareStatement(query, Statement.RETURN_GENERATED_KEYS);
            statement.setDate(1, sqlDate);
            statement.setDouble(2, iznajmljivanjeTrotineta.getBrojSati());
            statement.setDouble(3, iznajmljivanjeTrotineta.getUkupnaCena());
            statement.setLong(4, iznajmljivanjeTrotineta.getKorisnik().getkorisnikID());
            statement.setLong(5, iznajmljivanjeTrotineta.getTrotinet().getTrotinetID());
            statement.setLong(6, iznajmljivanjeTrotineta.getOsoba().getBrojLicneKarte());

            statement.executeUpdate();
            ResultSet rs = statement.getGeneratedKeys();

            if (rs.next()) {
                iznajmljivanjeTrotineta.setIznajmljivanjeID(rs.getLong(1));
            }
            rs.close();
            statement.close();
            System.out.println("Uspesno kreirana voznja");
        } catch (SQLException e) {
            System.out.println("Neuspesno kreirana voznja");
            throw new RuntimeException(e);
        }
    }

    @Override
    public void update(IznajmljivanjeTrotineta param) throws Exception {
        throw new UnsupportedOperationException("No implementation yet.");
    }

    @Override
    public void delete(String criteria) throws Exception {
        throw new UnsupportedOperationException("No implementation yet.");
    }

    public void addAll(List<IznajmljivanjeTrotineta> voznje) {

        try {
            String query = "INSERT INTO iznajmljivanjeTrotineta (datumVreme, brojSati, ukupnaCena, korisnikID, trotinetID, osobaBrojLK)  VALUES (?,?,?,?,?,?)";
            System.out.println(query);
            connection = DBConnectionFactory.getInstance().getConnection();

            PreparedStatement statement = connection.prepareStatement(query, Statement.RETURN_GENERATED_KEYS);

            for (IznajmljivanjeTrotineta iznajmljivanjeTrotineta : voznje) {
                Date sqlDate = new Date(iznajmljivanjeTrotineta.getDatumVreme().getTime());
                statement.setDate(1, sqlDate);
                statement.setDouble(2, iznajmljivanjeTrotineta.getBrojSati());
                statement.setDouble(3, iznajmljivanjeTrotineta.getUkupnaCena());
                statement.setLong(4, iznajmljivanjeTrotineta.getKorisnik().getkorisnikID());
                statement.setLong(5, iznajmljivanjeTrotineta.getTrotinet().getTrotinetID());
                statement.setLong(6, iznajmljivanjeTrotineta.getOsoba().getBrojLicneKarte());

                statement.executeUpdate();
                System.out.println("Uspesno kreirana voznja");
            }

            statement.close();
        } catch (SQLException e) {
            System.out.println("Neuspesno kreirana voznja");
            throw new RuntimeException(e);
        }
    }

}
