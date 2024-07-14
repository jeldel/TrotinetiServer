package so;

import domain.AbstractDomainObject;
import repository.db.DBBroker;

import java.sql.SQLException;

public abstract class AbstractSO {

    public void execute(AbstractDomainObject ado) throws Exception {
        try {
            validate(ado);
            executeOperation(ado);
            commitTransaction();
        } catch (Exception e) {
            rollbackTransaction();
        }
    }


    protected abstract void validate(AbstractDomainObject ado) throws Exception;

    protected abstract void executeOperation( AbstractDomainObject ado) throws Exception;


    protected void commitTransaction() throws SQLException {
        DBBroker.getInstance().getConnection().commit();
    }

    protected void rollbackTransaction() throws SQLException {
        DBBroker.getInstance().getConnection().rollback();
    }


}
