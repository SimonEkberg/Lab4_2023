package service.person;

import domainModell.person.Person;
import repository.DaoFactory;
import service.BaseService;

import java.util.Optional;

public class UpdatePersonService extends BaseService<Optional> {
    private Person person;
    public UpdatePersonService(Person person){
        this.person = person;
    }

    @Override
    public Optional execute() {
        return daoFactory.get(DaoFactory.type.PERSON).update(this.person);
    }
}
