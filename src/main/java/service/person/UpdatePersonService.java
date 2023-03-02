package service.person;

import domainModell.person.Person;
import repository.DaoFactory;
import service.BaseService;

public class UpdatePersonService extends BaseService<Person> {
    private Person person;
    public UpdatePersonService(Person person){
        this.person = person;
    }

    @Override
    public Person execute() {
        return (Person) daoFactory.get(DaoFactory.type.PERSON).update(this.person);
    }
}
