package service.person;

import db.DbConnectionManager;
import domainModell.person.Person;
import repository.PersonDao;
import service.CommandService;

public class SavePersonService implements CommandService<Person> {
    private Person person;
    public SavePersonService(Person person){
        this.person = person;
    }
    @Override
    public Person execute() {
        PersonDao personDao = new PersonDao();
        DbConnectionManager.getInstance().open();
        Person instance = personDao.save(this.person);
        DbConnectionManager.getInstance().close();
        return instance;
    }
}
