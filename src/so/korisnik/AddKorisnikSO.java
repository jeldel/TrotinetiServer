package so.korisnik;

import domain.Korisnik;
import repository.db.DBRepository;
import repository.db.impl.KorisnikRepository;
import so.AbstractSO;


public class AddKorisnikSO extends AbstractSO {
    private final DBRepository storageKorisnik;

    public AddKorisnikSO() {
       storageKorisnik = new KorisnikRepository();
    }

    @Override
    protected void precondition(Object param) throws Exception {
        if(!(param instanceof Korisnik)){
            throw new Exception("Parametar nije validan");
        }

        Korisnik korisnik = (Korisnik) param;
    }

    @Override
    protected void executeOperation(Object param) throws Exception {
        storageKorisnik.add((Korisnik) param);
    }

}
