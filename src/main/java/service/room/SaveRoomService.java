package service.room;

import domainModell.room.Room;
import repository.DaoFactory;
import service.BaseService;

import java.util.Optional;

public class SaveRoomService extends BaseService<Optional> {
    private Room room;
    public SaveRoomService(Room room){
        this.room = room;
    }
    @Override
    public Optional execute() {
        return daoFactory.get(DaoFactory.type.ROOM).save(this.room);
    }
}
