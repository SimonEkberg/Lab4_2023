package service.room;

import db.DbConnectionManager;
import domainModell.room.Room;
import repository.DaoFactory;
import repository.RoomDao;
import service.BaseService;
import service.ServiceCommand;

import java.util.List;

public class FindAllRoomService extends BaseService<List<Room>> {
    public FindAllRoomService(){
    }
    @Override
    public List<Room> execute() {
        return new DaoFactory().getRoomDao().getAll();
    }
}
