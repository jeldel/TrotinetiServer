package so.korisnik;

import domain.Korisnik;
import repository.db.DBRepository;
import repository.db.impl.KorisnikRepository;
import so.AbstractSO;

public class UpdateKorisnikSO extends AbstractSO {
    private final DBRepository storageKorisnik;

    public UpdateKorisnikSO() {
        storageKorisnik = new KorisnikRepository();
    }

    @Override
    protected void precondition(Object param) throws Exception {
        if(param == null || !(param instanceof Korisnik)){
            throw new Exception("Parametar nije validan");
        }

        Korisnik korisnik = (Korisnik) param;
    }

    @Override
    protected void executeOperation(Object param) throws Exception {
        storageKorisnik.update(param);
    }
}
