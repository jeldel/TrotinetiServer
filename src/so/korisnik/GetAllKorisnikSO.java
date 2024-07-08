package so.korisnik;

import domain.Korisnik;
import repository.db.DBRepository;
import repository.db.impl.KorisnikRepository;
import so.AbstractSO;

import java.util.List;

public class GetAllKorisnikSO extends AbstractSO {

    private List<Korisnik> korisnici;
    private final DBRepository storageKorisnik;

    public GetAllKorisnikSO() {
        storageKorisnik = new KorisnikRepository();
    }

    @Override
    protected void precondition(Object param) throws Exception {
        if(!(param instanceof Korisnik)){
            throw new Exception("Parametar nije validan");
        }

    }

    @Override
    protected void executeOperation(Object param) throws Exception {
        List<Korisnik> lista = storageKorisnik.getAll();
        korisnici = lista;
    }

    public List<Korisnik> getKorisnici() {
        return korisnici;
    }
}
