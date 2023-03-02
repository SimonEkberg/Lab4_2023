package service.room;

import domainModell.room.Room;
import repository.DaoFactory;
import service.BaseService;

import java.util.List;

public class FindAllRoomService extends BaseService<List<Room>> {
    @Override
    public List<Room> execute() {
        return daoFactory.get(DaoFactory.type.ROOM).getAll();
    }
}
