package service;

import domainModell.person.Person;
import repository.PersonDao;

import java.util.List;

public class PersonService {
    private final PersonDao personDao;

    public PersonService() {
        this(new PersonDao());
    }
    public PersonService(PersonDao personDao) {
        this.personDao = personDao;
    }

    public Person getPerson(int id){
        return personDao.get(id);
    }

    public List<Person> findAllPerson(){
        return personDao.getAll();
    }
    public Person savePerson(Person person){
        return personDao.save(person);
    }

    public Person updatePerson(Person person){
        return personDao.update(person);
    }
    public boolean deletePerson(int id){
        return personDao.delete(id);
    }



}
