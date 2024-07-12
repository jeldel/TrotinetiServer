package so.voznja;

import domain.AbstractDomainObject;
import domain.IznajmljivanjeTrotineta;
import repository.db.DBBroker;
import repository.db.DBRepository;
import repository.db.impl.IznajmljivanjeRepository;
import so.AbstractSO;


public class AddVoznjaSO extends AbstractSO {


    @Override
    protected void precondition(AbstractDomainObject ado) throws Exception {
        if (ado == null || !(ado instanceof IznajmljivanjeTrotineta)) {
            throw new Exception("Parametar nije validan");
        }
    }

    @Override
    protected void executeOperation(AbstractDomainObject ado) throws Exception {
        DBBroker.getInstance().insert(ado);
    }
}
