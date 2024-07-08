package so.trotinet;

import domain.Trotinet;
import repository.db.DBRepository;
import repository.db.impl.TrotinetRepository;
import so.AbstractSO;

import java.util.List;

public class GetAllTrotinetSO extends AbstractSO {
    private List<Trotinet> trotineti;
    private final DBRepository storageTrotineti;

    public GetAllTrotinetSO() {
        storageTrotineti = new TrotinetRepository();
    }

    @Override
    protected void precondition(Object param) throws Exception {
        if(!(param instanceof Trotinet)){
            throw new Exception("Parametar nije validan");
        }

    }

    @Override
    protected void executeOperation(Object param) throws Exception {
        List<Trotinet> lista = storageTrotineti.getAll();
        trotineti = lista;
    }

    public List<Trotinet> getTrotineti() {
        return trotineti;
    }
}
