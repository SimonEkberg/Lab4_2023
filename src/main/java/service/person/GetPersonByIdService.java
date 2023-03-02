package service.person;

import domainModell.person.Person;
import repository.Dao;
import repository.DaoFactory;
import service.BaseService;

public class GetPersonByIdService extends BaseService<Person> {
    private int id;
    public GetPersonByIdService(int id) {
        this.id = id;
    }
    @Override
    public Person execute(){
        Dao<Person> dao = daoFactory.get(DaoFactory.type.PERSON);
        return dao.get(this.id);
    }
}
