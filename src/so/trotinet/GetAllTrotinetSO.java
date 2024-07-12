package so.trotinet;

import domain.AbstractDomainObject;
import domain.Trotinet;
import repository.db.DBBroker;
import so.AbstractSO;

import java.util.ArrayList;
import java.util.List;

public class GetAllTrotinetSO extends AbstractSO {
    private List<Trotinet> trotineti;

    @Override
    protected void precondition(AbstractDomainObject ado) throws Exception {
        if(!(ado instanceof Trotinet)){
            throw new Exception("Parametar nije validan");
        }

    }

    @Override
    protected void executeOperation(AbstractDomainObject ado) throws Exception {
        List<AbstractDomainObject> lista = DBBroker.getInstance().selectAll(ado);
        trotineti = (ArrayList<Trotinet>) (ArrayList<?>) lista;
    }

    public List<Trotinet> getTrotineti() {
        return trotineti;
    }
}
