package repository;

import domainModell.person.Person;

import java.util.List;

/**
 * Interface for all CRUD operations of DAO classes
 * @author awi
 *
 * @param <T> One of the intended model classes that a DAO
 * implementation should be able to handle
 */
public interface Dao<T> {

    T get(int id);

    List<T> getAll();

    T save(T t);

    T update(T t);

    T delete(int id);
}
