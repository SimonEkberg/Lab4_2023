package service.person;

import db.DbConnectionManager;
import domainModell.person.Person;
import repository.PersonDao;
import service.CommandService;

import java.util.List;

public class FindAllPersonService implements CommandService<List<Person>> {
    public FindAllPersonService(){
    }
    @Override
    public List<Person> execute() {
        PersonDao personDao = new PersonDao();
        DbConnectionManager.getInstance().open();
        List<Person> list = personDao.getAll();
        DbConnectionManager.getInstance().close();
        return list;
    }
}
