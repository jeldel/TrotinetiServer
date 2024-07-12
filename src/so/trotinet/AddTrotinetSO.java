package so.trotinet;

import domain.AbstractDomainObject;
import domain.Trotinet;
import repository.db.DBBroker;
import repository.db.DBRepository;
import repository.db.impl.TrotinetRepository;
import so.AbstractSO;

public class AddTrotinetSO extends AbstractSO {


    @Override
    protected void precondition(AbstractDomainObject ado) throws Exception {
        if(ado == null || !(ado instanceof Trotinet)){
            throw new Exception("Parametar nije validan");
        }

    }

    @Override
    protected void executeOperation(AbstractDomainObject ado) throws Exception {
        DBBroker.getInstance().insert(ado);
    }
}
