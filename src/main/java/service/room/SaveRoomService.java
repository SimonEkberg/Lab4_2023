package service.room;

import domainModell.room.Room;
import repository.DaoFactory;
import service.BaseService;

public class SaveRoomService extends BaseService<Room> {
    private Room room;
    public SaveRoomService(Room room){
        this.room = room;
    }
    @Override
    public Room execute() {
        return (Room) daoFactory.get(DaoFactory.type.ROOM).save(this.room);
    }
}
