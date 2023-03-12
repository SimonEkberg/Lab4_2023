package service.room;

import domainModell.room.Room;
import repository.DaoFactory;
import service.BaseService;

import java.sql.SQLException;
import java.util.Optional;

public class UpdateRoomService extends BaseService<Optional> {
    private Room room;
    public UpdateRoomService(Room room){
        this.room = room;
    }

    @Override
    public Optional execute() throws SQLException {
        return daoFactory.get(DaoFactory.type.ROOM).update(this.room);
    }
}
