package service.room;

import domainModell.room.Room;
import repository.DaoFactory;
import service.BaseService;

import java.sql.SQLException;
import java.util.List;

public class FindAllRoomService extends BaseService<List<Room>> {
    @Override
    public List<Room> execute() throws SQLException {
        return daoFactory.get(DaoFactory.type.ROOM).getAll();
    }
}
