package service.room;

import domainModell.room.Room;
import repository.DaoFactory;
import service.BaseService;

public class DeleteRoomByIdService extends BaseService<Room> {
    private int id;
    public DeleteRoomByIdService(int id){
        this.id = id;
    }
    @Override
    public Room execute() {
        return (Room) daoFactory.get(DaoFactory.type.ROOM).delete(id);
    }
}
