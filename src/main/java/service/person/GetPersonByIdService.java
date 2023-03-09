package service.person;

import domainModell.person.Person;
import repository.Dao;
import repository.DaoFactory;
import service.BaseService;

import java.sql.SQLException;
import java.util.Optional;

public class GetPersonByIdService extends BaseService<Optional<Person>> {
    private int id;
    public GetPersonByIdService(int id) {
        this.id = id;
    }
    @Override
    public Optional<Person> execute() throws SQLException {
        Dao<Person> dao = daoFactory.get(DaoFactory.type.PERSON);
        return dao.get(this.id);
    }
}
