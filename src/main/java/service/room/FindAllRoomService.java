package service.room;

import db.DbConnectionManager;
import domainModell.room.Room;
import repository.RoomDao;
import service.CommandService;

import java.util.List;

public class FindAllRoomService implements CommandService<List<Room>> {
    public FindAllRoomService(){
    }
    @Override
    public List<Room> execute() {
        RoomDao roomDao = new RoomDao();
        DbConnectionManager.getInstance().open();
        List<Room> list = roomDao.getAll();
        DbConnectionManager.getInstance().close();
        return list;
    }
}
