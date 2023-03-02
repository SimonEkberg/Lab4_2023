package service.person;

import domainModell.person.Person;
import repository.DaoFactory;
import service.BaseService;

public class SavePersonService extends BaseService<Person> {
    private Person person;
    public SavePersonService(Person person){
        this.person = person;
    }
    @Override
    public Person execute() {
        return (Person) daoFactory.get(DaoFactory.type.PERSON).save(this.person);
    }
}
