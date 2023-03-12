package service.person;

import domainModell.person.Person;
import repository.DaoFactory;
import service.BaseService;

import java.sql.SQLException;
import java.util.Optional;

public class SavePersonService extends BaseService<Optional<Person>> {
    private Person person;
    public SavePersonService(Person person){
        this.person = person;
    }
    @Override
    public Optional<Person> execute() throws SQLException {
        return daoFactory.get(DaoFactory.type.PERSON).save(this.person);
    }
}
