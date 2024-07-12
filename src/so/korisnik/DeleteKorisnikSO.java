package so.korisnik;

import domain.AbstractDomainObject;
import domain.Korisnik;
import repository.db.DBBroker;
import so.AbstractSO;

public class DeleteKorisnikSO extends AbstractSO {

    @Override
    protected void precondition(AbstractDomainObject ado) throws Exception {
        if(!(ado instanceof Korisnik)){
            throw new Exception("Parametar nije validan");
        }

    }

    @Override
    protected void executeOperation(AbstractDomainObject ado) throws Exception {
        DBBroker.getInstance().delete(ado);
    }
}
