package service.person;

import domainModell.person.Person;
import repository.DaoFactory;
import service.BaseService;

import java.sql.SQLException;
import java.util.List;

public class FindAllPersonService extends BaseService<List<Person>> {
    @Override
    public List<Person> execute() throws SQLException {
        return daoFactory.get(DaoFactory.type.PERSON).getAll();
    }
}