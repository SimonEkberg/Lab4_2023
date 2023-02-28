package service.person;

import db.DbConnectionManager;
import repository.PersonDao;
import service.CleaningManagerServiceException;
import service.CommandService;

import java.util.NoSuchElementException;

public class DeletePersonByIdService implements CommandService<Boolean> {
    private int id;

    public DeletePersonByIdService(int id){
        this.id = id;
    }
    @Override
    public Boolean execute() {
        PersonDao personDao = new PersonDao();
        DbConnectionManager.getInstance().open();
        try{
            boolean isDeleted = personDao.delete(id);
            return isDeleted;
        }catch (NoSuchElementException e){
            throw new CleaningManagerServiceException(e.getMessage());
        }
        finally {
            DbConnectionManager.getInstance().close();
        }
    }
}
