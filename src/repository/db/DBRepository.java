package repository.db;

import repository.Repository;

import java.sql.SQLException;

public interface DBRepository<T, K> extends Repository<T, K> {

    default public void connect() throws SQLException {
        DBBroker.getInstance().getConnection();
    }

    default public void disconnect() throws SQLException {
        DBBroker.getInstance().getConnection().close();
    }

    default public void commit() throws SQLException {
        DBBroker.getInstance().getConnection().commit();
    }

    default public void rollback() throws SQLException {
        DBBroker.getInstance().getConnection().rollback();
    }

}
