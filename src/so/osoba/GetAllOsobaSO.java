package so.osoba;

import domain.Osoba;
import repository.db.DBRepository;
import repository.db.impl.OsobaRepository;
import so.AbstractSO;

import java.util.List;

public class GetAllOsobaSO extends AbstractSO {
    private List<Osoba> osobe;
    private final DBRepository storageOsoba;

    public GetAllOsobaSO() {
        storageOsoba = new OsobaRepository();
    }

    @Override
    protected void precondition(Object param) throws Exception {
        if(!(param instanceof Osoba)){
            throw new Exception("Parametar nije validan");
        }

    }

    @Override
    protected void executeOperation(Object param) throws Exception {
        List<Osoba> lista = storageOsoba.getAll();
        osobe = lista;
    }

    public List<Osoba> getOsobe() {
        return osobe;
    }
}
