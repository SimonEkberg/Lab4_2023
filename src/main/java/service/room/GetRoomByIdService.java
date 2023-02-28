package service.room;

import db.DbConnectionManager;
import domainModell.person.Person;
import domainModell.room.Room;
import repository.DaoFactory;
import repository.PersonDao;
import repository.RoomDao;
import service.BaseService;
import service.CleaningManagerServiceException;
import service.ServiceCommand;

import java.util.NoSuchElementException;

public class GetRoomByIdService extends BaseService<Room> {

    private int id;
    public GetRoomByIdService(int id){
        this.id = id;
    }


    @Override
    public Room execute(){
        RoomDao roomDao = new RoomDao();
        DbConnectionManager.getInstance().open();
        try {
            Room room = roomDao.get(this.id);
            return room;
        }
        catch (NoSuchElementException e){
            throw new CleaningManagerServiceException(e.getMessage());
        }
        finally {
            DbConnectionManager.getInstance().close();
        }
    }
}
