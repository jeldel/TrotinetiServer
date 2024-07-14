package so.voznja;

import domain.AbstractDomainObject;
import domain.IznajmljivanjeTrotineta;
import repository.db.DBBroker;
import so.AbstractSO;

import java.util.ArrayList;
import java.util.List;

public class GetAllVoznjaByCriteriaSO extends AbstractSO {
    private List<IznajmljivanjeTrotineta> iznajmljivanjeTrotinetaList;
    @Override
    protected void validate(AbstractDomainObject ado) throws Exception {
        if(!(ado instanceof IznajmljivanjeTrotineta)){
            throw new Exception("Parametar nije validan");
        }
    }

    @Override
    protected void executeOperation(AbstractDomainObject ado) throws Exception {
        List<AbstractDomainObject> lista = DBBroker.getInstance().selectWithCriteria(ado);
        iznajmljivanjeTrotinetaList = (ArrayList<IznajmljivanjeTrotineta>) (ArrayList<?>)lista;
    }

    public List<IznajmljivanjeTrotineta> getIznajmljivanjeTrotinetaList() {
        return iznajmljivanjeTrotinetaList;
    }
}
