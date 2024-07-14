package so.osoba;

import domain.AbstractDomainObject;
import domain.Osoba;
import repository.db.DBBroker;
import so.AbstractSO;

public class AddOsobaSO extends AbstractSO {


    @Override
    protected void validate(AbstractDomainObject ado) throws Exception {
        if (ado == null || !(ado instanceof Osoba)) {
            throw new Exception("Parametar nije validan");
        }
    }

    @Override
    protected void executeOperation(AbstractDomainObject ado) throws Exception {
        DBBroker.getInstance().insert(ado);
    }
}
