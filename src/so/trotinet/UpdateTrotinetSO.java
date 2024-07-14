package so.trotinet;

import domain.AbstractDomainObject;
import domain.Trotinet;
import repository.db.DBBroker;
import so.AbstractSO;


public class UpdateTrotinetSO extends AbstractSO {

    @Override
    protected void validate(AbstractDomainObject ado) throws Exception {
        if(!(ado instanceof Trotinet)){
            throw new Exception("Parametar nije validan");
        }

    }

    @Override
    protected void executeOperation(AbstractDomainObject ado) throws Exception {
        DBBroker.getInstance().update(ado);
    }
}
