package controller;

import domain.*;
import repository.db.impl.IznajmljivanjeRepository;
import repository.db.impl.KorisnikRepository;
import repository.db.impl.OsobaRepository;
import repository.db.impl.TrotinetRepository;
import so.AbstractSO;
import so.korisnik.AddKorisnikSO;
import so.korisnik.DeleteKorisnikSO;
import so.korisnik.GetAllKorisnikSO;
import so.korisnik.UpdateKorisnikSO;
import so.login.LoginSO;
import so.osoba.AddOsobaSO;
import so.osoba.GetAllOsobaSO;
import so.trotinet.AddTrotinetSO;
import so.trotinet.DeleteTrotinetSO;
import so.trotinet.GetAllTrotinetSO;
import so.trotinet.UpdateTrotinetSO;
import so.voznja.AddVoznjaSO;
import so.voznja.GetAllVoznjaSO;

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
        AbstractSO addTrotinetSO = new AddTrotinetSO();
        addTrotinetSO.execute(trotinet);
    }

    public void addOsoba(Osoba osoba) throws Exception {
        AbstractSO addOsobaSO = new AddOsobaSO();
        addOsobaSO.execute(osoba);
    }

    public void addKorisnik(Korisnik korisnik) throws Exception {
        AbstractSO addKorisnikSO = new AddKorisnikSO();
        addKorisnikSO.execute(korisnik);
    }

    public void addVoznja(IznajmljivanjeTrotineta iznajmljivanjeTrotineta) throws Exception {
        AbstractSO addVoznjaSO = new AddVoznjaSO();
        addVoznjaSO.execute(iznajmljivanjeTrotineta);
    }

    //getAll
    public List<Trotinet> getAllTrotinet() throws Exception {
        GetAllTrotinetSO getAllTrotinetSO = new GetAllTrotinetSO();
        getAllTrotinetSO.execute(new Trotinet());
        return getAllTrotinetSO.getTrotineti();
    }

    public List<Osoba> getAllOsoba() throws Exception {
        GetAllOsobaSO getAllOsobaSO = new GetAllOsobaSO();
        getAllOsobaSO.execute(new Osoba());
        return getAllOsobaSO.getOsobe();
    }

    public List<IznajmljivanjeTrotineta> getAllVoznje() throws Exception {
        GetAllVoznjaSO getAllVoznjaSO = new GetAllVoznjaSO();
        getAllVoznjaSO.execute(new IznajmljivanjeTrotineta());
        return getAllVoznjaSO.getVoznje();
    }

    public List<Korisnik> getAllKorisnik() throws Exception {
        GetAllKorisnikSO getAllKorisnikSO = new GetAllKorisnikSO();
        getAllKorisnikSO.execute(new Korisnik());
        return getAllKorisnikSO.getKorisnici();
    }

    //login
    public Korisnik login(Korisnik korisnik) throws Exception {
        LoginSO loginSO = new LoginSO();
        loginSO.execute(korisnik);
        return loginSO.getUlogovani();
    }

    //add all voznje
    public void addAllVoznje(List<IznajmljivanjeTrotineta> voznje) throws Exception {
        for(IznajmljivanjeTrotineta it : voznje){
           addVoznja(it);
        }
    }

    //getByCriteria
    public Trotinet getTrotinetById(Long id) {
        return storageTrotinet.getById(id);
    }

    public List<IznajmljivanjeTrotineta> getAllByCriteria(String username) {
        return storageIznajmljivanje.getAllByCriteria(username);
    }

    public List<Trotinet> getAllByVrsta(VrstaTrotinetaEnum vrstaTrotinetaEnum) {
        return storageTrotinet.getAllByVrsta(vrstaTrotinetaEnum);
    }

    public List<Korisnik> getAllByUsername(String username) {
        return storageKorisnik.getAllByCriteria(username);
    }

    public List<Osoba> getAllByBrojLK(Long brojLicneKarte) {
        return storageOsoba.getAllByCriteria(brojLicneKarte);
    }

    //delete
    public void deleteTrotinet(Long trotinetID) throws Exception {
        Trotinet trotinet = Controller.getInstance().getTrotinetById(trotinetID);
        AbstractSO deleteTrotinetSO = new DeleteTrotinetSO();
        deleteTrotinetSO.execute(trotinet);
    }

    public void deleteKorisnik(String username) throws Exception {
        List<Korisnik> korisnici = Controller.getInstance().getAllByUsername(username);
        Korisnik korisnik = korisnici.get(0);
        AbstractSO deleteKorisnikSO = new DeleteKorisnikSO();
        deleteKorisnikSO.execute(korisnik);
    }

    //update
    public void updateKorisnik(Korisnik korisnik) throws Exception {
        AbstractSO updateKorisnikSO = new UpdateKorisnikSO();
        updateKorisnikSO.execute(korisnik);
    }

    public void updateTrotinet(Trotinet trotinet) throws Exception {
        AbstractSO updateTrotinetSO = new UpdateTrotinetSO();
        updateTrotinetSO.execute(trotinet);
    }



    public void setUlogovanKorisnik(Korisnik ulogovanKorisnik) {
        this.ulogovanKorisnik = ulogovanKorisnik;
    }

    public Korisnik getUlogovanKorisnik() {
        return ulogovanKorisnik;
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
