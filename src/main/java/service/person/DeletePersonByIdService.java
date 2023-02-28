package service.person;

import db.DbConnectionManager;
import repository.DaoFactory;
import repository.PersonDao;
import service.BaseService;
import service.CleaningManagerServiceException;
import service.ServiceCommand;

import java.util.NoSuchElementException;

public class DeletePersonByIdService extends BaseService<Boolean> {
    private int id;

    public DeletePersonByIdService(int id){
        this.id = id;
    }
    @Override
    public Boolean execute() {
        try{
            boolean isDeleted = daoFactory.get(DaoFactory.FactoryType.PERSON).delete(id);
            return isDeleted;
        }catch (NoSuchElementException e){
            throw new CleaningManagerServiceException(e.getMessage());
        }
    }
}
