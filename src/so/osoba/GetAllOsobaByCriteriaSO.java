package so.osoba;

import domain.AbstractDomainObject;
import domain.Korisnik;
import domain.Osoba;
import repository.db.DBBroker;
import so.AbstractSO;

import java.util.ArrayList;
import java.util.List;


public class GetAllOsobaByCriteriaSO extends AbstractSO
{
    private List<Osoba> osobe;
    @Override
    protected void validate(AbstractDomainObject ado) throws Exception {
        if(!(ado instanceof Osoba)){
            throw new Exception("Parametar nije validan");
        }
    }

    @Override
    protected void executeOperation(AbstractDomainObject ado) throws Exception {
        List<AbstractDomainObject> lista = DBBroker.getInstance().selectWithCriteria(ado);
        osobe = (ArrayList<Osoba>) (ArrayList<?>)lista;
    }

    public List<Osoba> getLista() {
        return osobe;
    }
}
