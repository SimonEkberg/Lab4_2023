package service.room;

import db.DbConnectionManager;
import repository.RoomDao;
import service.CleaningManagerServiceException;
import service.CommandService;

import java.util.NoSuchElementException;

public class DeleteRoomByIdService implements CommandService<Boolean> {
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
