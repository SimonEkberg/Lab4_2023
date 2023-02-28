package service.person;

import db.DbConnectionManager;
import domainModell.person.Person;
import repository.PersonDao;
import service.CleaningManagerServiceException;
import service.CommandService;

import java.util.NoSuchElementException;

public class UpdatePersonService implements CommandService<Person> {
    private Person person;
    public UpdatePersonService(Person person){
        this.person = person;
    }

    @Override
    public Person execute() {
        PersonDao personDao = new PersonDao();
        DbConnectionManager.getInstance().open();
        try {
            Person person = personDao.update(this.person);
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
