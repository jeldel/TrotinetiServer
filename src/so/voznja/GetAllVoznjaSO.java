package so.voznja;

import domain.AbstractDomainObject;
import domain.IznajmljivanjeTrotineta;
import repository.db.DBBroker;
import so.AbstractSO;

import java.util.ArrayList;
import java.util.List;

public class GetAllVoznjaSO extends AbstractSO {
    private List<IznajmljivanjeTrotineta> voznje;

    @Override
    protected void precondition(AbstractDomainObject ado) throws Exception {
        if(!(ado instanceof IznajmljivanjeTrotineta)){
            throw new Exception("Parametar nije validan");
        }
    }

    @Override
    protected void executeOperation(AbstractDomainObject ado) throws Exception {
        List<AbstractDomainObject> lista = DBBroker.getInstance().selectAll(ado);
        voznje = (ArrayList<IznajmljivanjeTrotineta>) (ArrayList<?>)lista;
    }

    public List<IznajmljivanjeTrotineta> getVoznje() {
        return voznje;
    }
}
