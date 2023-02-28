package service.room;

import db.DbConnectionManager;
import domainModell.room.Room;
import repository.RoomDao;
import service.BaseService;
import service.ServiceCommand;

public class SaveRoomService extends BaseService<Room> {
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
