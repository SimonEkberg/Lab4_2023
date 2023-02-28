package service.person;

import domainModell.person.Person;
import service.BaseService;
import service.CleaningManagerServiceException;
import java.util.NoSuchElementException;

public class GetPersonByIdService extends BaseService<Person> {
    private int id;
    @Override
    public Person execute(){
        try {
            return daoFactory.getPersonDao().get(this.id);
        }
        catch (NoSuchElementException e){
            throw new CleaningManagerServiceException(e.getMessage());
        }
    }
}
