package service.room;

import db.DbConnectionManager;
import domainModell.room.Room;
import repository.RoomDao;
import service.CommandService;

public class SaveRoomService implements CommandService<Room> {
    private Room room;
    public SaveRoomService(Room room){
        this.room = room;
    }
    @Override
    public Room execute() {
        RoomDao personDao = new RoomDao();
        DbConnectionManager.getInstance().open();
        Room instance = personDao.save(this.room);
        DbConnectionManager.getInstance().close();
        return instance;
    }
}
