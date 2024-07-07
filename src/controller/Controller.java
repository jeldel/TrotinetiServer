package controller;

import domain.*;
import repository.db.impl.IznajmljivanjeRepository;
import repository.db.impl.KorisnikRepository;
import repository.db.impl.OsobaRepository;
import repository.db.impl.TrotinetRepository;
import so.AbstractSO;
import so.korisnik.AddKorisnikSO;

import java.sql.SQLException;
import java.util.List;

public class Controller {
    private static Controller instance;
    private final OsobaRepository storageOsoba;
    private final TrotinetRepository storageTrotinet;
    private final KorisnikRepository storageKorisnik;
    private final IznajmljivanjeRepository storageIznajmljivanje;
    private Korisnik ulogovanKorisnik;
    private Osoba izabranaOsoba;
    private Trotinet izabraniTrotinet;

    private Controller() {
        storageOsoba = new OsobaRepository();
        storageKorisnik = new KorisnikRepository();
        storageTrotinet = new TrotinetRepository();
        storageIznajmljivanje = new IznajmljivanjeRepository();
    }

    public static Controller getInstance() {
        if (instance == null) {
            instance = new Controller();
        }
        return instance;
    }

    //add
    public void addTrotinet(Trotinet trotinet) throws Exception {
        storageTrotinet.connect();
        try {
            storageTrotinet.add(trotinet);
            storageTrotinet.commit();
        } catch (SQLException e) {
            storageTrotinet.rollback();
            e.printStackTrace();
            throw e;
        } finally {
            storageTrotinet.disconnect();
        }
    }

    public void addOsoba(Osoba osoba) throws Exception {
        storageOsoba.connect();
        try {
            storageOsoba.add(osoba);
            storageOsoba.commit();
        } catch (SQLException e) {
            storageOsoba.rollback();
            e.printStackTrace();
            throw e;
        } finally {
            storageOsoba.disconnect();
        }
    }

    public void addKorisnik(Korisnik korisnik) throws Exception {
        AbstractSO addKorisnikSO = new AddKorisnikSO();
        addKorisnikSO.execute(korisnik);
        /*storageKorisnik.connect();
        try {
            storageKorisnik.add(korisnik);
            storageKorisnik.commit();
        } catch (SQLException e) {
            storageKorisnik.rollback();
            e.printStackTrace();
            throw e;
        } finally {
            storageKorisnik.disconnect();
        }*/
    }

    public void addVoznja(IznajmljivanjeTrotineta iznajmljivanjeTrotineta) throws Exception {
        storageIznajmljivanje.connect();
        try {
            storageIznajmljivanje.add(iznajmljivanjeTrotineta);
            storageIznajmljivanje.commit();
        } catch (SQLException e) {
            storageIznajmljivanje.rollback();
            e.printStackTrace();
            throw e;
        } finally {
            storageIznajmljivanje.disconnect();
        }
    }

    //getAll
    public List<Trotinet> getAllTrotinet() {
        return storageTrotinet.getAll();
    }

    public List<Osoba> getAllOsoba() {
        return storageOsoba.getAll();
    }

    public List<IznajmljivanjeTrotineta> getAllVoznje() {
        return storageIznajmljivanje.getAll();
    }

    public List<Korisnik> getAllKorisnik() {
        return storageKorisnik.getAll();
    }

    //login
    public Korisnik login(String username, String password) throws Exception {
        try {
            storageKorisnik.connect();
            List<Korisnik> korisnici = storageKorisnik.getAll();
            storageKorisnik.commit();
            for (Korisnik korisnik : korisnici) {
                if (korisnik.getUsername().equals(username) && korisnik.getSifra().equals(password)) {
                    ulogovanKorisnik = korisnik;
                    return korisnik;
                }
            }
            throw new Exception("Nepoznat korisnik");
        } catch (Exception e) {
            storageKorisnik.rollback();
            e.printStackTrace();
            throw e;
        } finally {
            storageKorisnik.disconnect();
        }
    }

    public Korisnik getUlogovanKorisnik() {
        return ulogovanKorisnik;
    }

    public void addAllVoznje(List<IznajmljivanjeTrotineta> voznje) throws SQLException {
        storageIznajmljivanje.connect();
        try {
            storageIznajmljivanje.addAll(voznje);
            storageIznajmljivanje.commit();
        } catch (SQLException e) {
            storageIznajmljivanje.rollback();
            e.printStackTrace();
            throw e;
        } finally {
            storageIznajmljivanje.disconnect();
        }
    }

    //getByID
    public Trotinet getTrotinetById(Long id) {
        return storageTrotinet.getById(id);
    }

    public List<IznajmljivanjeTrotineta> getAllByCriteria(String username) {
        return storageIznajmljivanje.getAllByCriteria(username);
    }

    public List<Trotinet> getAllByVrsta(VrstaTrotinetaEnum vrstaTrotinetaEnum) {
        return storageTrotinet.getAllByVrsta(vrstaTrotinetaEnum);
    }

    public void deleteTrotinet(Long trotinetID) throws Exception {
        storageTrotinet.connect();
        try {
            storageTrotinet.delete(trotinetID);
            storageTrotinet.commit();
        } catch (SQLException e) {
            storageTrotinet.rollback();
            e.printStackTrace();
            throw e;
        } finally {
            storageTrotinet.disconnect();
        }
    }

    public List<Korisnik> getAllByUsername(String username) {
        return storageKorisnik.getAllByCriteria(username);
    }

    public void deleteKorisnik(String username) throws SQLException {
        storageKorisnik.connect();
        try {
            storageKorisnik.delete(username);
            storageKorisnik.commit();
        } catch (SQLException e) {
            storageKorisnik.rollback();
            e.printStackTrace();
            throw e;
        } finally {
            storageKorisnik.disconnect();
        }
    }

    public List<Osoba> getByBrojLK(Long brojLicneKarte) {
        return storageOsoba.getAllByCriteria(brojLicneKarte);
    }

    public void setIzabranaOsoba(Osoba izabranaOsoba) {
        this.izabranaOsoba = izabranaOsoba;
    }

    public Osoba getIzabranaOsoba() {
        return izabranaOsoba;
    }

    public Trotinet getIzabraniTrotinet() {
        return izabraniTrotinet;
    }

    public void setIzabraniTrotinet(Trotinet izabraniTrotinet) {
        this.izabraniTrotinet = izabraniTrotinet;
    }
}
