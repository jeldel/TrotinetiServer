package so.korisnik;

import domain.AbstractDomainObject;
import domain.Korisnik;
import repository.db.DBBroker;
import so.AbstractSO;

import java.util.ArrayList;
import java.util.List;

public class GetAllKorisnikSO extends AbstractSO {

    private List<Korisnik> korisnici;

    @Override
    protected void validate(AbstractDomainObject ado) throws Exception {
        if(!(ado instanceof Korisnik)){
            throw new Exception("Parametar nije validan");
        }

    }

    @Override
    protected void executeOperation(AbstractDomainObject ado) throws Exception {
        List<AbstractDomainObject> lista = DBBroker.getInstance().selectAll(ado);
        korisnici = (ArrayList<Korisnik>) (ArrayList<?>)lista;
    }

    public List<Korisnik> getKorisnici() {
        return korisnici;
    }
}
