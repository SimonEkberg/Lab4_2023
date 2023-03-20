package service;

import domainModell.person.Person;
import repository.PersonDao;

import java.sql.SQLException;
import java.util.List;
import java.util.Optional;

public class PersonService {
    private final PersonDao personDao;

    public PersonService() {
        this(new PersonDao());
    }
    public PersonService(PersonDao personDao) {
        this.personDao = personDao;
    }

    public Optional<Person> getPerson(int id) throws SQLException {
        return personDao.get(id);
    }

    public List<Person> findAllPerson() throws SQLException {
        return personDao.getAll();
    }
    public Optional<Person> savePerson(Person person) throws SQLException {
        return personDao.save(person);
    }

    public Optional<Person> updatePerson(Person person) throws SQLException {
        return personDao.update(person);
    }
    public Optional<Person> deletePerson(int id) throws SQLException {
        return personDao.delete(id);
    }
}