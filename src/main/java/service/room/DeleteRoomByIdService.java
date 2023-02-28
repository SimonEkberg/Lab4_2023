package service.room;

import db.DbConnectionManager;
import repository.RoomDao;
import service.BaseService;
import service.CleaningManagerServiceException;
import service.ServiceCommand;

import java.util.NoSuchElementException;

public class DeleteRoomByIdService extends BaseService<Boolean> {
    private int id;

    public DeleteRoomByIdService(int id){
        this.id = id;
    }
    @Override
    public Boolean execute() {
        RoomDao personDao = new RoomDao();
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
