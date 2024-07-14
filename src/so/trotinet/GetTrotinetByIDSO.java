package so.trotinet;

import domain.AbstractDomainObject;
import domain.Trotinet;
import repository.db.DBBroker;
import so.AbstractSO;


public class GetTrotinetByIDSO extends AbstractSO {
    private Trotinet trotinet;
    @Override
    protected void validate(AbstractDomainObject ado) throws Exception {
        if(!(ado instanceof Trotinet)){
            throw new Exception("Parametar nije validan");
        }
    }

    @Override
    protected void executeOperation(AbstractDomainObject ado) throws Exception {
       Trotinet trotinet1 = (Trotinet) DBBroker.getInstance().selectOneWithCriteria(ado);
       trotinet = trotinet1;
    }

    public Trotinet getTrotinet() {
        return trotinet;
    }
}
