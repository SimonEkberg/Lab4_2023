package service.person;

import domainModell.person.Person;
import repository.DaoFactory;
import service.BaseService;
import service.CleaningManagerServiceException;
import java.util.NoSuchElementException;

public class DeletePersonByIdService extends BaseService<Person> {
    private int id;

    public DeletePersonByIdService(int id){
        this.id = id;
    }
    @Override
    public Person execute() {
        try{
            return (Person) daoFactory.get(DaoFactory.FactoryType.PERSON).delete(id);
        }catch (NoSuchElementException e){
            throw new CleaningManagerServiceException(e.getMessage());
        }
    }
}
