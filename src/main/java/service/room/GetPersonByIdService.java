package service.room;

import db.DbConnectionManager;
import domainModell.person.Person;
import domainModell.room.Room;
import repository.PersonDao;
import repository.RoomDao;
import service.CleaningManagerServiceException;
import service.CommandService;

import java.util.NoSuchElementException;

public class GetPersonByIdService implements CommandService<Room> {

    private int id;
    public GetPersonByIdService(int id){
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
