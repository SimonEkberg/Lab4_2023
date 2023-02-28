package service.room;

import db.DbConnectionManager;
import domainModell.room.Room;
import repository.RoomDao;
import service.CleaningManagerServiceException;
import service.CommandService;

import java.util.NoSuchElementException;

public class UpdateRoomService implements CommandService<Room> {
    private Room room;
    public UpdateRoomService(Room room){
        this.room = room;
    }

    @Override
    public Room execute() {
        RoomDao personDao = new RoomDao();
        DbConnectionManager.getInstance().open();
        try {
            Room room = personDao.update(this.room);
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
