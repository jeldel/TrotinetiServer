package so.voznja;

import domain.IznajmljivanjeTrotineta;
import repository.db.DBRepository;
import repository.db.impl.IznajmljivanjeRepository;
import so.AbstractSO;

import java.util.List;

public class GetAllVoznjaSO extends AbstractSO {
    private List<IznajmljivanjeTrotineta> voznje;
    private final DBRepository storageVoznje;

    public GetAllVoznjaSO() {
        storageVoznje = new IznajmljivanjeRepository();
    }

    @Override
    protected void precondition(Object param) throws Exception {
        if(!(param instanceof IznajmljivanjeTrotineta)){
            throw new Exception("Parametar nije validan");
        }
    }

    @Override
    protected void executeOperation(Object param) throws Exception {
        List<IznajmljivanjeTrotineta> lista = storageVoznje.getAll();
        voznje = lista;
    }

    public List<IznajmljivanjeTrotineta> getVoznje() {
        return voznje;
    }
}
