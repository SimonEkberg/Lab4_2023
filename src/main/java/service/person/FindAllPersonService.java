package service.person;

import db.DbConnectionManager;
import domainModell.person.Person;
import repository.DaoFactory;
import service.BaseService;

import java.util.List;

public class FindAllPersonService extends BaseService<List<Person>> {
    @Override
    public List<Person> execute() {
        return daoFactory.get(DaoFactory.FactoryType.PERSON).getAll();
    }
}