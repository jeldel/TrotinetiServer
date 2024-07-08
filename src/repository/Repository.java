package repository;

import java.util.List;

public interface Repository<T, K> {

    void add(T param) throws Exception;

    void update(T param) throws Exception;

    void delete(K criteria) throws Exception;

    List<T> getAll() throws Exception;

    List<T> getAllByCriteria(K criteria) throws Exception;

}
