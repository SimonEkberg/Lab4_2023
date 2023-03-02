package service.room;

import domainModell.room.Room;
import repository.DaoFactory;
import service.BaseService;

public class UpdateRoomService extends BaseService<Room> {
    private Room room;
    public UpdateRoomService(Room room){
        this.room = room;
    }

    @Override
    public Room execute() {
        return (Room) daoFactory.get(DaoFactory.type.ROOM).update(this.room);
    }
}
