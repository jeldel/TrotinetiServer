package repository.db;

import repository.Repository;

import java.sql.SQLException;

public interface DBRepository<T, K> extends Repository<T, K> {

    default public void connect() throws SQLException {
        DBConnectionFactory.getInstance().getConnection();
    }

    default public void disconnect() throws SQLException {
        DBConnectionFactory.getInstance().getConnection().close();
    }

    default public void commit() throws SQLException {
        DBConnectionFactory.getInstance().getConnection().commit();
    }

    default public void rollback() throws SQLException {
        DBConnectionFactory.getInstance().getConnection().rollback();
    }

}
