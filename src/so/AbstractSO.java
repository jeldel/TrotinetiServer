package so;

import repository.Repository;
import repository.db.DBConnectionFactory;

import java.sql.SQLException;

public abstract class AbstractSO {

    public void execute(Object param) throws Exception {
        try {
            precondition(param);
            executeOperation(param);
            commitTransaction();
        } catch (Exception e) {
            rollbackTransaction();
        }
    }


    protected abstract void precondition(Object param) throws Exception;

    protected abstract void executeOperation(Object param) throws Exception;


    protected void commitTransaction() throws SQLException {
        DBConnectionFactory.getInstance().getConnection().commit();
    }

    protected void rollbackTransaction() throws SQLException {
        DBConnectionFactory.getInstance().getConnection().rollback();
    }


}
