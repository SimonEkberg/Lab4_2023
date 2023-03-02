package service.person;

import domainModell.person.Person;
import repository.DaoFactory;
import service.BaseService;

public class DeletePersonByIdService extends BaseService<Person> {
    private int id;
    public DeletePersonByIdService(int id){
        this.id = id;
    }
    @Override
    public Person execute() {
        return (Person) daoFactory.get(DaoFactory.type.PERSON).delete(id);
    }
}
