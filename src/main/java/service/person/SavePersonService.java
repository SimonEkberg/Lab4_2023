package service.person;

import domainModell.person.Person;
import repository.DaoFactory;
import service.BaseService;

import java.util.List;
import java.util.Optional;

public class SavePersonService extends BaseService<Optional<Person>> {
    private Person person;
    public SavePersonService(Person person){
        this.person = person;
    }
    @Override
    public Optional<Person> execute() {
        return daoFactory.get(DaoFactory.type.PERSON).save(this.person);
    }
}
