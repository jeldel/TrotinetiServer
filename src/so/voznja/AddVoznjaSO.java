package so.voznja;

import domain.IznajmljivanjeTrotineta;
import repository.db.DBRepository;
import repository.db.impl.IznajmljivanjeRepository;
import so.AbstractSO;


public class AddVoznjaSO extends AbstractSO {
    private final DBRepository storageVoznje;

    public AddVoznjaSO() {
        storageVoznje = new IznajmljivanjeRepository();
    }

    @Override
    protected void precondition(Object param) throws Exception {
        if (param == null || !(param instanceof IznajmljivanjeTrotineta)) {
            throw new Exception("Parametar nije validan");
        }
    }

    @Override
    protected void executeOperation(Object param) throws Exception {
        storageVoznje.add(param);
    }
}
