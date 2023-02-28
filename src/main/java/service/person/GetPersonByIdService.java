package service.person;

import db.DbConnectionManager;
import domainModell.person.Person;
import repository.PersonDao;
import service.CleaningManagerServiceException;
import service.CommandService;

import java.util.NoSuchElementException;

public class GetPersonByIdService implements CommandService<Person> {

    private int id;
    public GetPersonByIdService(int id){
        this.id = id;
    }

    @Override
    public Person execute(){
        PersonDao personDao = new PersonDao();
        DbConnectionManager.getInstance().open();
        try {
            Person person = personDao.get(this.id);
            return person;
        }
        catch (NoSuchElementException e){
            throw new CleaningManagerServiceException(e.getMessage());
        }
        finally {
            DbConnectionManager.getInstance().close();
        }
    }
}
