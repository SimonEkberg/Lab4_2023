package service.room;

import domainModell.room.Room;
import repository.DaoFactory;
import service.BaseService;

public class GetRoomByIdService extends BaseService<Room> {

    private int id;
    public GetRoomByIdService(int id){
        this.id = id;
    }
    @Override
    public Room execute(){
        return (Room) daoFactory.get(DaoFactory.type.ROOM).get(this.id);
    }
}
