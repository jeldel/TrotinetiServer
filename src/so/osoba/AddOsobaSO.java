package so.osoba;

import domain.Osoba;
import repository.db.DBRepository;
import repository.db.impl.OsobaRepository;
import so.AbstractSO;

public class AddOsobaSO extends AbstractSO {
    private final DBRepository storageOsoba;

    public AddOsobaSO() {
        storageOsoba = new OsobaRepository();
    }

    @Override
    protected void precondition(Object param) throws Exception {
        if (param == null || !(param instanceof Osoba)) {
            throw new Exception("Parametar nije validan");
        }
    }

    @Override
    protected void executeOperation(Object param) throws Exception {
        storageOsoba.add((Osoba) param);
    }
}
