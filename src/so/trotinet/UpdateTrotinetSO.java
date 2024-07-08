package so.trotinet;

import domain.Trotinet;
import repository.db.DBRepository;
import repository.db.impl.TrotinetRepository;
import so.AbstractSO;


public class UpdateTrotinetSO extends AbstractSO {
    private final DBRepository storageTrotinet;

    public UpdateTrotinetSO() {
        storageTrotinet = new TrotinetRepository();
    }

    @Override
    protected void precondition(Object param) throws Exception {
        if(param == null || !(param instanceof Trotinet)){
            throw new Exception("Parametar nije validan");
        }

    }

    @Override
    protected void executeOperation(Object param) throws Exception {
        storageTrotinet.update((Trotinet) param);
    }
}
