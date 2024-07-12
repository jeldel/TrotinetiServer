package so.korisnik;

import domain.AbstractDomainObject;
import domain.Korisnik;
import repository.db.DBBroker;
import so.AbstractSO;

public class UpdateKorisnikSO extends AbstractSO {

    @Override
    protected void precondition(AbstractDomainObject ado) throws Exception {
        if(ado == null || !(ado instanceof Korisnik)){
            throw new Exception("Parametar nije validan");
        }

        Korisnik korisnik = (Korisnik) ado;
    }

    @Override
    protected void executeOperation(AbstractDomainObject ado) throws Exception {
        DBBroker.getInstance().update(ado);
    }
}
