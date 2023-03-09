package service.person;

import domainModell.person.Person;
import repository.DaoFactory;
import service.BaseService;

import java.sql.SQLException;
import java.util.Optional;

public class DeletePersonByIdService extends BaseService<Optional<Person>> {
    private int id;
    public DeletePersonByIdService(int id){
        this.id = id;
    }
    @Override
    public Optional<Person> execute() throws SQLException {
        return daoFactory.get(DaoFactory.type.PERSON).delete(id);
    }
}
