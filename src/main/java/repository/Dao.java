package repository;

import domainModell.person.Person;

import java.sql.SQLException;
import java.util.List;
import java.util.Optional;

/**
 * Interface for all CRUD operations of DAO classes
 * @author awi
 *
 * @param <T> One of the intended model classes that a DAO
 * implementation should be able to handle
 */
public interface Dao<T> {

    Optional<T> get(int id) throws SQLException;

    List<T> getAll() throws SQLException;

    Optional<T> save(T t) throws SQLException;

    Optional<T> update(T t) throws SQLException;

    Optional<T> delete(int id) throws SQLException;
}

