package so.osoba;

import domain.AbstractDomainObject;
import domain.Osoba;
import repository.db.DBBroker;
import so.AbstractSO;

import java.util.ArrayList;
import java.util.List;

public class GetAllOsobaSO extends AbstractSO {

    private List<Osoba> osobe;

    @Override
    protected void precondition(AbstractDomainObject ado) throws Exception {
        if(!(ado instanceof Osoba)){
            throw new Exception("Parametar nije validan");
        }

    }

    @Override
    protected void executeOperation(AbstractDomainObject ado) throws Exception {
        List<AbstractDomainObject> lista = DBBroker.getInstance().selectAll(ado);
        osobe = (ArrayList<Osoba>) (ArrayList<?>)lista;
    }

    public List<Osoba> getOsobe() {
        return osobe;
    }
}
