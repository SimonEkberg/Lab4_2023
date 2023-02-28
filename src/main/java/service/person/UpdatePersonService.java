package service.person;

import db.DbConnectionManager;
import domainModell.person.Person;
import repository.PersonDao;
import service.BaseService;
import service.CleaningManagerServiceException;
import service.ServiceCommand;

import java.util.NoSuchElementException;

public class UpdatePersonService extends BaseService<Person> {
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
